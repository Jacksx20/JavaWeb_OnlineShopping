
package controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import javax.servlet.annotation.MultipartConfig;
import model.DBUtils;
import model.Order;

@MultipartConfig
@WebServlet(name = "SelectServlet", urlPatterns = {"/SelectServlet"})
public class SelectServlet extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try (Connection cn=DBUtils.connect();
            PreparedStatement ps=cn.prepareStatement("select * from orders where owner=?");){//预处理SQL语句
            ps.setString(1, request.getParameter("owner"));//提取owner的值并填入SQL语句中
            try (ResultSet rs=ps.executeQuery()) {////上传SQL语句进行查询
                List<Order> orders=new ArrayList<Order>();//进行数据排序
                for(;rs.next();) {//取出数据加入ORDer
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
                request.getSession().setAttribute("orders", orders);//为orders赋值
                request.getRequestDispatcher("/adm/select.jsp").forward(request, response);//跳转到此页面
                
                
            }
            
        }catch(SQLException se) {
            se.printStackTrace();
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
