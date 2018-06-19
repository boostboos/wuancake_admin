package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${customConfig.pageSize}")
    private int pageSize;

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
        Integer startIndex = (currPage - 1) * pageSize;
        int totalSize;
        /*
        分页查询所有用户考勤汇总
         */
        List<GatherBean> gatherBeanList;
        if (auth == 1) {
            Integer groupId = isAdmin.getGroupId();
            //为分页查询所有纪录
            totalSize = reportService.querySizeByGroupId(groupId);
            gatherBeanList = reportService.queryByGroupId(groupId, startIndex, pageSize);
        } else {
            if (groups == null || groups == 0) {
                //默认全部分组
                totalSize = reportService.querySize();
                gatherBeanList = reportService.queryAll(startIndex, pageSize);
            } else {
                //说明选择了分组
                totalSize = reportService.querySizeByGroupId(groups);
                gatherBeanList = reportService.queryByGroupId(groups, startIndex, pageSize);
            }
        }
        /*
        判断最大周数如果为0或者不存在，默认为最新周的上一周
         */
        if (weekNum == null || weekNum == 0) {
            weekNum = WeekNumUtils.getMaxWeekNum();
        }
        /*
        设置考勤汇总默认内容，或根据条件设置考勤汇总内容
         */
        for (GatherBean gather : gatherBeanList) {
            /*
            判断并设置是否是保护期
             */
            Boolean isProtected = WeekNumUtils.isProtected(gather.getCreateTime());
            if (isProtected) {
                gather.setIsUnderProtected(1);
                pageBean.getGathers().add(gather);
            } else {
            /*
            查询默认最新四周或者截至周数的四周周报状态
            已有条件：分组名，用户名，用户QQ，用户id，是否是保护期
            未知：四周周报状态
             */
                //要填充的周报状态map
                Map<Integer, Integer> report4StatusMap = new HashMap<>();
            /*
            只需根据weekNum查询四周
             */
                for (int i = weekNum - 3; i <= weekNum; i++) {
                    ReportBean reportBean = reportService.queryReportStatu(gather.getId(), i);
                    if (i == WeekNumUtils.getProtectedWeek(gather.getCreateTime())) {
                        ReportBean bean = reportService.queryReportStatu(gather.getId(), i);
                        Integer status = null;
                        if (bean != null) {
                            status = bean.getStatus();
                        }
                        if (status != null && status == 2) {
                            //那周处于保护期，还提交周报了。。显示已提交
                            report4StatusMap.put(i, 7998);
                        } else {
                            //那周处于保护期，没提交周报。。显示保护期
                            report4StatusMap.put(i, 4857);
                        }
                    } else {
                        report4StatusMap.put(i, reportBean == null ? 1 : reportBean.getStatus() == 1 ? 1 : reportBean.getStatus() == 2 ? 2 : 3);
                    }
                }
                gather.setReport4StatusMap(report4StatusMap);
                pageBean.getGathers().add(gather);
            }
        }
        pageBean.setWeekNum(weekNum);
        pageBean.setTotalSize(totalSize);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage((int) Math.ceil((double) totalSize / pageSize));
        return new AsyncResult<>(pageBean);
    }

}
