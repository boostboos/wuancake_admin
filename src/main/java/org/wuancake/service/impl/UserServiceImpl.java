package org.wuancake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuancake.dao.UserMapper;
import org.wuancake.entity.UserBean;
import org.wuancake.service.IUserService;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean queryUserByQQ(String qq) {
        return userMapper.queryUserByQQ(qq);
    }

    @Override
    public void removeByUserId(Integer userId, String headsman, Date date) {
        userMapper.removeByUserId(userId, headsman, date);
    }

}
