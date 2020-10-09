package com.meituan.mzt.inter;

import com.meituan.mzt.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 模拟调用远程服务
 */
@Service
public class UserRemoteService {

    /**
     * 根据misId查询用户信息
     * @param misId
     * @return 用户信息
     */
    public User queryUserInfo(String misId){
        return new User(misId, "zhangsan");
    }

    /**
     * 更新用户信息
     * @param user
     * @return 更新结果
     */
    public boolean update(User user) {
        return true;
    }
}
