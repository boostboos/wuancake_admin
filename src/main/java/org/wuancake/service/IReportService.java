package org.wuancake.service;

import org.wuancake.entity.GatherBean;
import org.wuancake.entity.PageBean;
import org.wuancake.entity.ReportBean;

import java.util.List;
import java.util.Map;

public interface IReportService {
    List<GatherBean> queryByGroupId(Integer groupId, Integer startIndex, Integer pageSize);

    List<GatherBean> queryAll(Integer startIndex, Integer pageSize);

    ReportBean queryReportStatu(Integer id, int i);

    int querySizeByGroupId(Integer groupId);

    int querySize();
}
