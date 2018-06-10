package org.wuancake.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wuancake.dao.ReportMapper;
import org.wuancake.dao.UserMapper;
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

    //@Scheduled(cron = "0/10 * * * * *")
    public void rectifyUserReportStatus() {
        /*
        定时检查所有用户上周周报状态
         */
        Integer maxWeekNum = WeekNumUtils.getMaxWeekNum();
        List<Integer> ids = userMapper.queryAllUserIdNotKicked();
        for (int i = maxWeekNum; i >= maxWeekNum - 10; i--) {
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
