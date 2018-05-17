package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.wuancake.entity.Group;

import java.util.List;

/**
 * 分组相关
 */
@Mapper
public interface GroupMapper {

    @Select("select * from wa_group")
    @Results({@Result(column = "id", property = "group_id")})
    List<Group> showGroup();

}
