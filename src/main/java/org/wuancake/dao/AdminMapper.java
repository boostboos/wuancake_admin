package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.AdminBean;

/**
 * @author
 * @date
 */
@Mapper
public interface AdminMapper {

    /**
     * 通过邮箱和密码查找admin是否存在
     *
     * @param email    邮箱
     * @param password 密码
     * @return AdminBean
     */
    @Select("select * from adm where email=#{email} and password=#{password}")
    AdminBean findAdminByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
