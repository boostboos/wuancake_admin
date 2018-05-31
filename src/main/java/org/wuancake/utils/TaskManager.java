package org.wuancake.utils;


import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wuancake.dao.ReportMapper;
import org.wuancake.dao.UserMapper;
import org.wuancake.entity.ReportBean;

import java.util.List;

/**
 * @author Ericheel
 * @Description: 任务调度
 * @date 2018/5/3111:23
 */
@Component
public class TaskManager {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Scheduled(cron = "0/2 * * * * ?")
    public void rectifyUserReportStatus() {
        Integer maxWeekNum = WeekNumUtils.getMaxWeekNum();
        System.out.println(maxWeekNum);
        List<Integer> ids = userMapper.queryAllUserIdNotKicked();

        for (Integer id : ids) {
            List<ReportBean> reportBeans = reportMapper.queryReportStatus(id, maxWeekNum);

            System.out.println(reportBeans);
        }

    }

}
