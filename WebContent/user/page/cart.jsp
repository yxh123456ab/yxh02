<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/27
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>达内商城购物车</title>
    <link href="../css/header.css" rel="stylesheet" />
    <link href="../css/footer.css" rel="stylesheet" />
    <link href="../css/cart.css" rel="stylesheet" />
</head>
<style>
    .submitOrder{
        float: right;
        cursor: pointer;
        width: 133px;
        height:42px;
        line-height: 42px;
        background: -moz-linear-gradient(top, #27b0f6 0%, #0aa1ed 100%);
        background: -webkit-linear-gradient(top, #27b0f6 0%, #0aa1ed 100%);
        background: -o-linear-gradient(top, #27b0f6 0%, #0aa1ed 100%);
        background: -ms-linear-gradient(top, #27b0f6 0%, #0aa1ed 100%);
        margin-left: 22px;
        text-align: center;
        font-size: 18px;
        color:#fff;
        border-radius: 2px;
    }
</style>
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

<%--<div class="modal" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            删除提醒
        </div>
        <div class="modal_information">
            <img src="../img/model/model_img2.png" alt=""/>
            <span>确定删除您的这个宝贝吗？</span>
        </div>
        <div class="yes"><span>删除</span></div>
        <div class="no"><span>不删除</span></div>
    </div>
</div>
<div class="modalNo" style="display:none">
    <div class="modal_dialog">
        <div class="modal_header">
            删除提示
            <img src="../img/model/model_img1.png" alt="" class="rt close"/>
        </div>
        <div class="modal_information">
            <img src="../img/model/model_img2.png" alt=""/>
            <span>请选择商品</span>
        </div>
    </div>
</div>--%>
<div class="big">
    <form  name="" action="" method="post">
        <section id="section" >
            <div id="title">
                <b>购物车</b>
                <p>
                    已选<span class="total color">0</span>件商品<span class="interval"></span>合计(不含运费):<span class="totalPrices color susum">0.00</span><span class="unit color">元</span>
                </p>
            </div>
            <div id="box" >
                <div id="content_box">
                    <div class="imfor_top">
                        <div class="check_top">
                            <div class="all">
                            <span class="normal">
                                <img src="../img/cart/product_normal.png" alt=""/>
                            </span>  <input type="hidden" name="" value="">全选
                            </div>
                        </div>
                        <div class="pudc_top">商品</div>
                        <div class="pices_top">单价(元)</div>
                        <div class="num_top">数量</div>
                        <div class="totle_top">金额</div>
                        <div class="del_top">操作</div>
                    </div>

                    <c:forEach items="${tbCartItems }" var="item">
                    <div class="imfor">
                        <input type="hidden" id="isbn" value="${item.product}">
                        <div class="check">
                            <div class="Each">
                            <span class="normal">
                                <img src="../img/cart/product_normal.png" alt=""/>
                            </span>
                                <input type="hidden" name="" value="">
                            </div>
                        </div>
                        <div class="pudc">
                            <div class="pudc_information" id="1">
                                <img src="../img/goods/${item.product}/detail1.jpg" class="lf"/>
                                <input type="hidden" name="" value="">
                                <span class="des lf">
                         ${item.title}
                              <input type="hidden" name="" value="">
                        </span>
                                <p class="col lf"><span>作者：</span><span class="color_des">${item.author}  <input type="hidden" name="" value=""></span></p>
                            </div>
                        </div>
                        <div class="pices">
                            <p class="pices_des"></p>
                            <p class="pices_information"><b>￥</b><span>${item.price}<input type="hidden" name="" value=""></span></p>
                        </div>
                        <div class="num"><span class="reduc" onclick="numJudge(${item.product})">&nbsp;-&nbsp;</span>
                            <input type="text" id="itemCount" value="${item.count}" >
                            <span class="add" onclick="numAdd(${item.product})">&nbsp;+&nbsp;</span></div>
                        <div class="totle">
                            <span>￥</span>
                            <span class="totle_information">${item.price*item.count}</span>
                        </div>
                        <div class="del">
                            <a href="javascript:del(${item.product});" class="del_d">删除</a>
                        </div>
                    </div>
                    </c:forEach>


                </div>
                <div class="foot">
                    <div class="foot_check">
                        <div class="all">
                        <span class="normal">
                                <img src="../img/cart/product_normal.png" alt=""/>
                            </span>  <input type="hidden" name="" value="">全选
                        </div>
                    </div>
                    <div class="submitOrder" onclick="submitOrder()">提交订单</div>
                    <div class="foot_tol"><span>合计(不含运费):</span><span  class="foot_pices susumOne">0.00</span><span class='foot_des'>元</span></div>
                    <div class="foot_selected">
                        已选<span class="totalOne color">0</span>件商品
                    </div>
                </div>
            </div>
        </section>
    </form>
    <div class="none" style="display: none">
        <p class="none_title">购物车</p>
        <div class="none_top"></div>
        <div class="none_content">
            <img src="../img/30.png" alt="" class="lf"/>
            <p class="lf">您的购物车竟然还是空哒( ⊙ o ⊙ )</p>
            <span class="lf">赶快去下单吧！</span>
            <a href="#" class="lf">去购物>></a>
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
<script src="../js/cart.js"></script>
<script src="../js/index.js"></script>
<script>
    <!-- 显示空购物车页面-->
    $(function(){
        if(!$(".imfor")) {
            $('#section').hide();
            $('.none').show();
        }
        

        
    })
   
</script>
</body>
<script>
    
function submitOrder() {

    $orders = $(".imfor .true");

    //alert($orders.length);
    var data = [];
    var a = "";
    $orders.each(function () {
        var $box = $(this).parent(".Each").parent(".check").parent(".imfor");

        var isbn = $box.children("#isbn").val();
        var count = $box.children(".num").children("#itemCount").val();
         var temp = isbn+"-"+count;
         a += temp+",";
          /*  {
                "isbn" : isbn,
                "count" : count
            }*/
       // data.push(a);

    });
    
    a = a.substring(0,a.length-1);
    //alert(a);
    var url_zly="EditCart?method=submitOrder&data="+a;
    window.location.href = url_zly;
    /* $.ajax({
        type:"post",
        url:"EditCart?method=submitOrder&data="+a,
        contentType:"application/json",
        success:function(data){
            //alert(data)
        }
    }); */
}

    
    function del(product) {

        if(confirm("确认删除吗")==true){
            var url = "DeleteCartItem?method=deleteItem&product="+product;
            window.location.href = url;
        }else{
            return false;
        }
    }

    function numMinus(product,count) {
        if(count==1){
            del(product);
        }else{
            $.ajax({
                type:'post',
                data:'product='+product,
                url:'EditCart?method=numMinus',
                success:function (msg) {
                    console.log("正在处理");
                    if(msg == 'yes'){
                        console.log("减少成功");
                    }
                }
            });
        }

    }
	

    function numAdd(product) {
    $.ajax({
        type:'post',
        data:'product='+product,
        url:'EditCart?method=numAdd',
        success:function (msg) {
            console.log("正在处理");
            if(msg == 'yes'){
                console.log("增加成功");
            }
        }

    });

    }
    
    $('#zly_search').on("click",function(){
    	var input=$('#input').val();
    	
    	var url = "Password_And_ChangeAddress?method=search&text_search="+encodeURI(encodeURI(input))+"";
        
        
        window.location.href = url;
       // alert(url);
    	
    	
    });
    
    
    function numJudge(product) {
        $.ajax({
            type:'post',
            data:'product='+product,
            url:'EditCart?method=numJudge',
            success:function (msg) {
                console.log("正在查询");
                if(msg == 'ok'){
                    numMinus(product)
                }else if(msg == "no"){
                    del(product);
                }
            }
        });
    }

    function numMinus(product) {
        $.ajax({
            type:'post',
            data:'product='+product,
            url:'EditCart?method=numMinus',
            success:function (msg) {
                console.log("正在处理");
                if(msg == 'yes'){
                    console.log("减少成功");
                }
            }
        });


    }
</script>
</html>

