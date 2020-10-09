package com.meituan.mzt.service.impl;

import com.meituan.mzt.BaseTest;
import com.meituan.mzt.domain.Order;
import com.meituan.mzt.service.IOrderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class OrderServiceImplTest extends BaseTest {

    @Resource
    private IOrderService orderService;

    @Test
    public void createOrder() {
        Order order = new Order();
        order.setOrderNo("MT0000011");
        order.setProductName("超值优惠红烧肉套餐");
        order.setPurchaseName("张三");
        boolean ret = orderService.createOrder(order);
        Assert.assertTrue(ret);

    }
}