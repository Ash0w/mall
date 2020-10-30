package com.imooc.mall.service.impl;

import com.google.gson.Gson;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.enums.ProductStatusEnum;
import com.imooc.mall.enums.ResposeEnum;
import com.imooc.mall.from.CartAddFrom;
import com.imooc.mall.pojo.Cart;
import com.imooc.mall.pojo.Product;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoyu
 * Date: 2020/10/30
 * Time: 15:14
 * Description: Be brave or be a loser
 */
@Service
public class CartServiceImpl implements ICartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    public ResponseVo<CartVo> add(Integer uid, CartAddFrom cartAddFrom) {
        Integer quantity = 1;
        Product product = productMapper.selectByPrimaryKey(cartAddFrom.getProductId());
        //判断商品是否存在
        if (product == null) {
            return ResponseVo.error(ResposeEnum.PRODUCT_NOT_EXSIT);
        }
        //商品是否在售
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())) {
            return ResponseVo.error(ResposeEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        //库存是否充足
        if (product.getStock() <= 0) {
            return ResponseVo.error(ResposeEnum.PRODUCT_STOCK_ERROR);
        }
        //写入Redis
        //key:cart_1
        //使用Redis的hash结构存入数据
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Cart cart;
        //从Redis读出value，对数量进行操作
        String value = opsForHash.get(redisKey, String.valueOf(product.getId()));
        if (StringUtils.isEmpty(value)) {
            //没有该商品，执行新增
            cart = new Cart(product.getId(), quantity, cartAddFrom.getSelected());
        } else {
            //已经存在该商品，执行数量+1
            //json反序列化为对象
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + quantity);
        }
        opsForHash.put(redisKey,String.valueOf(product.getId()),gson.toJson(cart));

        return null;
    }
}
