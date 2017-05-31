package com.shark.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class GlobalResourceInit implements ServletContextListener {
    public GlobalResourceInit() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	Set<Integer> loginId = new HashSet<Integer>();
    	application.setAttribute("logedId", loginId);
    }
	
}
