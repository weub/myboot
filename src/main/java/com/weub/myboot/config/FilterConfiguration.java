package com.weub.myboot.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class FilterConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 假设我们的应用程序运行在一台负载均衡代理服务器后方,
     * 因此需要将代理服务器发来的请求包含的IP地址转换成真正的用户IP,
     * Tomcat 8 提供了对应的过滤器：RemoteIpFilter
     * @return
     */
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    /**
     * SpringBoot特有的注册Filter方法
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("paramName", "paramValue");
        registrationBean.setName("myFilter");
        registrationBean.setOrder(1);
        return  registrationBean;
    }

    /**
     * 自定义过滤器(Filter)
     * 因为应用了JDK8，Filter接口引入了default标识，不需要特地去实现doFilter以外的方法
     */
    private class MyFilter implements Filter {
        @Override
        public void doFilter(ServletRequest servletRequest,
                             ServletResponse servletResponse,
                             FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            logger.info("request from: " + request.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
