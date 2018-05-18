package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.UserBean;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from user where qq = #{QQ}")
    UserBean queryUserByQQ(@Param("QQ") String QQ);
}
