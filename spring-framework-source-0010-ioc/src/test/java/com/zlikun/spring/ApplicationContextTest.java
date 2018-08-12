package com.zlikun.spring;

import com.zlikun.spring.dto.UserInfo;
import com.zlikun.spring.service.UserService;
import com.zlikun.spring.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.StaticApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 从ApplicationContext类入手研究IoC实现机制<br>
 * 下面测试用例描述了完整的IoC容器生命周期
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/12 10:24
 */
public class ApplicationContextTest {

    // ApplicationContext即为Spring的IoC容器，其实现包含基于Xml的和Annotation的
    // 这里选择StaticApplicationContext实现类，该类不依赖外部配置，简化代码结构
    private StaticApplicationContext context = new StaticApplicationContext();

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
