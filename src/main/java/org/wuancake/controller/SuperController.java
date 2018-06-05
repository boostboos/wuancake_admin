package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wuancake.entity.*;
import org.wuancake.service.IReportService;
import org.wuancake.service.IUserService;
import org.wuancake.utils.WeekNumUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 共性代码
 */
@Controller
@EnableAsync
public class SuperController {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IUserService userService;

    @Async
    public @ResponseBody
    Future<PageBean> pageQuery(Integer currPage, Integer weekNum, Integer groups, HttpServletRequest request, AdminBean isAdmin) {
        Integer auth = isAdmin.getAuth();

        PageBean pageBean = new PageBean();
        pageBean.setCurrPage(currPage);
        Integer pageSize = 10;
        Integer startIndex = (currPage - 1) * pageSize;

        //设置考勤汇总相关的list
        List<GatherBean> list;
        if (auth == 1) {
            Integer groupId = isAdmin.getGroupId();
            list = reportService.queryByGroupId(groupId, startIndex, pageSize);
        } else {
            list = reportService.queryAll(startIndex, pageSize);
        }
        //获取截至的周数
        if (weekNum == null || weekNum == 0) {
            //按照最新的周数来，否则是按照选择的周数来查询
            weekNum = WeekNumUtils.getMaxWeekNum();
        }
        for (GatherBean gather : list) {
            UserBean user = userService.queryUserByQQ(gather.getQQ());
            Boolean aProtected = WeekNumUtils.isProtected(user.getCreateTime());
            gather.setIsUnderProtected(WeekNumUtils.isProtected(user.getCreateTime()) ? 1 : 0);
            gather.setId(user.getId());

            List<ReportBean> reportBeans = null;
            if (auth == 1) {
                reportBeans = reportService.queryReportStatus(user.getId(), weekNum);
            } else {
                //管理员或超级管理员
                if (groups == null || groups == 0) {
                    reportBeans = reportService.queryReportStatus(user.getId(), weekNum);
                } else {
                    reportBeans = reportService.queryReportStatusByGroupId(user.getId(), weekNum, groups);
                }
            }
            //要填充的周报状态map
            Map<Integer, Integer> report4StatusMap = new HashMap<>();
            switch (reportBeans.size()) {
                case 4:
                    report4StatusMap.put(weekNum - 3, reportBeans.get(0).getStatus());
                    report4StatusMap.put(weekNum - 2, reportBeans.get(1).getStatus());
                    report4StatusMap.put(weekNum - 1, reportBeans.get(2).getStatus());
                    report4StatusMap.put(weekNum, reportBeans.get(3).getStatus());
                    gather.setReport4StatusMap(report4StatusMap);
                    pageBean.getGathers().add(gather);
                    break;
                case 3:
                    report4StatusMap.put(weekNum - 3, 1);
                    report4StatusMap.put(weekNum - 2, reportBeans.get(0).getStatus());
                    report4StatusMap.put(weekNum - 1, reportBeans.get(1).getStatus());
                    report4StatusMap.put(weekNum, reportBeans.get(2).getStatus());
                    gather.setReport4StatusMap(report4StatusMap);
                    pageBean.getGathers().add(gather);
                    break;
                case 2:
                    report4StatusMap.put(weekNum - 3, 1);
                    report4StatusMap.put(weekNum - 2, 1);
                    report4StatusMap.put(weekNum - 1, reportBeans.get(0).getStatus());
                    report4StatusMap.put(weekNum, reportBeans.get(1).getStatus());
                    gather.setReport4StatusMap(report4StatusMap);
                    pageBean.getGathers().add(gather);
                    break;
                case 1:
                    report4StatusMap.put(weekNum - 3, 1);
                    report4StatusMap.put(weekNum - 2, 1);
                    report4StatusMap.put(weekNum - 1, 1);
                    report4StatusMap.put(weekNum, reportBeans.get(0).getStatus());
                    gather.setReport4StatusMap(report4StatusMap);
                    pageBean.getGathers().add(gather);
                    break;
                case 0:
                    break;
            }
        }
        Integer totalSize = list.size();
        //当前页第一页
        pageBean.setCurrPage(currPage);
        //总记录数为查询得到
        pageBean.setTotalSize(totalSize);
        //每页固定显式10条,limit (start,10)
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage((int) Math.ceil((double) totalSize / pageSize));

        return new AsyncResult<>(pageBean);

    }

}
