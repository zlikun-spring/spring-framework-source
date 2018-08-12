package com.zlikun.spring.context;

/**
 * 模拟Spring版本的ApplicationContext生命周期，自实现一个版本（简化）
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/12 10:46
 */
public interface ApplicationContext {

    <T> void registerSingleton(String name, Class<T> type);

    void refresh();

    void start();

    void stop();

    <T> T getBean(String name, Class<T> type);

}
