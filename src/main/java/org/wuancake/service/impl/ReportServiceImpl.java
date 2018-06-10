package org.wuancake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuancake.dao.ReportMapper;
import org.wuancake.entity.GatherBean;
import org.wuancake.entity.PageBean;
import org.wuancake.entity.ReportBean;
import org.wuancake.service.IReportService;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<GatherBean> queryByGroupId(Integer groupId, Integer startIndex, Integer pageSize) {
        return reportMapper.queryByGroupId(groupId, startIndex, pageSize);
    }

    @Override
    public List<GatherBean> queryAll(Integer startIndex, Integer pageSize) {
        return reportMapper.queryAll(startIndex, pageSize);
    }

    @Override
    public ReportBean queryReportStatu(Integer id, int i) {
        return reportMapper.queryReportStatu(id, i);
    }

    @Override
    public int querySizeByGroupId(Integer groupId) {
        return reportMapper.querySizeByGroupId(groupId);
    }

    @Override
    public int querySize() {
        return reportMapper.querySize();
    }


    public List<ReportBean> queryReportByWeekAndGroup(Integer weeks, Integer groups) {
        return reportMapper.queryReportByWeekAndGroup(weeks, groups);
    }


}
