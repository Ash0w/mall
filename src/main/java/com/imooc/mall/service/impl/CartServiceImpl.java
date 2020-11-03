package com.imooc.mall.service.impl;

import com.google.gson.Gson;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.enums.ProductStatusEnum;
import com.imooc.mall.enums.ResposeEnum;
import com.imooc.mall.from.CartAddFrom;
import com.imooc.mall.from.CartUpdateFrom;
import com.imooc.mall.pojo.Cart;
import com.imooc.mall.pojo.Product;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.vo.CartProductVo;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        opsForHash.put(redisKey, String.valueOf(product.getId()), gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = opsForHash.entries(redisKey);
        boolean selectAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = new ArrayList<>();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            //TODO 需要优化，使用mysql里的in
            Product product = productMapper.selectByPrimaryKey(productId);
            if (product != null) {
                CartProductVo cartProductVo = new CartProductVo(productId, cart.getQuantity(), product.getName(), product.getSubtitle(),
                        product.getMainImage(), product.getPrice(), product.getStatus(), product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        product.getStock(), cart.getProductSelected()
                );
                cartProductVoList.add(cartProductVo);
                if (!cart.getProductSelected()) {
                    selectAll = false;
                }
                //计算购物车选中商品的总价（只计算选中的）
                if (cart.getProductSelected()) {
                    cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
                }
            }
            cartTotalQuantity += cart.getQuantity();
        }
        cartVo.setSelectAll(selectAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateFrom cartUpdateFrom) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        //从Redis读出value，对数量进行操作
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)) {
            //购物车里没有该商品，报错
            return ResponseVo.error(ResposeEnum.CART_PRODUCT_NOT_EXSIT);
        }
        //已经存在该商品，修改内容
        Cart cart = gson.fromJson(value, Cart.class);
        if (cartUpdateFrom.getQuantity() != null && cartUpdateFrom.getQuantity() >= 0) {
            cart.setQuantity(cartUpdateFrom.getQuantity());
        }
        if (cartUpdateFrom.getSelected() != null) {
            cart.setProductSelected(cartUpdateFrom.getSelected());
        }
        opsForHash.put(redisKey, String.valueOf(productId), gson.toJson(cart));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        //从Redis读出value，对数量进行操作
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)) {
            //购物车里没有该商品，报错
            return ResponseVo.error(ResposeEnum.CART_PRODUCT_NOT_EXSIT);
        }
        opsForHash.delete(redisKey, String.valueOf(productId));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        for (Cart cart : listForCart(uid)) {
            cart.setProductSelected(true);
            opsForHash.put(redisKey, String.valueOf(cart.getProductId()), gson.toJson(cart));
        }
        return list(uid);
    }
    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        for (Cart cart : listForCart(uid)) {
            cart.setProductSelected(false);
            opsForHash.put(redisKey, String.valueOf(cart.getProductId()), gson.toJson(cart));
        }
        return list(uid);
    }
    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        Integer sum = listForCart(uid).stream().map(Cart::getQuantity).reduce(0, Integer::sum);
        return ResponseVo.success(sum);
    }
    private List<Cart> listForCart(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        List<Cart> cartList = new ArrayList<>();
        Map<String, String> entries = opsForHash.entries(redisKey);
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            cartList.add(gson.fromJson(entry.getValue(), Cart.class));
        }
        return cartList;
    }
}
