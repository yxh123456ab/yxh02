
<%@page import="cn.itheima.pojo.TbUser"%>
<%@page import="cn.itheima.pojo.TbBook"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/my.order.css" rel="stylesheet" />
    <link href="../css/header.css" rel="stylesheet" />
    <link href="../css/footer.css" rel="stylesheet" />
    <link href="../css/set.css" rel="stylesheet" />
    <script>
$('#zly_search').on("click",function(){
	var input=$('#input').val();
	
	var url = "Password_And_ChangeAddress?method=search&text_search="+encodeURI(encodeURI(input))+"";
    
    
    window.location.href = url;
   // alert(url);
	
	
});
</script>
</head>
<body>
<div id="header">
<% 

int integer = 0;
integer=Integer.valueOf( session.getAttribute("user_id").toString());
System.out.println(integer);  %>
    <!-- 头部-->
    <header id="top">
        <div id="logo" class="lf">
            <img class="animated jello" src="../img/header/logo.png" alt="logo"/>
        </div>
        <div id="top_input" class="lf">
            <input id="input" type="text" placeholder="请输入您要搜索的内容"/>

            <a id="zly_search" class="rt"><img id="search" src="../img/header/search.png" alt="搜索"/></a>
        </div>
        <div class="rt">
	        <ul class="lf">
	            <li><a href="displayBooks?method=displayHotBooks" >首页</a><b>|</b></li>
            <li><a href="Book_collect?method=Show_collect" >收藏</a><b>|</b></li>
            <li><a href="new_Don_tKnow?method=Show_order" >订单</a><b>|</b></li>
            <li><a href="DisplayCart?method=displayCart" >购物车</a><b>|</b></li>
            <li><a href="password-change.jsp">设置</a><b>|</b></li>
            <li><a href="login.jsp">退出</a><b>|</b></li>
            <li><a href="lookforward.html">帮助</a></li>
	        </ul>
        </div>
    </header>
    <!-- 主导航-->

    <!-- 主导航-->
    <nav id="nav1">
        <!-- 我的订单导航栏-->
        <div id="nav_order" class="lf">
            <ul>
                <li></li>
            </ul>
        </div>
    </nav>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    <div id="leftsidebar_box" class="lf">
        <div class="line"></div>
        <dl class="my_order">
            <dt onClick="changeImage()">帐号管理<img src="../img/myOrder/myOrder1.png"></dt>
            <dd class="first_dd"><a href="password-change.html">修改密码</a></dd>
            <dd><a href="address-add.jsp">添加地址</a></dd>
        </dl>

    </div>
    <!-- 右边栏-->
    <div class="rightsidebar_box rt" >
        <div class="order_empty">
         <div id="cover" class="rt">
      <form name="form1" id="fm-changep" method="post" action="">
            <div class="txt">
                <p>修改密码
                    <span>
                        <a href="index.jsp">继续购物</a>
                    </span>
                </p>
                <input name="user.uname" id="uname" type="hidden" value="<%=integer %>" />
                <div class="text">
                    <input type="password" placeholder="请输入当前密码" name="user.upwd" id="upwd" required>
                    <div id="accountCheck"></div>
                </div>
                <div class="text">
                    <input type="password" placeholder="请输入新密码" name="npwd" id="npwd" required minlength="6" maxlength="15">
                </div>
				<div class="text">
                    <input type="password" placeholder="请确认新密码" name="cpwd" id="cpwd" required minlength="6" maxlength="15">
                    <div id="pwdValidate"></div>
                </div>
                <div class="text">
	                <input class="button_login" type="button" value="提交" id="bt-changep" />
	                <div id="changeFail"></div>
                </div>
            </div>
        </form>
        </div>
        </div>
    </div>
</div>
<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../img/footer/icon1.png" alt=""/>
        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../img/footer/icon2.png" alt=""/>
        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../img/footer/icon3.png" alt=""/>
        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../img/footer/icon4.png" alt=""/>
        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
             <p class="footer1"><img src="../img/footer/tedu.png" alt="" class=" footLogo"/></p>
             <p class="footer2"><img src="../img/footer/footerFont.png"alt=""/></p>
            <!-- 页面底部-备案号 #footer -->
            <div class="record">
                2001-2016 版权所有 京ICP证8000853号-56
            </div>
        </div>
        <div class="foot_left lf" >
            <ul>
                <li><a href="#"><h3>买家帮助</h3></a></li>
                <li><a href="#">新手指南</a></li>
                <li><a href="#">服务保障</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>商家帮助</h3></a></li>
                <li><a href="#">商家入驻</a></li>
                <li><a href="#">商家后台</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>关于我们</h3></a></li>
                <li><a href="#">关于达内</a></li>
                <li><a href="#">联系我们</a></li>
                <li>
                    <img src="../img/footer/wechat.png" alt=""/>
                    <img src="../img/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>达内商城客户端</p>
            <img src="../img/footer/ios.png" class="lf">
            <img src="../img/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="../img/footer/erweima.png">
        </div>
    </div>
</div>
</body>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script src="../js/jquery.page.js"></script>
<script src="../js/order.js"></script>
<script type="text/javascript">
	// 验证用户名密码
	var first=false;
	var second=false;
	var third=false;
 	$('#npwd').focus(function(){
		var uname = $("#uname").html();
		var upwd = $("#upwd").val();
		console.log(">>>"+uname);
	    if (upwd == null || upwd == "") {
	        $("#accountCheck").text("原密码不能为空！");
	        $("#accountCheck").css("color","red");
	        first=false;
	    }
	    $.ajax({
	        type:"POST",
	        url:"Password_And_ChangeAddress?method=User_id_And_password",
	        data:"uname="+uname+"&upwd="+upwd,
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
	//验证新密码和确认密码是否一致
	$("#cpwd").blur(function(){
		var uname = $("#uname").val();
		var npwd = $("#npwd").val();
		var cpwd = $("#cpwd").val();
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
	 $('#bt-changep').click(function(){
        //读取用户的输入——表单序列化
		var uname = $("#uname").val();
		var upwd = $("#upwd").val();
		var cpwd = $("#cpwd").val();
		if(first==false || second==false)
			{
			return false;
			alert("请检查输入的信息")
			}
			
		else
			{
			
			alert("修改成功，请重新登录");
			$.ajax({
		        type:"POST",
		        url:"Password_And_ChangeAddress?method=Change_password",
		        data:"uname="+uname+"&cpwd="+cpwd,
		        success:function(msg){
		        	console.log(">>>"+msg);
		        	if(msg=='yes'){  //修改成功
	                    window.location.href =  "login.jsp";
	                }else{ //修改失败
	                    $('#changeFail').html('修改失败！');
	                    $("#changeFail").css("color","red");
	                }

		        }
		    });
			}
        
			
        //异步提交请求
          
    }); 
</script>
</html>
