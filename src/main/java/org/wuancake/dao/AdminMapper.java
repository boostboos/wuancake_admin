package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.AdminBean;

@Mapper
public interface AdminMapper {
    @Select("select * from adm where email=#{email} and password=#{password}")
    AdminBean findAdminByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
