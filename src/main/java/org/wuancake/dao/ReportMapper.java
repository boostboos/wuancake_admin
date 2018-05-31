package org.wuancake.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.GatherBean;
import org.wuancake.entity.ReportBean;

import java.util.List;
import java.util.Map;

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
     * @param groupId   分组id
     * @param startIndex 索引起始
     * @param pageSize   索引结束
     * @return 汇总相关集合
     */
    @Select("select distinct user_name,QQ,group_name " +
            "from user u,report r,wa_group w " +
            "where r.group_id = #{groupId} " +
            "and u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and  u.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<GatherBean> queryByGroupId(@Param("groupId") Integer groupId, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查找用户截至的四周周报的状态
     *
     * @param userId 用户id
     * @param weekNum 截至周数（四周）
     * @return 状态码集合
     */
    @Select("select IFNULL(r.week_num,0) as weekNum , IFNULL(r.status,1) as status from report r " +
            "where user_id = #{userId} " +
            "and week_num in " +
            "(#{weekNum},#{weekNum}-1,#{weekNum}-2,#{weekNum}-3)")
//            "(select x.weekNum from " +
//            "(select weekNum from report where userId = #{userId} order by weekNum desc limit 0,4) as x)")
    List<ReportBean> queryReportStatus(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum);

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
