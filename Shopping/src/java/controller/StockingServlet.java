
package controller;

import java.io.*;
import java.nio.file.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;
import model.*;


@MultipartConfig
@WebServlet(name = "StockingServlet", urlPatterns = {"/stocking"})
public class StockingServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=utf-8");//确认数据类型与编码方式
        request.setCharacterEncoding("utf-8");
        String title=request.getParameter("title"), sql, code=request.getParameter("code");//分别提取表单的值
        int k = -1;
        if(null==title) //如果表单中的title为空
            sql="update wares set price=?, amount=amount+? where code=?";
        else
            sql="insert wares values(?,?,?,?,?,?,?,?)";
        
        try (   Connection cn= DBUtils.connect();//建立链接
                PreparedStatement ps=cn.prepareStatement(sql)) {
            if(null==title) {//如果表单中的title为空，把数据填入其数据中
                ps.setDouble(1, Double.parseDouble(request.getParameter("price")));
                ps.setInt(2, Integer.parseInt(request.getParameter("amount")));
                ps.setString(3, request.getParameter("code"));
            }else{
                ps.setString(1, code);
                ps.setString(2, request.getParameter("title"));
                ps.setString(3, request.getParameter("model"));
                ps.setString(4, request.getParameter("depict"));
                ps.setString(5, code);  //用商品号作为图像文件名，确保文件名不重复
                ps.setDouble(6, Double.parseDouble(request.getParameter("price")));
                ps.setInt(7, Integer.parseInt(request.getParameter("amount")));
                ps.setInt(8, Integer.parseInt(request.getParameter("type")));
            }
            ps.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
        }
        Part part=request.getPart("photo");//获取photo的数据
        if(null != part){
        Path target=Paths.get(this.getServletContext().getRealPath(""),"images",code+".jpg");//如果photo不为空修改图片名
        try (   InputStream source=part.getInputStream();//获取POST的数据
                ) {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            
            }
        }
        k = 1;
        try(PrintWriter out=response.getWriter()){
            out.print(k);
        }
    }

}
