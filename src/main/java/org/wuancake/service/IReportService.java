package org.wuancake.service;

import org.wuancake.entity.Gather;

import java.util.List;

public interface IReportService {
    List<Gather> queryByGroupId(Integer group_id, Integer startIndex, Integer pageSize);

    List<Integer> queryReportStatus(Integer user_id, Integer maxWeekNum);

    List<Gather> queryAll(Integer startIndex, Integer pageSize);
}
