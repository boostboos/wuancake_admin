package org.wuancake.controller;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wuancake.dao.GroupMapper;
import org.wuancake.dao.ReportMapper;
import org.wuancake.dao.UserMapper;
import org.wuancake.entity.*;
import org.wuancake.service.IAdminService;
import org.wuancake.service.IUserService;
import org.wuancake.utils.MD5Utils;

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
        if (admin == null) {
            request.getSession().setAttribute("msg", "登录失败");
            return "index";
        }
        String email = admin.getEmail().replace(" ", "");
        String password = admin.getPassword().replace(" ", "");

        //校验非空
        if ("".equals(email) || "".equals(password)) {
            request.getSession().setAttribute("msg", "邮箱或密码不能为空");
            return "index";
        }

        //验证
        AdminBean isAdmin = adminService.findAdminByEmail(email);
        if (isAdmin == null) {
            request.getSession().setAttribute("msg", "邮箱错误");
            return "index";
        }
        if (!MD5Utils.verify(password, isAdmin.getPassword())) {
            request.getSession().setAttribute("msg", "密码错误");
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

        String email = tutorBean.getEmail();
        String password = tutorBean.getPassword();
        String username = tutorBean.getUsername();
        Integer groupId = tutorBean.getGroupId();
        if (email == null || password == null || username == null || groupId == null) {
            request.getSession().setAttribute("authBadInfo", "字段不能为null");
            return "addTutor";
        }
        if (email.contains(" ") || password.contains(" ") || username.contains(" ")) {
            request.getSession().setAttribute("authBadInfo", "不要有空格");
            return "addTutor";
        }

        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");

        if (isAdmin == null || isAdmin.getAuth() == 1) {
            //校验权限
            request.getSession().setAttribute("authBadInfo", "权限不足");
        } else {
            //检查是否存在
            TutorBean bean = adminService.findTutorByEmail(email);
            if (bean != null) {
                request.getSession().setAttribute("authBadInfo", "邮箱已经存在，添加失败");
                return "addTutor";
            }
            //添加
            tutorBean.setPassword(MD5Utils.generate(password));
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
        String username = adminBean.getUsername();
        String password = adminBean.getPassword();
        String email = adminBean.getEmail();
        if (isAdmin == null) {
            request.getSession().setAttribute("msg", "请登录");
            return "index";
        }
        if (email == null || password == null || username == null) {
            request.getSession().setAttribute("authBadInfo", "字段不能为null");
            return "addAdmin";
        }
        if (username.contains(" ") || password.contains(" ") || email.contains(" ")) {
            request.getSession().setAttribute("authBadInfo", "不要有空格");
            return "addAdmin";
        }


        if (isAdmin.getAuth() != 3) {
            //校验权限
            request.getSession().setAttribute("authBadInfo", "权限不足");
        } else {
            //检查是否存在
            TutorBean bean = adminService.findTutorByEmail(email);
            if (bean != null) {
                request.getSession().setAttribute("authBadInfo", "邮箱已经存在，添加失败");
                return "addAdmin";
            }
            //添加
            adminBean.setPassword(MD5Utils.generate(password));
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

    @Value("${customConfig.pageSize}")
    private Integer pageSize;

    @RequestMapping(value = "queryKickList/**")
    String hitMan47(HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        int currPage = Integer.parseInt(request.getQueryString().replace("%20", "").substring(9));

        Integer startIndex = (currPage - 1) * pageSize;

        int totalSize = adminService.queryAllUserNumBeKicked();

        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalSize(totalSize);
        pageBean.setTotalPage((int) Math.ceil((double) totalSize / pageSize));

        List<KickBean> list = adminService.queryUserListBeKicked(startIndex, pageSize);
        pageBean.setKickBeanList(list);
        request.getSession().setAttribute("pageBean", pageBean);
        return "hitman";
    }

    @RequestMapping(value = "resetPwd")
    String resetPwd(HttpServletRequest request, String oldPwd, String newPwd) {
        if (newPwd.contains(" ")) {
            request.getSession().setAttribute("authBadInfo", "新密码不要含有空格");
            return "resetPwd";
        }

        request.getSession().removeAttribute("authGoodInfo");
        request.getSession().removeAttribute("authBadInfo");
        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");
        boolean verify = MD5Utils.verify(oldPwd, isAdmin.getPassword());
        if (!verify) {
            request.getSession().setAttribute("authBadInfo", "原密码错误");
            return "resetPwd";
        } else {
            adminService.updatePwd(isAdmin.getId(), MD5Utils.generate(newPwd));
            request.getSession().setAttribute("authGoodInfo", "修改成功，下次登录记得用新密码");
            return "resetPwd";
        }
    }

}
