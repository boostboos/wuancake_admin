package org.wuancake.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.wuancake.entity.*;

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

    @Select("select group_name,user_name,QQ,headsman,ug.modify_time " +
            "from wa_group wg,user u,user_group ug " +
            "where ug.deleteFlg = 1 " +
            "and ug.user_id = u.id " +
            "and ug.group_id = wg.id")
    List<KickBean> queryAllUserBeKicked();

    @Select("select * from adm " +
            "where email = #{email}")
    AdminBean findAdminByEmail(@Param("email") String email);
}
