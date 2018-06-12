package org.wuancake.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Ericheel
 * @Description: 自动考勤周报状态缓存用
 * @date 2018/6/1222:48
 */
@Mapper
public interface WeekCacheMapper {

    @Select("select max(num) " +
            "from week")
    Integer getCurrentWeekNum();

    @Insert("insert into week " +
            "values(#{weekNum})")
    void updateCurrentWeekNum(@Param("weekNum") Integer weekNum);
}
