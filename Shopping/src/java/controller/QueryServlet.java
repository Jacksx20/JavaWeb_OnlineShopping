
package controller;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.DBUtils;

@MultipartConfig
@WebServlet(name = "QueryServlet", urlPatterns = {"/QueryServlet"})
public class QueryServlet extends HttpServlet {
//订单查询
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String fm="<tr><th><input type='checkbox'></th><td><img src='images/%s.jpg' width='60'></td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%.2f</td><td>%d</td></tr>",//网页的一部分
               tb="";
        try(    Connection cn=DBUtils.connect();//建立联系
                PreparedStatement ps=cn.prepareStatement("select * from wares where type=?")) {
                ps.setInt(1, Integer.parseInt(request.getParameter("type")));
                 ResultSet rs=ps.executeQuery()) {//上传SQL语句进行查询
            while(rs.next()) {
                tb+=String.format(fm, rs.getString("photo"),//构建tb
                        rs.getString("code"),
                        rs.getString("title"),
                        rs.getString("model"),
                        rs.getString("depict"),
                        rs.getDouble("price"),
                        rs.getInt("amount"));
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }
        try(PrintWriter out=response.getWriter()) {
            out.print(tb);//输出tb
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(    Connection cn=DBUtils.connect();
                PreparedStatement ps=cn.prepareStatement("delete wares where code in ("+ request.getParameter("cds")+")")){//预处理SQL语句
            ps.executeUpdate();//上传SQL语句进行查询
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

