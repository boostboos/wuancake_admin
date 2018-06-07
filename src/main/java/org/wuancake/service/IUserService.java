package org.wuancake.service;

import org.wuancake.entity.SimpleInfoByQQ;
import org.wuancake.entity.UserBean;

import java.util.Date;

public interface IUserService {
    UserBean queryUserByQQ(String qq);

    void removeByUserId(Integer userId, String headsman, Date date);

    SimpleInfoByQQ searchInfoByQQ(String qq);
}
