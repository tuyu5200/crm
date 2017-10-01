package com.crm.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Component
public class SecurityFilter implements Filter {

    private FilterConfig config;

    public boolean isContains(String container, String[] regx) {
        boolean result = false;

        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse responseWrapper = (HttpServletResponse) servletResponse;
        String loginStrings = this.config.getInitParameter("loginStrings");  //获取登录资源
        String includeStrings = this.config.getInitParameter("includeStrings");  //要过滤的资源的后缀参数
        String redirectPath = request.getContextPath() + this.config.getInitParameter("redirectPath"); //没有登陆转向页面
        String disableFilter = this.config.getInitParameter("disableFilter"); //过滤器是否有效
        //不过滤
        if (disableFilter.toUpperCase().equals("TRUE")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String[] loginList = loginStrings.split(";");
        String[] includeList = includeStrings.split(";");
        //对指定的参数进行过滤
        if (!this.isContains(request.getRequestURI(), includeList)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //不过滤登录页面
        if (this.isContains(request.getRequestURI(), loginList)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
//        判断用户是否登录
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            responseWrapper.sendRedirect("/login.jsp");
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
    }

    @Override
    public void destroy() {
        this.config = null;
    }
}
