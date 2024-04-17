
package controller;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import model.*;
//注册
@WebServlet(name = "RegistServlet", urlPatterns = {"/regist"})
public class RegistServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");//确认数据类型与编码方式
        try(    Connection cn= DBUtils.connect();
                PreparedStatement ps= cn.prepareStatement("insert clients values(?,?,?,?,?,?,0)")) {//预处理SQL语句
            ps.setString(1, request.getParameter("code"));
            ps.setString(2, request.getParameter("title"));
            ps.setString(3, request.getParameter("phone"));
            ps.setString(4, request.getParameter("wechat"));
            ps.setString(5, request.getParameter("address"));
            ps.setString(6, request.getParameter("pw1"));//填入数据
            ps.executeUpdate();//上传SQL语句进行查询
            response.sendRedirect("login.jsp");//跳转到登陆界面
        }catch(SQLException se) {
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
