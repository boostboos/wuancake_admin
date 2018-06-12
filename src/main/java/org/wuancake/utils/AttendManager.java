package org.wuancake.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wuancake.dao.ReportMapper;
import org.wuancake.dao.UserMapper;
import org.wuancake.dao.WeekCacheMapper;
import org.wuancake.entity.ReportBean;

import java.util.Date;
import java.util.List;

/**
 * @author Ericheel
 * @Description: 任务调度
 * @date 2018/6/222:45
 */
@Component
public class AttendManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private WeekCacheMapper weekCacheMapper;

    @Scheduled(cron = "00 00 04 * * SUN")
    public void rectifyUserReportStatus() {
        Integer maxWeekNum = WeekNumUtils.getMaxWeekNum();

        Integer currentWeekNum = weekCacheMapper.getCurrentWeekNum();
        if (currentWeekNum == null || currentWeekNum.equals(maxWeekNum) || currentWeekNum.equals(maxWeekNum - 1)) {
            updateReportStatus(maxWeekNum, 4);
            weekCacheMapper.updateCurrentWeekNum(maxWeekNum);
        } else {
            if (currentWeekNum < maxWeekNum - 1) {
                int howMany = maxWeekNum - currentWeekNum;
                updateReportStatus(maxWeekNum, howMany);
                weekCacheMapper.updateCurrentWeekNum(maxWeekNum);
            }
        }

    }

    private void updateReportStatus(Integer maxWeekNum, Integer howManyWeeks) {
        List<Integer> ids = userMapper.queryAllUserIdNotKicked();
        for (int i = maxWeekNum; i >= maxWeekNum - howManyWeeks; i--) {
            for (Integer id : ids) {
                ReportBean reportBean = reportMapper.queryReportStatu(id, i);
                if (reportBean == null) {
                    Integer groupId = userMapper.queryGroupIdByUserId(id);
                    if (groupId != null && groupId != 0) {
                        //当前周没提交 更新数据库
                        reportMapper.updateUserReportStatu(id, i, groupId, new Date());
                    }
                }
            }
        }
    }
}
