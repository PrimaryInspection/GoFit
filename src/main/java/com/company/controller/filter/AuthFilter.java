package com.company.controller.filter;

import com.company.model.entity.User;
import com.company.model.utils.ConfigurationManager;
import com.company.model.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Filter for user authorization, also checks if the user is admin
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && UtilManager.getProperty("role.admin").equalsIgnoreCase(String.valueOf(user.getRoleId()))) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            logger.info("User tried to reach \" + request.getRequestURI() + " +
                    "\" without being logged in as administrator! Redirected to error page!");
            response.sendRedirect(ConfigurationManager.getProperty("path.page.main"));
        }

    }

    @Override
    public void destroy() {

    }
}
