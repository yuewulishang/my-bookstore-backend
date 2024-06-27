package com.example.backend.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 允许所有来源的跨域请求
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // 如果是预检请求，立即返回
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String path = httpRequest.getRequestURI();

        // 允许访问登录和注册路径
        if (path.startsWith("/api/login") || path.startsWith("/api/register")) {
            chain.doFilter(request, response);
            return;
        }

        // 检查会话中是否有用户信息
        if (httpRequest.getSession().getAttribute("user") == null) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 返回401状态码而不是重定向
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 销毁过滤器
    }
}
