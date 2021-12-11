<%@page import="cn.itheima.pojo.TbUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% session = request.getSession(true);
TbUser mTbUser=(TbUser) session.getAttribute("user");
		System.out.println(mTbUser.getUserId()+mTbUser.getPhone()+"AAA");%>
		<p>mmmm</p>
		
</body>
</html>