
package controller;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.Cart;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("cart", new Cart());//为cart提交数据
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }
}
