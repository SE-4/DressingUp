<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<%@ include file="/common/common.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>用户注册</title>

    <script type="text/javascript">
        $(function () {

        });

        function save() {
            $('#managForm').form('submit', {
                url: "__APP__/User/userAdd",
                onSubmit: function () {
                    return inputCheck();
                },
                success: function (data) {
                    closeBackGround();
                    if (data == "success") {
                        $.messager.alert("提示", "操作成功","info",function(){
                            if(top.location!=self.location) top.location="__APP__/Index/index";
                        });
                    }
                }
            });
        }

        function inputCheck(){
        	openBackGround();
            $("#username").validatebox("isValid");
            if($("#passwd").val() != $("#password2").val()){
                $.messager.alert("提示","两次输入密码不一致!");
                return false;
            }else if(!($("#username").validatebox("isValid")&&$("#passwd").validatebox("isValid")&&$("#email").validatebox("isValid"))){
                return false;
            }
            var username = $("#username").val();
            var flag = true;
            $.ajax({
                type: "POST",
                url: "__APP__/User/check/username/"+username,
                data: {"action":"checkUser","username":username},
                async:false,
                success: function (data) {
                    if(data>0){
                    	closeBackGround();
                        $.messager.alert("提示","用户名已经存在!");
                        flag = false;
                    }
                },
                error: function (msg) {
                    alert(msg);
                }
            });
            return flag;
        }

    </script>
</head>
<body class="easyui-layout">
<div region="north" border="false" style="height:3px;overflow: hidden"></div>
<div region="west" border="false" style="width:3px;"></div>
<div region="east" border="false" style="width:3px;"></div>
<div region="south" border="false" style="height:3px;overflow: hidden"></div>
<div region="center" border="false">
    <div id="main" class="easyui-layout" fit="true" style="width:100%;height:100%;">

        <div region="center" border="false" style="padding:3px 0px 0px 0px;overflow:hidden">


                <form id="managForm" name="managForm" method="post">
                    <input type="hidden" id="action" name="action" value="add"/>
                    <input type="hidden" id="id" name="id"/>
                    <input type="hidden" id="roletype" name="roletype" value="3"/>
                    <table cellpadding="1" cellspacing="1" class="tb_custom1">
                        <tr>
                            <th width="10%" align="right"><label>用户名：</label></th>
                            <td width="30%">
                                <input id="username" name="username" class="easyui-validatebox"
                                       style="width:300px;word-wrap: break-word;word-break:break-all;" type="text" required="true"
                                       validType="length[0,32]"/><font color='red'>*</font></td>
                        </tr>
                        <tr>
                            <th width="10%" align="right"><label>密码：</label></th>
                            <td width="10%">
                                <input id="passwd" name="passwd" class="easyui-validatebox" required="true" validType="length[0,32]"
                                       style="width:300px"/>
                            </td>
                        </tr>
                        <tr>
                            <th width="10%" align="right"><label>密码确认：</label></th>
                            <td width="10%">
                                <input id="password2" name="" class="easyui-validatebox" style="width:300px" required="true"
                                       validType="length[0,32]"/>
                            </td>
                        </tr>
                        <tr>
                            <th width="10%" align="right"><label>订单类型：</label></th>
                            <td width="30%">
                                <select id="billtype" name="billtype">
                                    <option value="1">订做</option>
                                    <option value="2">成品</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th width="10%" align="right"><label>邮箱：</label></th>
                            <td width="30%">
                                <input id="email" name="email" class="easyui-validatebox"
                                       style="width:300px;word-wrap: break-word;word-break:break-all;" type="text" required="true"
                                       validType="email"/><font color='red'>*</font></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center">

                                <div id="dlg-buttons2">
                                    <a href="#" class="easyui-linkbutton" onclick="save();">保存</a>
                                    <a href="#" class="easyui-linkbutton" onclick="cancel();">取消</a>
                                </div>
                            </td>

                        </tr>

                    </table>
                </form>



        </div>
    </div>
</div>




</body>
</html>