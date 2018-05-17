package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.Admin;

@Mapper
public interface AdminMapper {
    @Select("select * from adm where email=#{email} and password=#{password}")
    Admin findAdminByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
