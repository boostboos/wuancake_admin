package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * 共性代码
 */
@Controller
public class SuperController {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IUserService userService;

    public @ResponseBody
    PageBean pageQuery(Integer currPage, Integer maxWeekNum, HttpServletRequest request, AdminBean isAdmin) {

        Integer auth = isAdmin.getAuth();

        PageBean pageBean = new PageBean();
        pageBean.setCurrPage(currPage);
        Integer pageSize = 10;
        Integer startIndex = (currPage - 1) * pageSize;

        //获取截至的周数
        if (maxWeekNum == null) {
            //按照最新的周数来，否则是按照选择的周数来查询
            maxWeekNum = WeekNumUtils.getMaxWeekNum();
        }
        //设置考勤汇总相关的list
        List<GatherBean> list;
        if (auth == 1) {
            //导师，只分页查询负责的分组,默认当前组最新四周
            Integer groupId = isAdmin.getGroupId();
            list = reportService.queryByGroupId(groupId, startIndex, pageSize);
        } else {
            //管理员或最高管理员，默认查询所有分组最新四周
            list = reportService.queryAll(startIndex, pageSize);
        }
        for (GatherBean gather : list) {
            UserBean user = userService.queryUserByQQ(gather.getQQ());
            Boolean aProtected = WeekNumUtils.isProtected(user.getCreateTime());
            gather.setIsUnderProtected(WeekNumUtils.isProtected(user.getCreateTime()) ? 1 : 0);
            gather.setId(user.getId());
            /**
             * 根据userId和最大周数查找这最新四周的周报状态
             */
            List<ReportBean> reportBeans = reportService.queryReportStatus(user.getId(), maxWeekNum);
            System.out.println(reportBeans.size());
            System.out.println(reportBeans);
            //要填充的周报状态map
            Map<Integer, Integer> report4StatusMap = new HashMap<>();

            report4StatusMap.put(maxWeekNum - 3, reportBeans.get(0).getStatus());
            report4StatusMap.put(maxWeekNum - 2, reportBeans.get(1).getStatus());
            report4StatusMap.put(maxWeekNum - 1, reportBeans.get(2).getStatus());
            report4StatusMap.put(maxWeekNum, reportBeans.get(3).getStatus());

            gather.setReport4StatusMap(report4StatusMap);

            pageBean.getGathers().add(gather);
        }
        Integer totalSize = list.size();
        //当前页第一页
        pageBean.setCurrPage(currPage);
        //总记录数为查询得到
        pageBean.setTotalSize(totalSize);
        //每页固定显式10条,limit (start,10)
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage((int) Math.ceil((double) totalSize / pageSize));

        return pageBean;

    }

}
