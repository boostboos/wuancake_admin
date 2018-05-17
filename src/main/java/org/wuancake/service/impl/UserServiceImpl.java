package org.wuancake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuancake.dao.UserMapper;
import org.wuancake.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer queryUserIdByQQ(String qq) {
        return userMapper.queryUserIdByQQ(qq);
    }
}
