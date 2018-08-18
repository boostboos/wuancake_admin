package org.wuancake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuancake.dao.VersionMapper;
import org.wuancake.entity.VersionBean;
import org.wuancake.service.VersionService;

import java.util.List;

/**
 * @author Lord
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    private VersionMapper versionMapper;

    @Override
    public void addVersion(VersionBean version) {
        versionMapper.add(version);
    }

    @Override
    public void updateVersion(Integer vid, Long v, String url) {
        versionMapper.update(vid, v, url);
    }

    @Override
    public Integer saveOrUpdate(VersionBean version) {
        Integer status;

        if(version.getVid()==null||"".equals(version.getVid())){
            status = versionMapper.add(version);
        }else{
            status = versionMapper.update(version.getVid(),version.getV(),version.getUrl());
        }

        return status;
    }

    @Override
    public Integer findVersionCount() {
        return versionMapper.findVersionCount();
    }

    @Override
    public VersionBean findOne(Integer vid) {
        return versionMapper.findById(vid);
    }

    @Override
    public List<VersionBean> findAllVersion(Integer startIndex, Integer pageSize) {
        return versionMapper.findAll(startIndex, pageSize);
    }

}
