<%@ page import="cn.itheima.pojo.TbUser" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/26
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    TbUser user = (TbUser)session.getAttribute("user");
    if(null==user){
        response.sendRedirect("../page/login.jsp");
        return;
    }
    System.out.println(user.getUserId()+"bbb");
    System.out.println("aaa");


%>
</body>
</html>
