package org.wuancake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Ericheel
 * @Description: 周报汇总相关
 * @date 2018/5/2323:19
 */
@Controller
public class GatherController extends SuperController {

    @RequestMapping(value = "queryGatherList/**")
    String queryGatherList(HttpServletRequest request, Integer currPage) throws ExecutionException, InterruptedException {
        String queryString = request.getQueryString().replace("%20", "").substring(9);

        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");

        Future<PageBean> pageBeanFuture = pageQuery(Integer.parseInt(queryString), null, null, request, isAdmin);
        PageBean pageBean = pageBeanFuture.get();

        //pageBean放入会话
        request.getSession().setAttribute("pageBean", pageBean);
        return "main";
    }

    @RequestMapping(value = "queryGatherListByGroupAndWeek/**")
    String queryGatherListByGroupAndWeek(HttpServletRequest request, Integer currPage, Integer weekNum, Integer groups) throws ExecutionException, InterruptedException {
        HttpSession session = request.getSession();
        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");
        String queryString = request.getQueryString().replace("%20", "").substring(9);
        //如果是第一页
        if ((Integer.parseInt(queryString)) == 1) {
            //并且又重选了查询条件
            if (weekNum != null || groups != null) {
                //销毁session中的groups和weekNum
                session.removeAttribute("groups");
                session.removeAttribute("weekNum");
            }
        }
        Future<PageBean> pageBeanFuture = null;
        if (isAdmin.getAuth() == 1) {
            pageBeanFuture = pageQuery(Integer.parseInt(queryString), weekNum, null, request, isAdmin);
        } else {
            //管理员或超级管理员
            if (session.getAttribute("groups") != null || session.getAttribute("weekNum") != null) {
                //session中weekNum和groups未销毁，说明正在按照条件进行分页查询
                pageBeanFuture = pageQuery(Integer.parseInt(queryString), (Integer) session.getAttribute("weekNum"), (Integer) session.getAttribute("groups"), request, isAdmin);
            } else {
                pageBeanFuture = pageQuery(Integer.parseInt(queryString), weekNum, groups, request, isAdmin);
                session.setAttribute("groups", groups);
                session.setAttribute("weekNum", weekNum);
            }
        }
        PageBean pageBean = pageBeanFuture.get();
        //pageBean放入会话
        session.setAttribute("pageBean", pageBean);
        return "mainByCondition";
    }
}
