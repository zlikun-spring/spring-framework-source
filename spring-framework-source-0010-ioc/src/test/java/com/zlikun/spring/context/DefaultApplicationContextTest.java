package com.zlikun.spring.context;

import com.zlikun.spring.dto.UserInfo;
import com.zlikun.spring.service.UserService;
import com.zlikun.spring.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 测试自定义ApplicationContext
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/12 10:49
 */
public class DefaultApplicationContextTest {

    private ApplicationContext context = new DefaultApplicationContext();

    @BeforeEach
    public void init() {

        // 向容器中注册一个Bean
        context.registerSingleton("userService", UserServiceImpl.class);
        // 刷新容器
        context.refresh();
        // 启动容器
        context.start();
    }

    @AfterEach
    public void destroy() {
        context.stop();
    }

    @Test
    public void ioc() {

        // 从容器中取出一个Bean
        UserService userService = context.getBean("userService", UserService.class);

        // 应用这个Bean
        UserInfo info = userService.query("zlikun");
        assertNotNull(info);

    }

}