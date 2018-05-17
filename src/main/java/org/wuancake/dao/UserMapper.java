package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select id from user where qq = #{QQ}")
    Integer queryUserIdByQQ(@Param("QQ") String QQ);
}
