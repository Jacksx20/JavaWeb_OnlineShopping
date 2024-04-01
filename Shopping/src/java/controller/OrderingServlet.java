/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import model.*;

/**
 *
 * @author Jack20
 */
@WebServlet(name = "OrderingServlet", urlPatterns = { "/ordering" })
public class OrderingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String sql = "insert orders(owner,total,consignee,address,phone) values(?,?,?,?,?)";
        Client usr = (Client) session.getAttribute("client");
        Cart cart = (Cart) session.getAttribute("cart");
        long oid;
        try (Connection cn = DBUtils.connect();) {
            try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, usr.getCode());
                ps.setDouble(2, cart.getTotal());
                ps.setString(3, request.getParameter("getter"));
                ps.setString(4, request.getParameter("address"));
                ps.setString(5, request.getParameter("phone"));
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    oid = rs.getLong(1);
                }
            }
            try (CallableStatement cs = cn.prepareCall("{call addDetails(?,?,?,?)}")) {
                for (Ware w : cart.getWares()) {
                    cs.setLong(1, oid);
                    cs.setString(2, w.getCode());
                    cs.setInt(3, w.getAmount());
                    cs.setDouble(4, w.getPrice());
                    cs.addBatch();
                }
                cs.executeBatch();
            }
            request.getSession().invalidate();
            request.getSession(true);
            response.sendRedirect("thanks.jsp");
        } catch (SQLException se) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
