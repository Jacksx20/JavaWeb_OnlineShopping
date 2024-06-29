
package controller;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.DBUtils;
import model.Order;
// 用于删除数据库中数据的部分
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//用于确保发送给服务器的文件格式
        response.setCharacterEncoding("utf-8");
        try (Connection cn = DBUtils.connect();
                PreparedStatement ps = cn.prepareStatement("delete from orders where code=?");
                ) {
            ps.setString(1, request.getParameter("codeid"));
            ps.executeUpdate();
            response.sendRedirect("adm/manage.jsp");//转到。。页
         
        }catch(SQLException se){}
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
