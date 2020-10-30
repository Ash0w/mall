package com.imooc.mall.service;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.from.CartAddFrom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ICartServiceTest extends MallApplicationTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void add() {
        CartAddFrom cartAddFrom = new CartAddFrom();
        cartAddFrom.setProductId(27);
        cartAddFrom.setSelected(true);
        cartService.add(1, cartAddFrom);
    }
}