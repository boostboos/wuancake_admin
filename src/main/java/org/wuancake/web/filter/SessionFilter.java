package org.wuancake.web.filter;

/**
 * @author Ericheel
 * @Description: 会话过滤
 * @date 2018/6/1210:04
 */

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionFilter implements Filter {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig) {
        patterns.add(Pattern.compile(""));
        patterns.add(Pattern.compile("/"));
        patterns.add(Pattern.compile("/index.jsp"));
        patterns.add(Pattern.compile("/login"));
        patterns.add(Pattern.compile(".*[(\\.js)||(\\.css)||(\\.png)]"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        if (isInclude(url)) {
            httpRequest.getSession().removeAttribute("msg");
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("isAdmin") != null) {
                // session存在
                chain.doFilter(httpRequest, httpResponse);
                return;
            } else {
                httpRequest.getSession().setAttribute("msg", "请先登录");
                httpRequest.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 是否需要过滤
     *
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}
