package org.wuancake.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.wuancake.entity.GroupBean;

import java.util.List;

/**
 * 分组相关
 *
 * @author
 * @date
 */
@Mapper
@CacheConfig(cacheNames = "groupCaches")
public interface GroupMapper {

    /**
     * 查找数据库中分组相关
     *
     * @return 分组JavaBean的list集合
     */
    @Select("select * from wa_group")
    @Results({@Result(column = "id", property = "groupId")})

    List<GroupBean> showGroup();

}
