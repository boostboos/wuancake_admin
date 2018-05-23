package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.GatherBean;

import java.util.List;

/**
 * 周报相关
 *
 * @author
 * @date
 */
@Mapper
public interface ReportMapper {
    /**
     * 通过分组id查找所需字段，此方法为导师提供
     *
     * @param group_id   分组id
     * @param startIndex 索引起始
     * @param pageSize   索引结束
     * @return 汇总相关集合
     */
    @Select("select distinct user_name,QQ,group_name " +
            "from user u,report r,wa_group w " +
            "where r.group_id = #{group_id} " +
            "and u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and  u.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<GatherBean> queryByGroupId(@Param("group_id") Integer group_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查找用户截至的四周周报的状态
     *
     * @param user_id 用户id
     * @param weekNum 截至周数（四周）
     * @return 状态码集合
     */
    @Select("select status from report " +
            "where user_id = #{user_id} " +
            "and week_num in " +
            "(select x.week_num from " +
            "(select week_num from report where user_id = #{user_id} order by week_num desc limit 0,4) as x)")
    List<Integer> queryReportStatus(@Param("user_id") Integer user_id, @Param("weekNum") Integer weekNum);

    /**
     * 查找所有用户的周报汇总相关字段，此方法为管理员或超级管理员提供
     *
     * @param startIndex 起始索引
     * @param pageSize   结束索引
     * @return 考勤汇总集合
     */
    @Select("select distinct user_name,QQ,group_name " +
            "from user u,report r,wa_group w " +
            "where u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and  u.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<GatherBean> queryAll(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
}
