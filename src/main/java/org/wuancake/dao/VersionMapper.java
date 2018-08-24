package org.wuancake.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.wuancake.entity.VersionBean;

import java.util.List;

/**
 * @author
 * @date
 */
@Mapper
@CacheConfig(cacheNames = "versionCaches")
public interface VersionMapper {

    /**
     * 添加版本
     *
     * @param versionBean
     * @return status
     */
    @Insert("insert into version " +
            "values(null,#{v},#{url})")
    Integer add(VersionBean versionBean);

    /**
     * 修改版本
     *
     * @param vid
     * @param v
     * @param url
     * @return status
     */
    @Update("update version set v = #{v}, url = #{url} where vid = #{vid}")
    Integer update(@Param("vid") Integer vid, @Param("v") Long v, @Param("url") String url);

    /**
     * 查询App版本 总数
     *
     */
    @Select("select count(vid) from version")
    Integer findVersionCount();

    /**
     * 查询所有App版本
     *
     * @param startIndex
     * @param pageSize
     */
    @Select("select vid,v,url " +
            "from version " +
            "limit #{startIndex},#{pageSize}")
    List<VersionBean> findAll(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);


    /**
     * 查询详情
     *
     * @param vid 主键
     */
    @Select("select vid, v, url from version where vid = #{vid}")
    VersionBean findById(@Param("vid") Integer vid);
}
