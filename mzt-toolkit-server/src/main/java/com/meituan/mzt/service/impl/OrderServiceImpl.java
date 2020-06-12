package com.meituan.mzt.service.impl;

import com.meituan.mzt.constants.LogRecordType;
import com.meituan.mzt.domain.Order;
import com.meituan.mzt.service.IOrderService;
import com.mzt.toolkit.annotation.LogRecordAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author muzhantong
 * create on 2020/6/12 11:08 上午
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    /*'张三下了一个订单,购买商品「超值优惠红烧肉套餐」,下单结果:true' */
    @Override
    @LogRecordAnnotation(success = "{{#order.purchaseName}}下了一个订单,购买商品「{{#order.productName}}」,下单结果:{{#_ret}}",
            bizKey = LogRecordType.ORDER +"{{#order.orderNo}}", bizNo = "{{#order.orderNo}}")
    public boolean createOrder(Order order) {
        log.info("【创建订单】orderNo={}", order.getOrderNo());
        // db insert order
        return true;
    }
}
