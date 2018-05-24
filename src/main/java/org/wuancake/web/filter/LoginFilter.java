package org.wuancake.web.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.wuancake.entity.AdminBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ericheel
 * @Description: 登录相关的过滤器
 * @date 2018/5/248:39
 */
@WebFilter(urlPatterns = "/*")
@Component
@Order(value = 1)
public class LoginFilter implements Filter {

    private static final String EXCLUDE_PAGE = "/index.jsp,/login,/,''";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String[] split = EXCLUDE_PAGE.split(",");
        int count = 0;
        for (String page : split) {
            if (page.equals(request.getServletPath())) {
                count++;
            }
        }
        if (count > 0) {
            filterChain.doFilter(request, response);
            return;
        } else {

            AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");

            if (isAdmin == null) {
                request.getSession().setAttribute("msg", "会话已过期请重新登录");
                response.sendRedirect(request.getContextPath() + "index.jsp");
            } else {
                //放行
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
