package com.meituan.mzt.service;

import com.meituan.mzt.domain.User;
import com.meituan.mzt.inter.UserRemoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

/**
 * UserService测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRemoteService userRemoteService;

    @Autowired
    private IUserService userService;

    @Before
    public void setUp() {
        when(userRemoteService.queryUserInfo("1")).thenReturn(new User("1", "name"));
        when(userRemoteService.update(isA(User.class))).thenReturn(true);
    }

    @Test
    public void update() throws Exception {
        boolean result = userService.updateUserInfo(new User("1", "newName"));
        assertTrue("must true", result);

        // AssertJ写法
        assertThat(result).isTrue();

        // 验证是否执行过一次queryUserInfo(1)
        verify(userRemoteService, times(1)).queryUserInfo(eq("1"));

        // 验证是否执行过一次update
        verify(userRemoteService, times(1)).update(isA(User.class));
    }

    @Test
    public void updateNotFind() throws Exception {
        boolean result = userService.updateUserInfo(new User("2", "newName"));
        assertFalse("must false", result);

        // AssertJ写法
        assertThat(result).isFalse();

        // 验证是否执行过一次queryUserInfo(1)
        verify(userRemoteService, times(1)).queryUserInfo(eq("2"));

        // 验证是否执行过一次update
        verify(userRemoteService, never()).update(isA(User.class));
    }
}
