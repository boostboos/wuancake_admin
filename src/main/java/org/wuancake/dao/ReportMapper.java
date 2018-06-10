package org.wuancake.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.wuancake.entity.GatherBean;
import org.wuancake.entity.ReportBean;

import java.util.Date;
import java.util.List;

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
     * 通过分组id查找所有用户的周报汇总相关字段，此方法为导师提供
     *
     * @param groupId    分组id
     * @param startIndex 索引起始
     * @param pageSize   索引结束
     * @return 汇总相关集合
     */
//    @Select("select distinct u.id,u.create_time, user_name,QQ,group_name " +
//            "from user u,report r,wa_group w,user_group ug " +
//            "where r.group_id = #{groupId} " +
//            "and u.id = ug.user_id " +
//            "and ug.group_id = r.group_id " +
//            "and r.group_id = w.id " +
//            "and ug.deleteFlg = 0 " +
//            "limit #{startIndex},#{pageSize}")

    @Select("select distinct u.id,u.user_name,u.QQ,u.create_time,wg.group_name " +
            "from user u,wa_group wg " +
            "where u.id in (select user_id from user_group where deleteFlg = 0 and group_id = #{groupId}) " +
            "and wg.id = (select group_id from user_group where user_id = u.id) " +
            "limit #{startIndex},#{pageSize};")
    List<GatherBean> queryByGroupId(@Param("groupId") Integer groupId, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查找所有用户的周报汇总相关字段，此方法为管理员或超级管理员提供
     *
     * @param startIndex 起始索引
     * @param pageSize   结束索引
     * @return 考勤汇总集合
     */
//    @Select("select distinct u.id,u.create_time, user_name,QQ,group_name " +
//            "from user u,report r,wa_group w,user_group ug " +
//            "where u.id = ug.user_id " +
//            "and ug.group_id = r.group_id " +
//            "and r.group_id = w.id " +
//            "and ug.deleteFlg = 0 " +
//            "limit #{startIndex},#{pageSize}")

    @Select("select distinct u.id,u.user_name,u.QQ,u.create_time,wg.group_name " +
            "from user u,wa_group wg " +
            "where u.id in (select user_id from user_group where deleteFlg = 0) " +
            "and wg.id = (select group_id from user_group where user_id = u.id) " +
            "limit #{startIndex},#{pageSize};")
    List<GatherBean> queryAll(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查找某一周某一个用户的状态
     *
     * @param userId
     * @param weekNum
     * @return
     */
    @Select("select week_num,status from report " +
            "where user_id = #{userId} " +
            "and week_num = #{weekNum} ")
    @Cacheable
    ReportBean queryReportStatu(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum);


    /**
     * 更新用户周报状态，用于自动检查用户周报是否提交
     *
     * @param userId
     * @param weekNum
     * @param groupId
     * @param date
     */
    @Insert("insert into report " +
            "values (#{weekNum},#{userId},#{groupId},'本周未提交周报',1,#{date}) ")
    void updateUserReportStatu(@Param("userId") Integer userId, @Param("weekNum") Integer weekNum, @Param("groupId") Integer groupId, @Param("date") Date date);

<<<<<<< HEAD

    @Select("<script>select `report`.*,`user`.user_name userName,wa_group.group_name groupName from report,`user`,wa_group where report.group_id = wa_group.id and user_id = `user`.id <if test = 'weeks > 0'> and week_num = #{weeks}</if> <if test = 'groups > 0'>and group_id = #{groups}</if></script>")
	List<ReportBean> queryReportByWeekAndGroup(@Param("weeks") Integer weeks, @Param("groups") Integer groups);
=======
    @Select("select week_num weekNum,user_id userId,group_id groupId,text,status,reply_time replyTime from report where group_id = #{groups} and week_num = #{weeks}")
    List<ReportBean> queryReportByWeekAndGroup(@Param("weeks") Integer weeks, @Param("groups") Integer groups);
>>>>>>> 6d20a6416c00ba8ce03f543f1a18880b44651d33

    /**
     * 查询所有周报数(distinct)
     *
     * @return
     */
    @Select("select count(user_id) " +
            "from user_group " +
            "where deleteFlg = 0 " +
            "and group_id !=0")
    int querySize();

    /**
     * 查询某一分组的周报数(distinct)
     *
     * @param groupId
     * @return
     */
    @Select("select count(user_id) " +
            "from user_group " +
            "where group_id = #{groupId} " +
            "and deleteFlg = 0")
    int querySizeByGroupId(@Param("groupId") Integer groupId);
    
    
    
    
    
}