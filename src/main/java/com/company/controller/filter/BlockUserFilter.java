package com.company.controller.filter;

import com.company.model.entity.User;
import com.company.model.utils.ConfigurationManager;
import com.company.model.utils.MessageManager;
import com.company.model.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(BlockUserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");

        RequestDispatcher dispatcher =request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
        if(user == null){
            logger.info("User tried to reach " + request.getRequestURI() + "being not logged in. Forwarding to login page.");
            request.setAttribute("userNullMessage" , MessageManager.getProperty("message.usernullerror"));
            dispatcher.forward(request,response);
        }else if(UtilManager.getProperty("status.blocked").equalsIgnoreCase(user.getStatus().toString())){
            logger.info("User tried to reach " + request.getRequestURI() + "having status 'BLOCKED'. Forwarding to login page.");
            request.setAttribute("userBlockMessage" , MessageManager.getProperty("message.userblocked"));
            dispatcher.forward(request,response);
        }else {
            logger.debug("@@@@ TEST IN BLOCK FILTER @@@@ ");
            chain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
