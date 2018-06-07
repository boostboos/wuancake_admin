package org.wuancake.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.TutorBean;
import org.wuancake.entity.UserBean;
import org.wuancake.entity.UserGroupBean;

import java.util.List;

/**
 * @author
 * @date
 */
@Mapper
@CacheConfig(cacheNames = "adminCaches")
public interface AdminMapper {

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
    TutorBean findTutorByEmail(String email);

    /**
     * 增加管理员
     *
     * @param adminBean
     */
    @Insert("insert into adm " +
            "values(null,#{username},#{email},#{password},#{auth},#{groupId})")
    void addAdmin(AdminBean adminBean);

    @Select("select * from user_group where deleteFlg = 1")
    List<UserGroupBean> queryAllUserBeKicked();

    @Select("select * from adm " +
            "where email = #{email}")
    AdminBean findAdminByEmail(@Param("email") String email);
}
