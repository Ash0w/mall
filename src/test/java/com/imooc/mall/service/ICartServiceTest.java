package com.imooc.mall.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.ResposeEnum;
import com.imooc.mall.from.CartAddFrom;
import com.imooc.mall.from.CartUpdateFrom;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class ICartServiceTest extends MallApplicationTests {
    @Autowired
    private ICartService cartService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Integer uid = 1;
    private Integer productId = 26;

    @Before
    public void add() {
        log.info("-----------新增购物车-----------");
        CartAddFrom cartAddFrom = new CartAddFrom();
        cartAddFrom.setProductId(productId);
        cartAddFrom.setSelected(true);
        ResponseVo<CartVo> responseVo = cartService.add(uid, cartAddFrom);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo<CartVo> responseVo = cartService.list(uid);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
        CartUpdateFrom from = new CartUpdateFrom();
        from.setQuantity(2);
        from.setSelected(false);
        ResponseVo<CartVo> responseVo = cartService.update(uid, productId, from);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @After
    public void delete() {
        log.info("-----------删除购物车-----------");
        ResponseVo<CartVo> responseVo = cartService.delete(uid, productId);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void selectAll() {
        ResponseVo<CartVo> responseVo = cartService.selectAll(uid);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void unSelectAll() {
        ResponseVo<CartVo> responseVo = cartService.unSelectAll(uid);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void sum() {
        ResponseVo<Integer> responseVo = cartService.sum(uid);
        log.info("list={}", gson.toJson(responseVo));
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}