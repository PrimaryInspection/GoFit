package com.company.controller.command.impl.user;

import com.company.controller.command.impl.ActionCommand;
import com.company.model.entity.User;
import com.company.model.service.ILoginRegistrationService;
import com.company.model.service.IUserService;
import com.company.model.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.company.model.utils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class RegisterNewUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegisterNewUserCommand.class);

    private static final String PARAM_NAME_FIRST_NAME = "first_name";
    private static final String PARAM_NAME_SECOND_NAME = "second_name";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD_CONFIRM = "password_confirmation";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_GOAL_WEIGHT = "weightGoal";
    private static final String PARAM_NAME_HEIGHT = "height";
    private static final String PARAM_NAME_LIFESTYLE = "lifestyle";

    private IUserService userService = ServiceFactory.getUserService();
    private ILoginRegistrationService loginRegistrationService = ServiceFactory.getLoginRegistrationService();

    private HttpSession session;

    /**
     * Registration of new user
     * @return page path
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page =
                ConfigurationManager.getProperty("path.page.registration");
        session=request.getSession();

        User newUser = getUserFromRequest(request);
        request.setAttribute("registrationUser" , newUser);
        logger.debug("!!! New user:" + newUser);
        String password_confirm = request.getParameter(PARAM_NAME_PASSWORD_CONFIRM);

        if(!loginRegistrationService.confirmPassword(newUser.getPassword() , password_confirm)){
            request.setAttribute("errorPassConfirmMessge" , MessageManager.getProperty("message.passconfirmerror"));
            return page;
        }
        logger.debug("Password confirmation is OK.");

        if(loginRegistrationService.checkLoginExist(newUser.getLogin())){
            request.setAttribute("errorLoginExistMessage" , MessageManager.getProperty("message.loginexisterror"));
            return page;
        }
        logger.debug("Exist loggin checking is OK");

        if(userService.addUser(newUser)){
        request.setAttribute("registrationSuccessMessage",MessageManager.getProperty("message.registrationconfirm"));
        page = ConfigurationManager.getProperty("path.page.login");
        }else{
            request.setAttribute("errorRegistrationMessage" , MessageManager.getProperty("message.registrationerror"));
        }
        return page;
    }

    /**
     * Getting user's parameters from request
     * @return User user
     */
    private User getUserFromRequest(HttpServletRequest request){
        User user = new User(
                request.getParameter(PARAM_NAME_FIRST_NAME),
                request.getParameter(PARAM_NAME_SECOND_NAME),
                request.getParameter(PARAM_NAME_LOGIN),
                request.getParameter(PARAM_NAME_PASSWORD),
                request.getParameter(PARAM_NAME_EMAIL),
                LocalDate.parse(request.getParameter(PARAM_NAME_BIRTHDAY)),
                Integer.parseInt(request.getParameter(PARAM_NAME_WEIGHT)),
                Integer.parseInt(request.getParameter(PARAM_NAME_GOAL_WEIGHT)),
                Integer.parseInt(request.getParameter(PARAM_NAME_HEIGHT)),
                Integer.parseInt(request.getParameter(PARAM_NAME_LIFESTYLE))
        );
        return user;
    }
}
