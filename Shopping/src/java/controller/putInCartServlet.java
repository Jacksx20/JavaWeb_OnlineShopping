
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

//购物车
@WebServlet(name = "putIntoCartServlet", urlPatterns = {"/putInCart"})
public class putInCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Ware w= (Ware)session.getAttribute("ware");//取出数据
        if(null==w) return;//如果w为空停止
        Ware ware=w.cloneWare();
        Cart cart=(Cart)session.getAttribute("cart");
        cart.putWare(ware);
        response.sendRedirect("cart.jsp");//转到改网页
    }

    @Override
    public String getServletInfo() {
        return "把商品放进购物车";
    }

}
