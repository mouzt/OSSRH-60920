package com.meituan.mzt.service;

import com.meituan.mzt.domain.User;

/**
 * 用户服务
 */
public interface IUserService {

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);

}
