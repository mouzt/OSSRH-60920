package com.mzt.toolkit.service.impl;


import com.mzt.toolkit.beans.Operator;
import com.mzt.toolkit.service.IOperatorGetService;

/**
 * @author muzhantong
 * create on 2020/4/29 5:45 下午
 */
public class OperatorGetServiceImpl implements IOperatorGetService {

    @Override
    public Operator getUser() {
        Operator operator = new Operator();
        operator.setOperatorName("牟站通");
        operator.setOperatorId("111");
        return operator;
    }
}
