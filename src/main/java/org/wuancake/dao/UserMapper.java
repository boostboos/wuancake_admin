package org.wuancake.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.wuancake.entity.UserBean;

import java.util.Date;
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

    UserBean queryUserByQQ(@Param("QQ") String QQ);

    /**
     * 通过用户id踢掉用户（deleteFlg=1）即可
     *
     * @param userId 用户id
     */
    @Update("update user_group " +
            "set headsman=#{headsman},modify_time=#{modifyTime},deleteFlg = 1 " +
            "where user_id = #{userId}")
    void removeByUserId(@Param("userId") Integer userId, @Param("headsman") String headsman, @Param("modifyTime") Date date);

    @Select("select user_id from user_group " +
            "where deleteFlg = 0")

    List<Integer> queryAllUserIdNotKicked();

    @Select("select group_id from user_group " +
            "where user_id = #{userId} " +
            "and deleteFlg = 0")

    Integer queryGroupIdByUserId(@Param("userId") Integer userId);
}
