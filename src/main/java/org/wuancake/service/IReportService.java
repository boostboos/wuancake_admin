package org.wuancake.service;

import org.wuancake.entity.GatherBean;

import java.util.List;

public interface IReportService {
    List<GatherBean> queryByGroupId(Integer group_id, Integer startIndex, Integer pageSize);

    List<Integer> queryReportStatus(Integer user_id, Integer maxWeekNum);

    List<GatherBean> queryAll(Integer startIndex, Integer pageSize);
}
