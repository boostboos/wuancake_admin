package org.wuancake.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.TutorBean;

/**
 * @author
 * @date
 */
@Mapper
@CacheConfig(cacheNames = "adminCaches")
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

    /**
     * 添加导师
     *
     * @param tutorBean
     */
    @Insert("insert into adm " +
            "values(null,#{username},#{email},#{password},#{auth},#{groupId})")
    void addTutor(TutorBean tutorBean);

    /**
     * 根据邮箱查重
     *
     * @param email
     * @return
     */
    @Select("select * from adm " +
            "where email = #{email}")
    @Cacheable
    TutorBean findTutorByEmail(String email);

    /**
     * 增加管理员
     *
     * @param adminBean
     */
    @Insert("insert into adm " +
            "values(null,#{username},#{email},#{password},#{auth},#{groupId})")
    void addAdmin(AdminBean adminBean);
}
