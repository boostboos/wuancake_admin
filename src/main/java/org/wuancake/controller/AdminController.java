package org.wuancake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wuancake.dao.ReportMapper;
import org.wuancake.dao.UserMapper;
import org.wuancake.entity.*;
import org.wuancake.service.IAdminService;
import org.wuancake.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReportMapper reportMapper;

    @RequestMapping(value = "/login")
    String login(AdminBean admin, HttpServletRequest request, HttpServletResponse response) throws ExecutionException, InterruptedException {

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

        Future<PageBean> pageBeanFuture = pageQuery(1, null, null, request, isAdmin);
        PageBean pageBean = pageBeanFuture.get();
        request.getSession().setAttribute("pageBean", pageBean);

        //放入会话
        request.getSession().setAttribute("isAdmin", isAdmin);

        return "main";
    }


    @RequestMapping(value = "/removeSb")
    String removeSomeBody(HttpServletRequest request, Integer userId, Integer currPage, RedirectAttributes redirectAttributes) {
        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");
        userService.removeByUserId(userId, isAdmin.getUsername(), new Date());
        redirectAttributes.addAttribute("currPage", currPage);
        return "redirect:queryGatherList";
    }

    @RequestMapping(value = "/removeSbOnCondition")
    String removeSomeBodyOnCondition(HttpServletRequest request, Integer userId, Integer currPage, RedirectAttributes redirectAttributes) {
        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");
        userService.removeByUserId(userId, isAdmin.getUsername(), new Date());
        redirectAttributes.addAttribute("currPage", currPage);

        redirectAttributes.addAttribute("groups", request.getSession().getAttribute("groups"));
        redirectAttributes.addAttribute("weekNum", request.getSession().getAttribute("weekNum"));
        return "redirect:queryGatherListByGroupAndWeek";
    }

    @RequestMapping(value = "addTutor")
    String addTutor(HttpServletRequest request, TutorBean tutorBean) {
        //前端校验字段非空
        request.getSession().removeAttribute("authGoodInfo");
        request.getSession().removeAttribute("authBadInfo");
        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");

        if (isAdmin.getAuth() == 1) {
            //校验权限
            request.getSession().setAttribute("authBadInfo", "权限不足");
        } else {
            //检查是否存在
            TutorBean bean = adminService.findTutorByEmail(tutorBean.getEmail());
            if (bean != null) {
                request.getSession().setAttribute("authBadInfo", "邮箱已经存在，添加失败");
                return "addTutor";
            }
            //添加
            adminService.addTutor(tutorBean);
            request.getSession().setAttribute("authGoodInfo", "添加成功");
        }
        return "addTutor";

    }

    @RequestMapping(value = "addAdmin")
    String addAdmin(HttpServletRequest request, AdminBean adminBean) {
        //前端校验字段非空
        request.getSession().removeAttribute("authGoodInfo");
        request.getSession().removeAttribute("authBadInfo");
        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");

        if (isAdmin.getAuth() != 3) {
            //校验权限
            request.getSession().setAttribute("authBadInfo", "权限不足");
        } else {
            //检查是否存在
            TutorBean bean = adminService.findTutorByEmail(adminBean.getEmail());
            if (bean != null) {
                request.getSession().setAttribute("authBadInfo", "邮箱已经存在，添加失败");
                return "addAdmin";
            }
            //添加
            adminService.addAdmin(adminBean);
            request.getSession().setAttribute("authGoodInfo", "添加成功");
        }
        return "addAdmin";

    }

    @RequestMapping(value = "logout")
    String logOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping(value = "hitman47")
    String hitMan47(HttpServletRequest request) {
        List<UserGroupBean> list = adminService.queryAllUserBeKicked();
        request.getSession().setAttribute("kickList", list);
        System.out.println(list);
        return "hitman47";
    }
}
