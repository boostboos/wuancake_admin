package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.GatherBean;
import org.wuancake.entity.PageBean;
import org.wuancake.entity.UserBean;
import org.wuancake.service.IReportService;
import org.wuancake.service.IUserService;
import org.wuancake.utils.WeekNumUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 可能这些Controller会有一些重复的代码，都放它们父类
 */
@Controller
public class FatherOfController {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IUserService userService;

    public @ResponseBody
    PageBean pageQuery(Integer currPage, HttpServletRequest request, AdminBean isAdmin) {

        //如果是null就从session中获取
        try {
            isAdmin = (AdminBean) ((ArrayList<Object>) (request.getSession().getAttribute("sessionList"))).get(0);
        } catch (Exception e) {
            //会话过期

        }

        Integer auth = isAdmin.getAuth();

        PageBean pageBean = new PageBean();
        pageBean.setCurrPage(currPage);
        Integer pageSize = 10;
        Integer startIndex = (currPage - 1) * pageSize;
        //获取最新的周数
        Integer maxWeekNum = WeekNumUtils.getMaxWeekNum();

        //设置考勤汇总相关的list
        List<GatherBean> list;
        if (auth == 1) {
            //导师，只分页查询负责的分组,默认当前组最新四周
            Integer group_id = isAdmin.getGroup_id();
            list = reportService.queryByGroupId(group_id, startIndex, pageSize);
        } else {
            //管理员或最高管理员，默认查询所有分组最新四周
            list = reportService.queryAll(startIndex, pageSize);
        }

        for (GatherBean gather : list) {
            UserBean user = userService.queryUserByQQ(gather.getQQ());
            gather.setIsUnderProtected(WeekNumUtils.isProtected(user.getCreate_time()) ? 1 : 0);

            /**
             * 根据userId和最大周数查找这最新四周的周报状态
             */
            List<Integer> statusList = reportService.queryReportStatus(user.getId(), maxWeekNum);

            Map<Integer, Integer> report4StatusMap = new HashMap<>();
            report4StatusMap.put(maxWeekNum - 3, statusList.get(0));
            report4StatusMap.put(maxWeekNum - 2, statusList.get(1));
            report4StatusMap.put(maxWeekNum - 1, statusList.get(2));
            report4StatusMap.put(maxWeekNum, statusList.get(3));

            gather.setReport4StatusMap(report4StatusMap);
        }

        Integer totalSize = list.size();
        //当前页第一页
        pageBean.setCurrPage(currPage);
        //总记录数为查询得到
        pageBean.setTotalSize(totalSize);
        //每页固定显式10条,limit (start,10)
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage((int) Math.ceil((double) totalSize / pageSize));
        //设置考勤汇总Bean
        pageBean.setGathers(list);

        return pageBean;

    }

}
