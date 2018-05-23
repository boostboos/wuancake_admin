package org.wuancake.dao;

import org.apache.ibatis.annotations.*;
import org.wuancake.entity.UserBean;

/**
 * 用户相关Mapper接口
 *
 * @author
 * @date
 */
@Mapper
public interface UserMapper {

    /**
     * 通过qq查找用户
     *
     * @param QQ qq
     * @return 用户实体
     */
    @Select("select * from user where qq = #{QQ}")
    UserBean queryUserByQQ(@Param("QQ") String QQ);

    /**
     * 通过用户id踢掉用户（deleteFlg=1）即可
     *
     * @param user_id 用户id
     */
    @Update("update user " +
            "set deleteFlg = 1 " +
            "where id = #{user_id}")
    void removeByUserId(@Param("user_id") Integer user_id);
}
