<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/personage.css" rel="stylesheet" />
</head>
<body>
<!-- 页面顶部-->
<jsp:include page="header.jsp"></jsp:include>
<!-- nav主导航-->
<nav id="nav">
    <ul>
        <li><a href="index.html" class="acti">首页</a></li>
        <li><a href="index.html#computer" >电脑办公</a></li>
        <li><a href="index.html#stationery" >办公文具</a></li>
    </ul>
</nav>
<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
 <!-- 左边栏-->
 <jsp:include page="left.jsp"></jsp:include>
   <!-- 右边栏-->
    <div class="rightsidebar_box rt">	
        <!--标题栏-->
        <div class="rs_header">
            <span class="address_title">收获地址管理</span>
        </div>
        <!--收货人信息填写栏-->
        <div class="rs_content">
        	<form method="post" action="">
	            <!--收货人姓名-->
	            <div class="recipients">
	                <span class="red">*</span><span class="kuan">收货人：</span><input type="text" name="receiverName" id="receiverName"/>
	            </div>
	            <!--收货人所在城市等信息-->
	            <div  class="address_content">
					 <span class="red">*</span>
           <span class="kuan" style="width: 70px">
                    省&nbsp;&nbsp;份：
           </span>
           <select
           data-province="---- 选择省 ----" 
           id="receiverState"
           name="receiverState"
           onchange=getCities(this.value,-1,-1);>
           </select>
			 城市：
       <select 
       data-city="---- 选择市 ----" 
       id="receiverCity"
       name="receiverCity"
       onchange="getAreas(this.value,-1)"></select>
	     区/县：
       <select 
       data-district="---- 选择区 ----" 
       id="receiverDistrict"
       name="receiverDistrict"></select>
				</div> 
	            
	            
	            <!--收货人详细地址-->
	            <div class="address_particular">
	                <span class="red">*</span><span class="kuan">详细地址：</span><textarea name="receiverAddress" id="receiverAddress" cols="60" rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
	            </div>
	            <!--收货人地址-->
	            <div class="address_tel">
	                <span class="red">*</span><span class="kuan">手机号码：</span><input type="tel" id="receiverMobile" name="receiverMobile"/>固定电话：<input type="text" name="receiverPhone" id="receiverPhone"/>
	            </div>
	            <!--邮政编码-->
	            <div class="address_postcode">
	                <span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input type="text" name="receiverZip"/>
	            </div>
	            <!--地址名称-->
	            <div class="address_name">
	                <span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input type="text" id="addressName" name="addressName"/>如：<span class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
	            </div>
	            <!--保存收货人信息-->
	            <div class="save_recipient">
	                保存收货人信息
	            </div>
	
    		</form>
            <!--已有地址栏-->
            <div class="address_information_manage">
                <div class="aim_title">
                    <span class="dzmc dzmc_title">地址名称</span><span class="dzxm dzxm_title">姓名</span><span class="dzxq dzxq_title">地址详情</span><span class="lxdh lxdh_title">联系电话</span><span class="operation operation_title">操作</span>
                </div>
                <div class="aim_content_one aim_active">
                    <span class="dzmc dzmc_active">办公室</span>
                    <span class="dzxm dzxm_normal">杨洋</span>
                    <span class="dzxq dzxq_normal">北京市海淀区北下关街道中鼎大厦B座331</span>
                    <span class="lxdh lxdh_normal">18435110514</span>
                    <span class="operation operation_normal">
                    	<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>
                    </span>
                    <span class="swmr swmr_normal"></span>
                </div>
                <div class="aim_content_two">
                    <span class="dzmc dzmc_normal">家里</span>
                    <span class="dzxm dzxm_normal">杨洋</span>
                    <span class="dzxq dzxq_normal">北京市大兴区西红门镇瑞海家园</span>
                    <span class="lxdh lxdh_normal">13788882346</span>
                    <span class="operation operation_normal">
                    	<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>
                    </span>
                    <span class="swmr swmr_normal">设为默认</span>
                </div>
                <div class="aim_content_three">
                    <span class="dzmc dzmc_normal">宿舍</span>
                    <span class="dzxm dzxm_normal">杨洋</span>
                    <span class="dzxq dzxq_normal">山西省小店区山西大学商务学院</span>
                    <span class="lxdh lxdh_normal">13799992347</span>
                    <span class="operation operation_normal">
                    	<span class="aco_change">修改</span>|<span class="aco_delete">删除</span>
                    </span>
                    <span class="swmr swmr_normal">设为默认</span>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="${pageContext.request.contextPath}/images/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="${pageContext.request.contextPath}/images/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="${pageContext.request.contextPath}/images/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="${pageContext.request.contextPath}/images/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
             <p class="footer1"><img src="${pageContext.request.contextPath}/images/footer/logo.png" alt="" class=" footLogo"/></p>
             <p class="footer2"><img src="${pageContext.request.contextPath}/images/footer/footerFont.png" alt=""/></p>
        </div>
        <div class="foot_left lf">
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
                    <img src="${pageContext.request.contextPath}/images/footer/wechat.png" alt=""/>
                    <img src="${pageContext.request.contextPath}/images/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>学子商城客户端</p>
            <img src="${pageContext.request.contextPath}/images/footer/ios.png" class="lf">
            <img src="${pageContext.request.contextPath}/images/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="${pageContext.request.contextPath}/images/footer/erweima.png">
        </div>
		<!-- 页面底部-备案号 #footer -->
        <div class="record">
            &copy;2017 达内集团有限公司 版权所有 京ICP证xxxxxxxxxxx
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.page.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/orders.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/distpicker.data.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/distpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/personal.js"></script>
<script type="text/javascript">
$(function(){
	//所有的dd隐藏
	 $("#leftsidebar_box dd").hide();
	//让账号管理显示
	 $("#leftsidebar_box .address dd").show();
	//所有的自定义列表的标题后边的图片 myOrder2.png
	$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
   //设置账号管理的图片和其他的三个不相同
	$("#leftsidebar_box .address").find('img').attr("src","../images/myOrder/myOrder1.png");
    
	getProrvinces(-1,-1,-1);
})
//显示区县的信息
function getAreas(cityCode,areaCode){
	//receiverDistrict
	$.ajax({
		"url":"${pageContext.request.contextPath}/dict/showArea.do",
		"data":"cityCode="+cityCode,
		"type":"GET",
		"dataType":"json",
		"success":function(obj){
			$("#receiverDistrict").html("<option>- - - 请选择 - - - </option>");
			for(i = 0;i<obj.data.length;i++){
				$("#receiverDistrict").append("<option value="+obj.data[i].areaCode+">"+obj.data[i].areaName+"</option>");
			}
		}
	});
	
}
//显示城市信息
function getCities(provinceCode,cityCode,areaCode){
	$.ajax({
		"url":"${pageContext.request.contextPath}/dict/showCity.do",
		"data":"provinceCode="+provinceCode,
		"type":"GET",
		"dataType":"json",
		"success":function(obj){
			$("#receiverCity").html("<option>- - - 请选择 - - - </option>");
			for(i = 0;i<obj.data.length;i++){
				$("#receiverCity").append("<option value="+obj.data[i].cityCode+">"+obj.data[i].cityName+"</option>");
			}
		
		}
		
	});
	getAreas(cityCode,-1);
}

//显示省列表信息
function getProrvinces(provinceCode,cityCode,areaCode){
	$.ajax({
		"url":"${pageContext.request.contextPath}/dict/showProvince.do",
		"data":"",
		"type":"GET",
		"dataType":"json",
		"success":function(obj){
			$("#receiverState").html("<option>- - - 请选择 - - -</option>");
			for(i=0;i<obj.data.length;i++){
				$("#receiverState").append
				("<option value="+obj.data[i].provinceCode+">"+obj.data[i].provinceName+"</option>");
			}
		}
		
	});
	getCities(provinceCode,-1,-1);
}

	$(".lxdh_normal").each(function(i,e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});
</script>
</html>





