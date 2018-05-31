package org.wuancake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.PageBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ericheel
 * @Description: 周报汇总相关
 * @date 2018/5/2323:19
 */
@Controller
public class GatherController extends SuperController {

    @RequestMapping(value = "queryGatherList/**")
    String queryGatherList(HttpServletRequest request, Integer currPage) {

        String queryString = request.getQueryString().replace("%20", "").substring(9);

        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");

        PageBean pageBean = pageQuery(Integer.parseInt(queryString), null, request, isAdmin);

        //pageBean放入会话
        request.getSession().setAttribute("pageBean", pageBean);
        return "main";
    }

    @RequestMapping(value = "queryGatherListByGroupAndWeek/**")
    String queryGatherListByGroupAndWeek(HttpServletRequest request, Integer currPage) {
        String queryString = request.getQueryString().replace("%20", "").substring(9);

        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");
        //选择要查看的分组（管理员以上才有这个）
        String subGroup = (String) request.getSession().getAttribute("subGroup");
        //选择要查看的截至周数 （导师以上都可以）
        String subWeek = (String) request.getSession().getAttribute("subWeek");

        PageBean pageBean = pageQuery(Integer.parseInt(queryString), null, request, isAdmin);

        //pageBean放入会话
        request.getSession().setAttribute("pageBean", pageBean);
        return "mainByGroupAndWeek";
    }
}
