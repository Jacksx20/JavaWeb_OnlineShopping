package controller;

import javax.servlet.*;
import model.*;

public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app= sce.getServletContext();
        DBUtils.setUrl(app.getInitParameter("dburl"));
        app.setAttribute("types", DBUtils.queryTypes()); 
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
