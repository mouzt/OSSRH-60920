package com.meituan.mzt.service.impl;


import com.meituan.mzt.domain.User;
import com.meituan.mzt.service.IUserService;
import com.meituan.mzt.inter.UserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRemoteService userRemoteService;

    @Override
    public boolean updateUserInfo(User user) {
        if (user == null ) {
            return false;
        }
        User oldUser = userRemoteService.queryUserInfo(user.getMisId());
        if (oldUser == null) {
            return false;
        }
        return userRemoteService.update(user);
    }
}
