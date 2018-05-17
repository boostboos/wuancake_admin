package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.Gather;
import org.wuancake.entity.Report;

import java.util.List;

/**
 * 周报相关
 */
@Mapper
public interface ReportMapper {

    @Select("select distinct user_name,QQ,group_name " +
            "from user u,report r,wa_group w " +
            "where r.group_id = #{group_id} " +
            "and u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and  u.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<Gather> queryByGroupId(@Param("group_id") Integer group_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);


    @Select("select status from report " +
            "where user_id = #{user_id} " +
            "and week_num in ( select x.week_num from (select week_num from report where user_id = #{user_id} order by week_num desc limit 0,4) as x)")
    List<Integer> queryReportStatus(@Param("user_id") Integer user_id, @Param("weekNum") Integer weekNum);

    @Select("select distinct user_name,QQ,group_name " +
            "from user u,report r,wa_group w " +
            "where u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and  u.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<Gather> queryAll(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
}
