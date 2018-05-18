package org.wuancake.service;

import org.wuancake.entity.UserBean;

public interface IUserService {
    UserBean queryUserByQQ(String qq);
}
