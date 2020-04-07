<%
String __APP__ = request.getContextPath()+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=__APP__ %>easyui/themes/<%=session.getAttribute("tname")==null?"bootstrap":session.getAttribute("tname") %>/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=__APP__ %>easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=__APP__ %>easyui/themes/all.css"/>
<link rel="stylesheet" type="text/css" href="<%=__APP__ %>kindeditor-4.1.10/themes/default/default.css"/>
<script type="text/javascript">
	var __APP__ = "<%=request.getContextPath() %>/";
	var __FAPP__="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()%>/"
	var __APPUPLOAD__ = "<%=request.getContextPath() %>/upload/";
</script>
<script type="text/javascript" src="<%=__APP__ %>easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=__APP__ %>easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=__APP__ %>easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=__APP__ %>js/common.js"></script>
<script type="text/javascript" src="<%=__APP__ %>js/json2.js"></script>
<script type="text/javascript" src="<%=__APP__ %>kindeditor-4.1.10/kindeditor.js"></script>
<script type="text/javascript" src="<%=__APP__ %>kindeditor-4.1.10/lang/zh_CN.js"></script>

