<%@page import="cn.test.check_user"%>
<%@ page import="cn.itheima.pojo.TbBook" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/my.order.css" rel="stylesheet"/>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
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
<!-- 页面顶部-->
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

<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li></li>
    </ul>
</div>
    <!--我的订单内容区域 #container-->
    <div id="container" class="clearfix">
        <!-- 左边栏-->
        <div id="leftsidebar_box" class="lf">
            <div class="line"></div>
            <dl class="my_order">
                <dt onClick="changeImage()">我的订单
                    <img src="../img/myOrder/myOrder2.png">
                </dt>

            </dl>

         </div>
        <!-- 右边栏-->
        <div class="rightsidebar_box rt">
            <!-- 商品信息标题-->
            <table id="order_list_title"  cellpadding="0" cellspacing="0" >
                <tr>
                    <th width="345">商品</th>
                    <th width="82">单价（元）</th>
                    <th width="50">数量</th>
                    <th width="82">售后</th>
                    <th width="100">实付款（元）</th>
                    <th width="90">交易状态</th>
                    <th width="92">操作</th>
                </tr>
            </table>
            <!-- 订单列表项 -->
            
             <c:forEach items="${Order_items}" var="order" varStatus="loop">
            <div id="orderItem">
                <p class="orderItem_title">
                 <span id="order_id">
                     &nbsp;&nbsp;订单编号:<a href="#">${order.orderId}</a>
                 </span>
                    &nbsp;&nbsp;成交时间：${tborders[loop.count-1].placed}&nbsp;&nbsp;
                 <span>
                     <a href="#" class="servie">
                         联系客服<img src="../img/myOrder/kefuf.gif"/>
                     </a>
                 </span>
                </p>
            </div>
            <div id="orderItem_detail">
                <ul>
                    <li class="product">
                        <b><a href="displayBooks?method=queryBook&isbn=${Books[loop.count-1].isbn}"><img src="../img/goods/${Books[loop.count-1].isbn}/detail1.jpg" /></a></b>
                        <b class="product_name lf" >
                            <a href="">${Books[loop.count-1].title}</a>
                            <br/>
                        </b>
                        <b class="product_color ">
                            出版社：${Books[loop.count-1].press}
                        </b>
                    </li>
                    <li class="unit_price">
                        专属价
                        <br/>
                        ￥${Books[loop.count-1].price}
                    </li>
                    
                    <li class="count">
                       ${counts[loop.count-1]}件
                    </li>
                    <li class="sale_support">
                        退款/退货
                        <br/>
                        我要维权
                    </li>
                    <li class=" payments_received">
                        ￥${order.price}
                    </li>
                    <li class="trading_status">
                        <img src="../img/myOrder/car.png" alt=""/>
						<c:choose>
						<c:when test="${tborders[loop.count-1].sta==0}">待处理</c:when>
						<c:when test="${tborders[loop.count-1].sta==1}">处理中</c:when>
						<c:when test="${tborders[loop.count-1].sta==2}">已发货</c:when>
						<c:when test="${tborders[loop.count-1].sta==3}">已交付</c:when>
						
						</c:choose>
                        <br/>
                    <a id="zly_show" href="new_Don_tKnow?method=order_info&order=${order.orderId}&isbn=${Books[loop.count-1].isbn}" >订单详情</a>
                        
                    </li>
                    <li class="operate">
                    <c:choose>
                    <c:when test="${tborders[loop.count-1].sta=='3'}">
                   	<a id="zly_order_style_ed" style="background: -webkit-linear-gradient(top, rgb(185, 246, 39) 0%, rgb(169, 223, 9) 100%);">已经收货</a>
					</c:when>
                    <c:when test="${tborders[loop.count-1].sta!='3'}">
                    <a id="zly_order_style_ing" href="new_Don_tKnow?method=sure_receive&order=${order.orderId}">确认收货</a></c:when>
                    
                    
                    </c:choose>
                        
                    </li>
                </ul>
            </div>
            </c:forEach>
			<!--分页器-->
            <div class="tcdPageCode"></div>
        </div>
        <!-- 右边栏(没有订单时) -->
        <!-- 右边栏(没有订单时)开始 -->
        <!--
		<div class="rightsidebar_box rt" >
		      <div class="order_empty">
		          <img src="../img/myOrder/myOrder3.png" alt=""/>
		         <p>你可能还没有订单(⊙o⊙)</p>
		         <span>赶紧去下单吧 <b>去购物</b></span>
		     </div>
	    </div>
    -->
        <!-- 右边栏(没有订单时)结束 -->
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

	
	$("#zly_order_style_ed").css({
		"background":"-webkit-linear-gradient(top, rgb(185, 246, 39) 0%, #a9df09 100%)"
			
	});



//分页部分
/* $(".tcdPageCode").createPage({
    pageCount:6,
    current:1,
    backFn:function(p){
    	
    }
});  */

/* $(".tcdPageCode").createPage({
    pageCount: 2,
    current: 1,
    backFn:function(pageIndex){
        var start = (pageIndex-1)*${requestScope.length };
        window.location.href='../servlet/show-order?pageIndex='+pageIndex+'&start='+start+'&length='+${requestScope.length };
    }
}); */

</script>
</html>
