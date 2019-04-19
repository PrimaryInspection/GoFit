package com.company.servlet;


import com.company.service.IPageService;
import com.company.service.command.ActionCommand;
import com.company.service.command.factory.ActionFactory;
import com.company.service.factory.ServiceFactory;
import com.company.utils.ConfigurationManager;
import com.company.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    private IPageService pageService = ServiceFactory.getPageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request,response);
    }

    protected  void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request , response);
        logger.info("Obtained command is :" + command.getClass().getSimpleName());

        page = command.execute(request , response);

        if (page != null){
            if(pageService.isRedirect()){
                logger.info("Request redirected to page: " + page.toString());
                pageService.setRedirect(false);
                response.sendRedirect(page);
            }else {
                logger.info("Request will be forwarded to " + page );
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request,response);
            }
        }
        else {
            page = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("nullPage" , MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }


}
