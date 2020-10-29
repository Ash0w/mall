package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.ResposeEnum;
import com.imooc.mall.vo.ProductVo;
import com.imooc.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class IProducrServiceTest extends MallApplicationTests {
    @Autowired
    private IProducrService producrService;

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = producrService.list(null, 1, 3);
        Assert.assertEquals(ResposeEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}