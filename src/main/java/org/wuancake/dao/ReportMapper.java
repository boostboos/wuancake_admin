package org.wuancake.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.wuancake.entity.GatherBean;
import org.wuancake.entity.ReportBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 周报相关
 *
 * @author
 * @date
 */
@Mapper
@CacheConfig(cacheNames = "reportCaches")
public interface ReportMapper {
    /**
     * 通过分组id查找所需字段，此方法为导师提供
     *
     * @param groupId    分组id
     * @param startIndex 索引起始
     * @param pageSize   索引结束
     * @return 汇总相关集合
     */
    @Select("select distinct user_name,QQ, group_name,u.create_time,u.id " +
            "from user u,report r,wa_group w,user_group ug " +
            "where r.group_id = #{groupId} " +
            "and u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and ug.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<GatherBean> queryByGroupId(@Param("groupId") Integer groupId, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查找所有用户的周报汇总相关字段，此方法为管理员或超级管理员提供
     *
     * @param startIndex 起始索引
     * @param pageSize   结束索引
     * @return 考勤汇总集合
     */
    @Select("select distinct u.id,u.create_time, user_name,QQ,group_name " +
            "from user u,report r,wa_group w,user_group ug " +
            "where u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and ug.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<GatherBean> queryAll(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    @Select("select distinct u.id,u.create_time, user_name,QQ,group_name " +
            "from user u,report r,wa_group w,user_group ug " +
            "where r.group_id = #{groupId} " +
            "and u.id = r.user_id " +
            "and r.group_id = w.id " +
            "and ug.deleteFlg = 0 " +
            "limit #{startIndex},#{pageSize}")
    List<GatherBean> queryByGroupIdAndWeekNum(Integer groupId, Integer startIndex, Integer pageSize, String weekNum);

    /**
     * 查找用户截至的四周周报的状态
     *
     * @param userId  用户id
     * @param weekNum 截至周数（四周）
     * @return 状态码集合
     */
    @Select("select week_num,status from report r,user_group ug " +
            "where r.user_id = ug.user_id " +
            "and r.user_id = #{userId} " +
            "and ug.deleteFlg = 0 " +
            "and r.week_num in " +
            "(#{weekNum},#{weekNum}-1,#{weekNum}-2,#{weekNum}-3)")
//            "(select x.weekNum from " +
//            "(select weekNum from report where userId = #{userId} order by weekNum desc limit 0,4) as x)")
    List<ReportBean> queryReportStatus(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum);

    /**
     * 查找某一周某一个用户的状态
     *
     * @param userId
     * @param weekNum
     * @return
     */
    @Select("select week_num,status from report r " +
            "where user_id = #{userId} " +
            "and week_num = #{weekNum} ")
    ReportBean queryReportStatu(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum);

    @Select("select week_num,status from report r,user_group ug " +
            "where r.user_id = ug.user_id " +
            "and r.user_id = #{userId} " +
            "and r.group_id = #{groupId} " +
            "and ug.deleteFlg = 0 " +
            "and r.week_num in " +
            "(#{weekNum},#{weekNum}-1,#{weekNum}-2,#{weekNum}-3)")
    List<ReportBean> queryReportStatusByGroupId(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum, @Param("groupId") int groupId);


    @Insert("insert into report " +
            "values (#{weekNum},#{userId},#{groupId},'null',1,#{date}) ")
    void updateUserReportStatu(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum, @Param("groupId") Integer groupId, @Param("date") Date date);


    @Select("select week_num weekNum,user_id userId,group_id groupId,text,status,reply_time replyTime from report where group_id = #{groups} and week_num = #{weeks}")
    List<ReportBean> queryReportByWeekAndGroup(@Param("weeks") Integer weeks, @Param("groups") Integer groups);

    @Select("select count(distinct user_id) " +
            "from report")
    int querySize();

    @Select("select count(distinct user_id) " +
            "from report " +
            "where group_id = #{groupId}")
    int querySizeByGroupId(@Param("groupId") Integer groupId);
}
