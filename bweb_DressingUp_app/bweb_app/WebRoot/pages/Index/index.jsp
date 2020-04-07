<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<%@ include file="/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=__APP__ %>css/index.css" />
<script type="text/javascript" src="<%=__APP__ %>js/ideaframe.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
    	// 获取登录的角色
        var roletype = "<%=session.getAttribute("roletype")%>";

        $(function(){
            
            if(roletype){
                if(roletype == "1"){
                    $("#iframepage").attr("src",__APP__+"pages/User/user.jsp");
                }else if(roletype == "3"){
                 $("#iframepage").attr("src",__APP__+"pages/Seedoc/seedoc.jsp");
                }else if(roletype == "4"){
                	$("#iframepage").attr("src",__APP__+"pages/Guide/guide.jsp");
                }
            }else{
                //$("#iframepage").attr("src","__APP__/Index/register");
                window.location.href=__APP__+"Index/login.jsp";
            }

        });

        var _menus = "";
        if (roletype) {
            if (roletype == "1") {
                _menus = {
                    "menus": [
                        
                        {"menuid": "1", "icon": "icon-role", "menuname": "用户管理",
                            "menus": [
                                {"menuid": "111", "menuname": "用户列表", "icon": "icon-users", "url": __APP__+"pages/User/user.jsp"}
                            ]

                        },
                        {"menuid": "1", "icon": "icon-role", "menuname": "图片管理",
                            "menus": [
                                {"menuid": "111", "menuname": "图片列表", "icon": "icon-users", "url": __APP__+"pages/Tuijian/tuijian.jsp"}
                            ]

                        }
                        
                    ]};
            }
        }


    </script>

<style type="text/css">
.themeblock {
	width: 10px;
	height: 10px;
	display: inline-block;
}

.header {
	height: 70px;
	overflow: hidden;
	background: url(images/bg.jpg) no-repeat 0 -1000px;
}

.logo {
	float: left;
	color: #FFF;
	margin-left: 15px;
	line-height: 45px;
}

.logo img {
	height: 50px;
	float: left;
	margin-right: 10px;
	margin-top: 10px;
}

.bg-main,.border-main .button.active,.pointer.border-main .active,.nav-pills.border-main .active a,.nav-main.border-main .nav-head,.pagination.border-main .active
	{
	background-color: #0ae;
} /*背景-主色*/
.head-l {
	float: left;
	margin-top: 17px;
	margin-left: 15px;
}

.head-l .button {
	padding: 8px 15px;
}

.head-l .bg-blue:hover {
	background-color: #03b6fd;
}

.head-l .button {
	padding: 8px 15px;
}

.button-little {
	padding: 3px 5px;
	font-size: 12px;
	font-weight: normal;
	line-height: 16px;
}

.bg-green {
	background-color: #2c7;
}

.bg-blue {
	background-color: #0ae;
}

.bg-red,.border-red .button.active,.pointer.border-red .active,.nav-pills.border-red .active a,.nav-main.border-red .nav-head,.pagination.border-red .active
	{
	background-color: #e33;
}

.radius-circle {
	border-radius: 50%;
}

.margin-big-left {
	margin-left: 20px;
	margin-bottom: 20px;
}
</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	 
<div region="south" split="true"
     style="height: 30px;">
    <div>
        <table width="100%">
            <tr>
                <td style="width: 20%;padding-right: 50px;" align="right">
                    登录账号：<%=session.getAttribute("username") %> &nbsp;&nbsp;&nbsp;&nbsp;<a
                        href="<%=__APP__ %>IndexServlet?action=logout">退出</a>
                </td>
                <!--  
                <td style="width: 80%" align="right">
                    主题&nbsp;&nbsp;&nbsp;&nbsp;:<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=default" style="color: #2571EB;">经典</a>&nbsp;&nbsp;
                    <<a href="<%=__APP__%>/IndexServlet?action=changeTheme&name=default">经典</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="<%=__APP__%>/IndexServlet?action=changeTheme&name=cupertino">清新蓝</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=gray"
						style="color: #585858;">灰色</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=black"
						style="color: #000000;">酷黑</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=bootstrap"
						style="color: #585858;">bootstrap</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=ui-cupertino"
						style="color: #008198;">清新蓝</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=ui-dark-hive"
						style="color: #000000;">深黑</a>&nbsp;&nbsp;
					<a
						href="<%=__APP__%>IndexServlet?action=changeTheme&name=ui-pepper-grinder"
						style="color: #ECC73B;">花布</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=ui-sunny"
						style="color: #BF570C;">阳光</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=metro"
						style="color: #585858;">metro</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=metro-blue"
						style="color: #00AEEF;">metroblue</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=metro-gray"
						style="color: #454545;">metrogray</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=metro-green"
						style="color: #008900;">metrogreen</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=metro-orange"
						style="color: #D14625;">metroorange</a>&nbsp;&nbsp;
					<a href="<%=__APP__%>IndexServlet?action=changeTheme&name=metro-red"
						style="color: #7A0000;">metrored</a>&nbsp;&nbsp;
					</td>
					-->
	
	<!--<td style="width: 20%" align="right">-->
	<!--模式：&nbsp;&nbsp;&nbsp;&nbsp;<a href="__APP__/Index/changeModel?name=index">桌面</a>&nbsp;&nbsp;&nbsp;&nbsp;-->
	<!--<a href="__APP__/Index/changeModel/name/index2">经典</a>&nbsp;&nbsp;&nbsp;&nbsp;-->
	<!--</td>-->
	</tr>
	</table>

	</div>
	</div>
	
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width: 180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>
	</div>
	
	<div region="north" hide="true" split="true">
		<div class="header">
	  <div class="logo">
	    <img src="images/icon.jpg" class="radius-circle" height="50" alt="" />
	    <h1 style="font-size:25px;margin-top:15px;width:350px;">后台管理</h1>
	  </div>
	</div>
	</div>
	
	
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 0px; color: red; overflow: hidden;"
				closable="true">
				<iframe src="" id="iframepage" name="iframepage" frameBorder=0
					width="100%" height="100%" onLoad=""></iframe>
			</div>
		</div>
	</div>
	<div region="east" collapsed="false" id="datetool" title="日历"
		split="true" style="width: 180px; overflow: hidden;">
		<div class="easyui-calendar"></div>
		<embed width="160" height="70" align="middle"
			pluginspage="http://www.macromedia.com/go/getflashplayer"
			type="application/x-shockwave-flash" allowscriptaccess="always"
			name="honehoneclock" bgcolor="#ffffff" quality="high"
			src="<%= __APP__%>swf/honehone_clock_wh.swf" wmode="transparent">
		</embed>
	</div>

	<!--修改密码窗口-->

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	</div>
</body>
</html>
<script type="text/javascript">
</script>