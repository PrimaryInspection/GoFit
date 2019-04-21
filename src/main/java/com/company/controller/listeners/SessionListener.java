package com.company.controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionListener.class);


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.debug("Session created");
    }

    /**
     * Remove user from session, after session's destruction
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug("Session destroyed");
        se.getSession().removeAttribute("user");
    }
}
