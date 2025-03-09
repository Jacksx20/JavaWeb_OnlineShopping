/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import model.DBUtils;
/**
 *
 * @author Jack20
 */
@WebServlet(name = "ExistedWareServlet", urlPatterns = {"/existedWare"})
public class ExistedWareServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String put="({})",tp=request.getParameter("tp"),cd=request.getParameter("cd");
        try (Connection cn=DBUtils.connect();
             PreparedStatement ps=cn.prepareStatement("select * from wares where type=? and code=?");) {
            ps.setInt(1, Integer.parseInt(tp));
            ps.setString(2, cd);
            try (ResultSet rs=ps.executeQuery()) {
                // 需要添加org.json依赖
                JSONObject json = new JSONObject();
                if(rs.next()) {
                    json.put("title", rs.getString("title").trim());
                    json.put("model", rs.getString("model").trim());
                    json.put("depict", rs.getString("depict").trim());
                    json.put("price", rs.getDouble("price"));
                }
                out.print(json.toString());
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(put);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
