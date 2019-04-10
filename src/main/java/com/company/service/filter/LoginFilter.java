package com.company.service.filter;

import com.company.model.User;
import com.company.utils.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter  implements Filter{
    private static final Logger logger = LogManager.getLogger(LoginFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            logger.info("User tried to reach " + request.getRequestURI() + " without being logged in! Redirected to login page!");
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
//            dispatcher.forward(request, response);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.login"));
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
        }


    @Override
    public void destroy() {

    }
}
