package com.zlikun.spring.context;


import org.springframework.beans.BeanUtils;

import java.util.HashMap;

/**
 * 实现自定义ApplicationContext
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/12 10:46
 */
public class DefaultApplicationContext implements ApplicationContext {

    // 这里仅为了模拟Bean容器，不考虑并发和程序健壮性要求
    private HashMap<String, BeanDefinition> cache = new HashMap<>();

    /**
     * Bean定义类，用于记录Bean注册时的类型及实例
     *
     * @param <T>
     */
    private static class BeanDefinition<T> {
        Class<T> type;
        T instance;

        public BeanDefinition(Class<T> type, T instance) {
            this.type = type;
            this.instance = instance;
        }
    }

    @Override
    public <T> void registerSingleton(String name, Class<T> type) {
        // 如果注入Bean不是一个可实例化类，抛出异常
        // 话说怎样用反射简单的判定一个Class实例是一个可实例化类型，这里简单判断是接口就算不是可实例化类（很明显，不严谨，等后面找到合理方法再修改）
        if (type.isInterface()) {
            throw new IllegalArgumentException("注册类型必须是一个LocalClass");
        }

        // 如果Bean不存在，则注册之
        if (!cache.containsKey(name)) {
            // 根据注册类型使用反射创建一个实例
            cache.put(name, new BeanDefinition(type, BeanUtils.instantiateClass(type)));
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public <T> T getBean(String name, Class<T> type) {
        BeanDefinition<T> definition = cache.get(name);
        if (definition == null) return null;
        // 如果类型一致，直接返回实例
        if (definition.type.equals(type)) return definition.instance;
        // 如果传入类型为注册类型父类或接口，直接返回实例
        if (type.isAssignableFrom(definition.type)) return definition.instance;
        // 其它返回空
        return null;
    }
}
