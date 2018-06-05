package org.wuancake.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.wuancake.entity.UserBean;

import java.util.List;

/**
 * 用户相关Mapper接口
 *
 * @author
 * @date
 */
@Mapper
@CacheConfig(cacheNames = "userCaches")
public interface UserMapper {

    /**
     * 通过qq查找用户
     *
     * @param QQ qq
     * @return 用户实体
     */
    @Select("select * from user where qq = #{QQ}")
    @Cacheable
    UserBean queryUserByQQ(@Param("QQ") String QQ);

    /**
     * 通过用户id踢掉用户（deleteFlg=1）即可
     *
     * @param userId 用户id
     */
    @Update("update user_group " +
            "set group_id = 0 " +
            "where user_id = #{userId}")
    void removeByUserId(@Param("userId") Integer userId);

    @Select("select user_id from user_group " +
            "where group_id != 0")
    @Cacheable
    List<Integer> queryAllUserIdNotKicked();

    @Select("select group_id from user_group " +
            "where user_id = #{userId} " +
            "and group_id != 0")
    @Cacheable
    Integer queryGroupIdByUserId(@Param("userId") Integer userId);
}
