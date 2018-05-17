package org.wuancake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuancake.dao.GroupMapper;
import org.wuancake.dao.ReportMapper;
import org.wuancake.entity.GroupBean;
import org.wuancake.service.IGroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<GroupBean> showGroup() {
        return groupMapper.showGroup();
    }

}
