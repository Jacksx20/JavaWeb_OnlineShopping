
package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Ware;

/**
 *
 * @author Jack20
 */
@WebServlet(name = "WareDetailsServlet", urlPatterns = {"/wareDetails"})
public class WareDetailsServlet extends HttpServlet {
//商品详细信息
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//确认数据类型与编码方式
        String wareId= request.getParameter("wareId");//取waerID的值
        HttpSession session= request.getSession();
        HashMap<String,Ware> wares= (HashMap<String,Ware>)session.getAttribute("wares");//对数据进行处理
        Ware ware= wares.get(wareId);
        session.setAttribute("ware", ware);//附加值
        request.getRequestDispatcher("wareDetails.jsp").forward(request, response);//给这个网页传递数据
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
