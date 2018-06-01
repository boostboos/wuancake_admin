package org.wuancake.web.filter;

import org.springframework.stereotype.Component;
import org.wuancake.entity.AdminBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ericheel
 * @Description: 登录相关的过滤器
 * @date 2018/5/248:39
 */
@Component
public class LoginFilter implements Filter {

    private static final String EXCLUDE_PAGE = "/index.jsp,/login,/,''";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String requestURL = request.getServletPath();
//        String[] splitURL = EXCLUDE_PAGE.split(",");
//
//        for (String url : splitURL) {
//            if (url.equals(requestURL)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//        }
//
//        AdminBean isAdmin = (AdminBean) request.getSession().getAttribute("isAdmin");
//        if (isAdmin == null) {
//            response.sendRedirect(request.getContextPath() + "index.jsp");
//        } else {
            filterChain.doFilter(request, response);
//        }
    }


    @Override
    public void destroy() {
    }
}
