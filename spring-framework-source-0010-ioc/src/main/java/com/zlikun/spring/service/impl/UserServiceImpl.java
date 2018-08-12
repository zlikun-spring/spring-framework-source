package com.zlikun.spring.service.impl;

import com.zlikun.spring.dto.UserInfo;
import com.zlikun.spring.service.UserService;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/12 10:34
 */
public class UserServiceImpl implements UserService {
    @Override
    public UserInfo query(String username) {
        return new UserInfo();
    }
}
