package org.wuancake.utils;

import org.wuancake.dao.ReportMapper;
import org.wuancake.dao.UserMapper;
import org.wuancake.entity.ReportBean;

import java.util.Date;
import java.util.List;

/**
 * @author Ericheel
 * @Description: 考勤工具类
 * @date 2018/6/212:21
 */
public class AttendUtils {

    public static void rectifyUserReportStatus(UserMapper userMapper, ReportMapper reportMapper) {
        Integer maxWeekNum = WeekNumUtils.getMaxWeekNum();
        List<Integer> ids = userMapper.queryAllUserIdNotKicked();
        for (Integer id : ids) {
            ReportBean reportBean = reportMapper.queryReportStatu(id, maxWeekNum);
            if (reportBean == null) {
                Integer groupId = userMapper.queryGroupIdByUserId(id);
                if (groupId != null && groupId != 0) {
                    //当前周没提交 更新数据库
                    reportMapper.updateUserReportStatu(id, maxWeekNum, groupId, new Date());
                }
            }
        }
    }
}
