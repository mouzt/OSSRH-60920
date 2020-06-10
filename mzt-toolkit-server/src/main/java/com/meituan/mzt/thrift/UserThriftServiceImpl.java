package com.meituan.mzt.thrift;

import com.meituan.mzt.domain.User;
import com.meituan.mzt.service.IUserService;
import com.meituan.xframe.boot.thrift.autoconfigure.annotation.ThriftServerPublisher;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户信息Thrift服务
 */
@ThriftServerPublisher(port = 8411)
public class UserThriftServiceImpl implements UserThriftService.Iface {

    @Autowired
    private IUserService userService;

    @Override
    public boolean updateUserInfo(String misId, String name) throws TException {
        return userService.updateUserInfo(new User(misId, name));
    }
}
