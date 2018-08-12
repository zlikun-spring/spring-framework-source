package com.zlikun.spring.service;

import com.zlikun.spring.dto.UserInfo;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018/8/12 10:33
 */
public interface UserService {

    UserInfo query(String username);

}
