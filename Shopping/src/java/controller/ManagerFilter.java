/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import model.*;

/**
 *
 * @author Jack20
 */
@WebFilter(filterName = "ManagerFilter", urlPatterns = { "/" })
public class ManagerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Client usr = (Client) (req.getSession().getAttribute("client"));
        String uri = req.getRequestURI(), file = req.getServletPath();
        if (null == usr || !(usr.isManager() || "/adm/ordering.jsp".equals(file))) {
            req.getSession().setAttribute("aim", uri);
            res.sendRedirect("../login.jsp");
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
