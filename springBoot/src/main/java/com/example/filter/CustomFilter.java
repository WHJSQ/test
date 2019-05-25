package com.example.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/27.
 */
@Slf4j
@WebFilter(filterName = "customFilter",urlPatterns = {"/*"})
public class CustomFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            log.info("filter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            log.info("请求处理");
            System.out.print(servletRequest.toString());
            filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
            log.info("销毁请求");
    }
}
