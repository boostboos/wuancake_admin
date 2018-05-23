package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wuancake.entity.AdminBean;
import org.wuancake.entity.PageBean;
import org.wuancake.service.IAdminService;
import org.wuancake.service.IReportService;
import org.wuancake.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * admin相关控制层类
 *
 * @author
 * @date
 */
@Controller
public class AdminController extends SuperController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login")
    String login(AdminBean admin, HttpServletRequest request, HttpServletResponse response) {

        String email = admin.getEmail();
        String password = admin.getPassword();

        //校验非空
        if ("".equals(email) || "".equals(password)) {
            request.getSession().setAttribute("msg", "邮箱或密码不能为空");
            return "index";
        }
        //验证
        AdminBean isAdmin = adminService.findAdminByEmailAndPassword(email, password);

        if (isAdmin == null) {
            request.getSession().setAttribute("msg", "邮箱或密码错误");
            return "index";
        }
        PageBean pageBean = pageQuery(1, request, isAdmin);
        //放入会话
        request.getSession().setAttribute("isAdmin", isAdmin);
        request.getSession().setAttribute("pageBean", pageBean);

        return "main";
    }


    @RequestMapping(value = "/removeSb")
    String removeSomeBody(Integer user_id, Integer currPage, RedirectAttributes redirectAttributes) {
        userService.removeByUserId(user_id);
        redirectAttributes.addAttribute("currPage", currPage);
        return "redirect:queryGatherList";
    }
}
