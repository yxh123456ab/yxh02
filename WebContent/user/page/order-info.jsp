<%@page import="cn.test.check_user"%>
<%@ page import="cn.itheima.pojo.TbBook" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>达内学子商城-订单详情页</title>
    <link href="../css/header.css" rel="stylesheet" />
    <link href="../css/footer.css" rel="stylesheet" />
    <link href="../css/order.info.css" rel="stylesheet" />
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
<!-- nav主导航-->

<!--详细信息-->
<div id="container">
        <!-- 导航 -->
        <div class="container_nav">
            首页&gt;订单管理&gt;订单<span>${order.orderId}</span>
        </div>
        <div class="orderInfo_icon">
            <p>订单<span class="order-num">${order.orderId}</span>&nbsp;&nbsp;&nbsp;当前状态：<span class="state">等待发货</span></p>
        </div>
        <!-- 订单状态流程图-->
        <div id="orderStatusChart">
              <dl>
                  <dt><img src="../img/orderinfo/orderinfo_img1_2.png" alt=""/></dt>
                  <dd>
                      <p>提交订单</p>
                      <span>${order.placed}</span>
                  </dd>
              </dl>
              <dl>
                <dt class="point"><img src="../img/orderinfo/orderinfo_img6_2.png" alt=""/></dt>
              </dl>

              <dl>
                  <dt><img src="../img/orderinfo/orderinfo_img3_1.png" alt=""/></dt>
                  <dd>
                      <p style="display: none">配送中</p>
                      <span style="display: none">2016.01.01 13:00</span>
                  </dd>
              </dl>
              <dl>
                <dt class="point"><img src="../img/orderinfo/orderinfo_img6_1.png" alt=""/></dt>
              </dl>

              <dl>
                  <dt><img src="../img/orderinfo/orderinfo_img4_1.png" alt=""/></dt>
                  <dd >
                      <p style="display: none">确认收货</p>
                      <span style="display: none">2016.01.01 13:00</span>
                  </dd>
              </dl>
              <dl>
                <dt class="point"><img src="../img/orderinfo/orderinfo_img6_1.png" alt=""/></dt>
              </dl>

            <dl>
                  <dt><img src="../img/orderinfo/orderinfo_img5_1.png" alt=""/></dt>
                  <dd >
                      <p style="display: none">评价</p>
                      <span style="display: none">2016.01.01 13:00</span>
                  </dd>
              </dl>

        </div>
        <div class="clear">

        <!-- 详细信息-->
            <h1>详细信息</h1>
            收货人：<span class="consignee">${address.receiver}</span>&nbsp;&nbsp;&nbsp;&nbsp;邮编：<span class="postcode">100000</span>&nbsp;&nbsp;&nbsp;&nbsp;联系电话：<span class="tel">${address.receiverPhone}</span>
            <br/>
            <p>收货地址：<span>${address.address}</span></p>
        </div>

        <!-- 商品信息-->
        <div style="width: 1000px; margin:0px auto">
            <h1 class="commodity">商品信息</h1>
            <div class="product_confirm">
                <ul class="item_title">
                    <li class="p_info">商品信息</li>
                    <li class="p_price">单价(元)</li>
                    <li class="p_count">数量</li>
                    <li class="p_tPrice">实付款</li>
                </ul>
                <div>订单编号：<span>${order.orderId}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成交时间：${order.placed}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系客服：<img src="../img/orderinfo/kefuf.gif" alt=""/></div>
                 <c:forEach items="${books}" var="book"  varStatus="loop">
                <ul class="item_detail">
                    <li class="p_info" style="height: 102px;">
                        <b><img src="../img/goods/${book.isbn}/detail1.jpg" /></b>
                        <p class="product_name lf" >
                           ${book.title}
                        </p>
                        <br/>
                <span class="product_color ">
                   
                </span>
                    </li>
                    <li class="p_price">
                        <i>专属价</i>
                        <br/>
                        <span class="pro_price">￥${book.price}</span>
                    </li>
                    <li class="p_count">${orderItem[loop.count-1].count}件</li>
                    <li class="p_tPrice">￥${orderItem[loop.count-1].price}</li>
                </ul>
                </c:forEach>
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
<script src="../js/jquery-3.1.1.min.js"></script>
<script>
    //搜索下拉
    $('.seek').focus(function(){

        if($(this).hasClass('clickhover')){

            $(this).removeClass('clickhover');
            $(this).find('.seek_content').hide();
            $(this).find('img').attr('src','../img/header/header_normal.png');

        }else{
            $(this).addClass('clickhover');
            $(this).find('.seek_content').show();
            $(this).find('img').attr('src','../img/header/header_true.png');
        }
    })
    $('.seek_content>div').click(function(){
        $('.seek').removeClass('clickhover');
        var text=$(this).html();
        $('.seek span').html(text);
        $(this).parent().hide();
        $('.seek').find('img').attr('src','../img/header/header_normal.png');
        $('.seek').blur();

    })

    $('.seek').blur(function(){

        $('.seek').removeClass('clickhover');
        $('.seek_content').hide();

        $('.seek').find('img').attr('src','../img/header/header_normal.png');
        console.log(1);
    })
    //头部hover
    $(".care").hover(function(){
        $(this).attr('src',"../img/header/care1.png");
    },function(){
        $(this).attr('src',"../img/header/care.png");
    });
    $(".order").hover(function(){
        $(this).attr('src',"../img/header/order1.png");
    },function(){
        $(this).attr('src',"../img/header/order.png");
    });
    $(".shopcar").hover(function(){
        $(this).attr('src',"../img/header/shop_car1.png");
    },function(){
        $(this).attr('src',"../img/header/shop_car.png");
    });
</script>
</html>
