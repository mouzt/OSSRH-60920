package com.meituan.mzt.thrift;

import com.meituan.xframe.boot.thrift.autoconfigure.annotation.ThriftClientProxy;
import com.meituan.xframe.boot.thrift.autoconfigure.client.ThriftClientProxyTestListener;
import org.apache.thrift.TException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UserThriftService测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners(listeners = ThriftClientProxyTestListener.class)
public class UserThriftServiceTest {

    @ThriftClientProxy(remoteAppKey = "com.sankuai.meishi.paas.gravityweb", remoteServerPort = 8411)
    private UserThriftService.Iface userThriftService;

    @Test
    public void updateUserInfo() throws TException {
        boolean result = userThriftService.updateUserInfo("1", "zhangsan");
        Assert.assertTrue(result);
    }
}