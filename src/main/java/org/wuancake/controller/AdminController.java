package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.PageBean;
import org.wuancake.service.IAdminService;
import org.wuancake.service.IReportService;
import org.wuancake.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController extends FatherOfController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IReportService reportService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login")
    String login(AdminBean admin, HttpServletRequest request, HttpServletResponse response) {
        List<Object> sessionList = new ArrayList<>();

        //获取邮箱和密码,前端校验非空
        String email = admin.getEmail();
        String password = admin.getPassword();

        //后台校验非空
        if (email.equals("") || password.equals("")) {
            request.getSession().setAttribute("msg", "邮箱或密码不能为空");
            return "index";
        }
        //验证
        AdminBean isAdmin = adminService.findAdminByEmailAndPassword(email, password);

        if (isAdmin == null) {
            request.getSession().setAttribute("msg", "邮箱或密码错误");
            return "index";
        }
        //session放入admin
        sessionList.add(isAdmin);
        /**
         * 设置默认的分页查询相关
         */
        //currPage此字段在分页查询时为变动的
        Integer currPage = 1;
        PageBean pageBean = pageQuery(currPage, request, isAdmin);
        //session放入PageBean
        sessionList.add(pageBean);

        //将分页查询的PageBean和isAdmin的List放入session中
        request.getSession().setAttribute("sessionList", sessionList);
        return "main";
    }

}
