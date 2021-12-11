
<%@page import="cn.zly.servlet.Management"%>
<%@page import="java.util.Date"%>
<%@page import="cn.itheima.pojo.TbUser"%>
<%@page import="cn.itheima.pojo.TbBook"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>订单打印</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- page style-->
  <style>
  .my_itemtitle{
    font-size: 20px;
  }
  .my_info{
    width: 50% !important;
  }
  </style>
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../css/font-awesome/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../css/Ionicons/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../css/AdminLTE/AdminLTE.min.css">
  <!-- Google Font -->
  <!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> -->
</head>
          <% TbUser user=(TbUser)session.getAttribute("management");
 String date =new Management().current_time();
	
%>
<body onload="window.print();">
  <div class="wrapper">
    <!-- Main content -->
    <section class="invoice">
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
            <i class="fa"></i>订单详情
      
            <small class="pull-right">日期:<%=date %></small>
          </h2>
      
        </div>
        <!-- /.col -->
      </div>
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-4 invoice-col my_info">
            <b class="my_itemtitle">收件人</b><br>
            <br>
            <b>姓名：</b>${address.receiver}<br>
            <b>地址：</b>${address.address}<br>
            <b>电话：</b>${address.receiverPhone}<br>
        </div>
        <!-- /.col -->
        <div class="col-sm-4 invoice-col my_info">
          <b class="my_itemtitle">${order.orderId}</b><br>
          <br>
          <b>创建时间：</b>${order.placed}<br>
          <b>订单总额：</b>￥${order.payment}<br>
          <b>订单状态：</b>
<c:choose>
           <c:when test="${order.sta==0}">未处理</c:when>
           <c:when test="${order.sta==1}">处理中</c:when>
           <c:when test="${order.sta==2}">已发货</c:when>
           <c:when test="${order.sta==3}">已交付</c:when>
          
         
          
          </c:choose>

        </div>
        <!-- /.col -->
      </div>
      <hr>
      <!-- /.row -->
      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>数量</th>
              <th>名称</th>
              <th>商品编号</th>
              <th>商品描述</th>
              <th>单价</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book" varStatus="loop">
            <tr>
              <td>${orderItems[loop.count-1].count}</td>
              <td>${book.title}</td>
              <td>${book.isbn}</td>
              <td></td>
              <td>￥${book.price}</td>
               </tr>
              </c:forEach>
           
            </tbody>
          </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- ./wrapper -->
</body>
</html>
