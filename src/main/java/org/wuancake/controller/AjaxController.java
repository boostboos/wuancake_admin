package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wuancake.entity.*;
import org.wuancake.service.IGroupService;
import org.wuancake.service.IReportService;
import org.wuancake.service.IUserService;
import org.wuancake.utils.WeekNumUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 专门接收ajax请求的
 *
 * @author
 * @date
 */
@Controller
public class AjaxController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReportService reportService;

    @RequestMapping(value = "showGroup")
    @ResponseBody
    List<GroupBean> showGroup(HttpServletResponse response) {
        List<GroupBean> groups = groupService.showGroup();
        return groups;
    }

    @RequestMapping(value = "showWeekNum")
    @ResponseBody
    Integer showWeekNum(HttpServletResponse response) {
        return WeekNumUtils.getMaxWeekNum();
    }

    @RequestMapping(value = "searchInfoByQQ")
    @ResponseBody
    SimpleInfoByQQ searchInfoByQQ(String qq) {
        SimpleInfoByQQ simpleInfoByQQ = userService.searchInfoByQQ(qq);
        if (simpleInfoByQQ == null) {
            SimpleInfoByQQ sb = new SimpleInfoByQQ();
            sb.setQQ("aptx4869");
            return sb;
        } else {
            return simpleInfoByQQ;
        }
    }


}
