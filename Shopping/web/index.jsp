<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>电子商务网站开发实践</title>
        <link rel="stylesheet" href="css/index.css">
    </head>
    <body>
        <section>
            <header>
                <c:choose>
                <c:when test="${empty sessionScope.client}">
                    <a href="regist.jsp">注册</a>&nbsp;&nbsp;
                    <a href="login.jsp">登录</a>
                </c:when>
                <c:otherwise>
                    欢迎${sessionScope.client.title}归来！
                </c:otherwise>    
                </c:choose>
                <a href="cart.jsp"><img src="images/cart.jpg" alt="" title="我的购物车"></a>
            </header>
            <nav>
                <h3>商品类别</h3>
                <c:forEach items="${applicationScope.types}" var="type">
                <a href='wareSet?code=${type.code}&title=${type.title}'>${type.title}</a>
                </c:forEach>
                <a href="adm/manage.jsp">进入后台管理</a>
            </nav>
            <main>
                <address>${sessionScope.address}</address>
                <c:forEach items="${sessionScope.wares}" var="entry"><!--//获得数据并挨个输出-->
                <div>
                    <a href="wareDetails?wareId=${entry.key}"><img src="images/${entry.value.photo}.jpg" alt="" width="125"></a>
                    <b>商品名称：</b>${entry.value.title}
                    <b>品牌：</b>${entry.value.model}
                    <b>性能参数：</b>${entry.value.depict}
                    <b>单价：</b><fmt:formatNumber value="${entry.value.price}" type="currency"/>
                </div>
                </c:forEach>
            </main>
        </section>
    </body>
</html>
