package org.wuancake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuancake.dao.ReportMapper;
import org.wuancake.entity.Gather;
import org.wuancake.service.IReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Gather> queryByGroupId(Integer group_id, Integer startIndex, Integer pageSize) {
        return reportMapper.queryByGroupId(group_id, startIndex, pageSize);
    }

    @Override
    public List<Integer> queryReportStatus(Integer user_id, Integer maxWeekNum) {
        return reportMapper.queryReportStatus(user_id, maxWeekNum);
    }

    @Override
    public List<Gather> queryAll(Integer startIndex, Integer pageSize) {
        return reportMapper.queryAll(startIndex, pageSize);
    }
}
