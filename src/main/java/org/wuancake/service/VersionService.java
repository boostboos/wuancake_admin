package org.wuancake.service;

import org.wuancake.entity.VersionBean;

import java.util.List;

public interface VersionService {

    void addVersion(VersionBean version);

    void updateVersion(Integer vid, Long v, String url);

    Integer saveOrUpdate(VersionBean version);

    Integer findVersionCount();

    VersionBean findOne(Integer vid);

    List<VersionBean> findAllVersion(Integer startIndex, Integer pageSize);
}
