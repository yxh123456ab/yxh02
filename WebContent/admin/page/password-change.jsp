<%@page import="cn.itheima.pojo.TbUser"%>
<%@page import="cn.test.check_user"%>
<%@ page import="cn.itheima.pojo.TbBook" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>修改密码</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- page style -->
  <style>
  .my_content{
    padding: 9% 30%;
    height: 60%;
    /*background: #f00;*/
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
  <!-- AdminLTE Skin -->
  <link rel="stylesheet" href="../css/AdminLTE/skin/skin-blue.min.css">
  <!-- Google Font -->
  <!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> -->
</head>
<body class="hold-transition skin-blue sidebar-mini">

<% TbUser user =(TbUser)session.getAttribute("management"); 
System.out.println(user.getUname());
//System.out.println("12232");
%>
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="index.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>书城</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg">学子书城管理系统</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">导航切换</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <!-- /.messages-menu -->
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="../img/setting.png" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs"><%=user.getUname()%></span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="../img/word.jpg" class="img-circle" alt="User Image">
                <p>让学习成为一种习惯</p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                   <a href="I_dont_know_how_to_name?method=Change_upwd" class="btn btn-default btn-flat">修改密码</a>
                </div>
                <div class="pull-right">
                  <a href="login.jsp" class="btn btn-default btn-flat">退出</a>
                </div>
              </li>
            </ul>
          </li>

        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">&nbsp;</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>订单管理</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="Mangement_others?method=show_all_orders">所有订单</a></li>
            <li><a href="Mangement_others?method=show_Any&sta=0">待处理</a></li>
            <li><a href="Mangement_others?method=show_Any&sta=1">处理中</a></li>
            <li><a href="Mangement_others?method=show_Any&sta=2">已发货</a></li>
            <li><a href="Mangement_others?method=show_Any&sta=3">已交付</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>商品管理</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="bookEdit?method=displayAllBooks">所有商品</a></li>
            <li><a href="product-add.jsp">添加商品</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1><small></small></h1>
      <ol class="breadcrumb">
        <!-- <li><a href="#"><i class="fa fa-dashboard"></i> </a></li> -->
        <!-- <li class="active"></li> -->
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
      <div class="my_content">
        <!-- Horizontal Form -->
        <div class="box box-primary">
          <div class="box-header with-border">
            <h3 class="box-title">修改密码</h3>
          </div>
          <!-- /.box-header -->
          <!-- form start -->
          <form class="form-horizontal">
            <div class="box-body">
              <div class="form-group">
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="cpassword" placeholder="Current Password">
                  <div id="accountCheck"></div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="npassword" placeholder="New Password">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="cnpassword" placeholder="Confirm New Password">
                	<div id="pwdValidate"></div>
                </div>
              </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
              <button type="button" class="btn btn-primary pull-right">提交</button>
            </div>
            <!-- /.box-footer -->
          </form>
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="pull-right hidden-xs">
      缔造年轻人的中国梦
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2021 <a href="http://www.tedu.cn">达内教育</a>.</strong> All rights reserved.
  </footer>

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="../js/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../js/bootstrap/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="../js/AdminLTE/adminlte.min.js"></script>
<!-- page script -->
<script>
var first=false;
var second=false;
var third=false;

	$('#cpassword').blur(function(){
		var uid = <%=user.getUserId()%>;
		var upwd = $("#cpassword").val();
		
		console.log(">>>"+uid);
	    if (upwd == null || upwd == "") {
	        $("#accountCheck").text("原密码不能为空！");
	        $("#accountCheck").css("color","red");
	        first=false;
	        return false;
	    }
	    $.ajax({
	        type:"POST",
	        url:"I_dont_know_how_to_name?method=check_old_pass",
	        data:"uid="+uid+"&upwd="+upwd,
	        success:function(msg){
	        	console.log(">>>"+msg);
	            if(msg == "yes"){
	                $("#accountCheck").text("");
	                first=true;
	                return true;
	                
	            }else if(msg == "no"){
	                $("#accountCheck").text("原密码错误");
	                $("#accountCheck").css("color","red");
	                first=false;
	                return false;
	            }
	        }
	    });
	});
	
	
	$("#cnpassword").blur(function(){
		
		var npwd = $("#npassword").val();
		var cpwd = $("#cnpassword").val();
		if(npwd==cpwd){
			$("#pwdValidate").text("");
			second=true;
			return true;
        }else{
            $("#pwdValidate").text("新密码不一致");
            $("#pwdValidate").css("color","red");
            second=false;
	        return false;
		}
	});
	
	
	
	  $('.btn-primary').click(function(){
	        //读取用户的输入——表单序列化
			var uid = <%=user.getUserId()%>;
			
			var cpwd = $("#cnpassword").val();
			
			if(first==false || second==false)
				{
				alert("请确认信息！");
				return false;
				}
				
			else
				 {
				
				/* var url = "I_dont_know_how_to_name?method=change&uid="+uid+"&cpwd="+cpwd;
		        
			       
			     
		        window.location.href = url; */
				
				
				
				
				 $.ajax({
			        type:"POST",
			        url:"I_dont_know_how_to_name?method=change",
			        data:"uid="+uid+"&cpwd="+cpwd,
			        success:function(msg){
			        	console.log(">>>"+msg);
			        	if(msg=='yes'){  //修改成功
			        		alert("请重新登录");
		                    window.location.href ="login.jsp";
		                }else{ //修改失败
		                    alert("fail");
		                }

			        }
			    }); 
				} 
	        
				
	        //异步提交请求
	          
	    });  
	
	



</script>
</body>
</html>
