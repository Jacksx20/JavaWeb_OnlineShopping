
package controller;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet(name = "TimeServlet", urlPatterns = {"/TimeServlet"})
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try (Connection cn=DBUtils.connect();//建立链接
            PreparedStatement ps=cn.prepareStatement("select * from orders where date=?");){//预处理SQL语句
            ps.setString(1, request.getParameter("date"));//取出date的数据加入ps中
            try (ResultSet rs=ps.executeQuery()) {//上传SQL语句进行查询
                
                List<Order> orders=new ArrayList<Order>();//对数据进行排序
                for(;rs.next();) {
                    Order order = new Order();
                    order.setCode(rs.getLong("code"));
                    order.setOwner(rs.getString("owner"));
                    order.setDate(rs.getString("date"));
                    order.setTotal(rs.getDouble("total"));
                    order.setConsignee(rs.getString("consignee"));
                    order.setAddress(rs.getString("address"));
                    order.setPhone(rs.getString("phone"));
                    orders.add(order);
   
                }
                request.getSession().setAttribute("orders", orders);
                request.getRequestDispatcher("/adm/select.jsp").forward(request, response);//给这个网页传递数据
                
                
            }
            
        }catch(SQLException se) {
            se.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
