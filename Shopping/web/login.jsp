<%-- 
    Document   : login
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            div {
                background-color: skyblue;
                text-align: center;
                margin-bottom: 20px;
            }
            form {
                display: grid;
                grid: auto-flow/auto auto;
                place-content: center;
                gap: 5px;
            }
            span {
                grid-area: auto/2;
                place-self: start end;
                font-size: 9pt;
                margin-bottom: 8px;
            }
            [type=submit] {
                grid-area: auto/span 2;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div>客户登陆</div>
        <form action="login" method="post">
            账号 <input name="acc">
            <span>没有账号？请<a href="regist.jsp">注册</a></span>
            密码 <input type="password" name="pw">
            <span style="color:red">${requestScope.err}<input type="submit"></span>
        </form>
    </body>
</html>
