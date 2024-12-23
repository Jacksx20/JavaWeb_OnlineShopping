

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/cart.css">
        <script src="js/cart.js" defer></script>
    </head>
    <body>
        <div>查看和编辑购物车</div>
        <table border="1">
            <caption id="tot">总金额：<f:formatNumber value="${empty sessionScope.cart.total? 0.0 : sessionScope.cart.total}" type="currency" /></caption><!--//利用sessionScope获取的值并放到总金额处-->
            <caption>
                <a href="index.jsp"><input type="button" value="继续浏览"></a>
                <a href="adm/ordering.jsp"><input type="button" value="下单支付"></a>
            </caption>
            <tr>
                <th>商品名称</th>
                <th>品牌型号</th>
                <th>性能参数</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            <c:forEach items="${sessionScope.cart.wares}" var="w"><!--//用forEach列出sessionScope.cart.wares的每个元素并挨个输出-->
            <tr>
                <td data-code="${w.code}">${w.title}</td>
                <td>${w.model}</td>
                <td>${w.depict}</td>
                <td><f:formatNumber value="${w.price}" type="currency"/></td>
                <td><input type="number" value="${w.amount}"></td>
                <td><f:formatNumber type="currency" value="${w.money}"/></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
