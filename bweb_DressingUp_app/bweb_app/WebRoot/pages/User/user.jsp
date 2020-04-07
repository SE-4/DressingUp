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
    <title>管理员管理列表</title>

    <script type="text/javascript">
        $(function () {
            $('#grid1').datagrid({
                title: '管理员列表',
                nowrap: true,
                striped: true,
                border:false,
                fit: true,
                url: __APP__+"UserServlet",
                idField: 'uuid',
                pagination: true,
                rownumbers: true,
                pageSize: 10,
                pageNumber: 1,
                singleSelect: true,
                fitColumns: true,
                sortName: 'id',
                sortOrder: 'desc',
                columns: [
                    [
                        {title: '编号', field: 'id', width: 100, hidden: false},
                        {title: '用户名', field: 'username', width: 100, sortable: true},
                        {title: '密码', width: 100, field: 'passwd'},
                        {title: '邮箱',width:100,field:'email'},
                        {title: '姓名',width:100,field:'name'},
                        {title: '角色',width:100,field:'roletype',sortable: true,formatter:function(value, rec){
                        	if(value==1){
                        	  return "管理员"
                        	}else{
                        		return "普通用户"
                        	}
                        }}

                    ]
                ],
                toolbar: [
                    {
                        text: '新增',
                        iconCls: 'icon-add',
                        handler: function () {
                            $("#action").val("add");
                            $("#managerDialog").dialog('open');
                            $('.validatebox-tip').hide();
                            
                            $("#username").val("");
				            $("#passwd").val("");
				            $("#password2").val("");				      
				            $("#email").val("");		
				            $("#name").val("");		
                        }
                    },
                    '-',
                    {
                        text: '修改',
                        id: 'commit',
                        iconCls: 'icon-edit',
                        handler: function () {
                            $("#action").val("edit");
                            var selected = $('#grid1').datagrid('getSelected');
                            if (selected) {
                                edit(selected);
                                var index = $('#grid1').datagrid('getRowIndex', selected);

                            } else {
                                $.messager.alert("提示", "请选择一条记录进行操作");
                            }
                        }
                    },
                    '-',
                    {
                        text: '删除',
                        id: 'commit',
                        iconCls: 'icon-remove',
                        handler: function () {
                            var rows = $('#grid1').datagrid('getSelected');
                            if (rows) {
                                var rowId = rows.id;
                                $.messager.confirm('提示', '确定要删除吗？', function (r) {
                                    if (r) {
                                        deleteItem(rowId);
                                    }
                                });
                            } else {
                                $.messager.alert("提示", "请选择一条记录进行操作");
                            }
                        }
                    }
                ]

            });
        });

        function save() {
            $('#managForm').form('submit', {
                url: __APP__+"UserServlet",
                onSubmit: function () {
                    return inputCheck();
                },
                success: function (data) {
                    closeBackGround();
                    if (data == "success") {
                        $.messager.alert("提示", "操作成功", "info", function () {
                            closeFlush();
                        });
                    }
                }
            });
        }

        function edit(obj) {
            //$.post("controller/userController.php?action=getOne",{id:uuid},function(data){
            var username = obj.username;
            var password = obj.passwd;
            var email = obj.email;
            var name= obj.name;
            $("#username").val(username);
            $("#passwd").val(password);
            $("#id").val(obj.id);
            $("#email").val(obj.email);
            $("#name").val(obj.name);
            $("#managerDialog").dialog('open');
            //});
        }

        function deleteItem(id) {
            ajaxCallBack("UserServlet", {id: id,action:"delete"}, function(data){
            	closeFlush();
            }, null, true);
        }

        function cancel() {
//            $.messager.confirm('提示', '是否要关闭？', function (r) {
//                if (r) {
//                   
//                }
//            });
            $("#managerDialog").dialog('close');
        }

        function query() {
            $('#grid1').datagrid('options').queryParams = serializeObject($('#searchForm'));
            $('#grid1').datagrid("reload");
        }

        function reset() {
            searchForm.reset();
        }

        function closeFlush() {
            managForm.reset();
            $("#managerDialog").dialog('close');
            $("#grid1").datagrid("reload");
        }

        function inputCheck() {
            if ($("#passwd").val() != $("#password2").val()) {
                $.messager.alert("提示", "两次输入密码不一致!");
                return false;
            } else if (!($("#managForm").form("validate"))) {
                return false;
            }
            openBackGround();
            return true;
        }
    </script>
</head>
<body>
<div id="main" class="easyui-layout" fit="true" style="width:100%;height:100%;">
    <div region="north" id="" style="height:70px;" border="false" title="查询条件">
            <form action="" id="searchForm" name="searchForm" method="post">
                <table cellpadding="1" cellspacing="0" class="tb_search">
                    <tr>
                        <td width="10%">
                            <label for="susername">用户名：</label>
                            <input type="text" id="susername" name="searchStr" width="100%" maxlength="32"/>
                        </td>

                        <td width="10%">
                            <a href="#" onclick="query();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
                            <a href="#" onclick="reset();" class="easyui-linkbutton" iconCls="icon-redo">重置</a>
                        </td>
                    </tr>
                </table>
            </form>
    </div>
    <div region="center" border="false" style="overflow:hidden;">

        <table id="grid1"></table>

    </div>
</div>


<div id="managerDialog" class="easyui-dialog" title="用户管理" style="width:500px;height:290px;" toolbar="#dlg-toolbar"
     buttons="#dlg-buttons2" resizable="true" modal="true" closed='true'>
    <form id="managForm" name="managForm" method="post">
        <input type="hidden" id="action" name="action"/>
        <input type="hidden" id="id" name="id"/>
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
                <th width="10%" align="right"><label>邮箱：</label></th>
                <td width="10%">
                    <input id="email" name="email" class="easyui-validatebox" required="true" validType="length[0,32]"
                           style="width:300px"/>
                </td>
            </tr>
            
            <tr>
                <th width="10%" align="right"><label>姓名：</label></th>
                <td width="10%">
                    <input id="name" name="name" class="easyui-validatebox" required="true" validType="length[0,32]"
                           style="width:300px"/>
                </td>
            </tr>
            
            <tr>
                <th width="10%" align="right"><label>角色：</label></th>
                <td width="30%">
                    <select id="roletype" name="roletype" class="easyui-combobox" panelHeight="200"
                            style="width:300px;word-wrap: break-word;word-break:break-all;" type="text" required="true"
                            validType="length[0,32]">
                        <option value="1" selected="selected">管理员</option>
                        <option value="2">普通用户</option>
                    </select></td>
            </tr>

        </table>
    </form>
    <div id="dlg-buttons2">
        <a href="#" class="easyui-linkbutton" onclick="save();">保存</a>
        <a href="#" class="easyui-linkbutton" onclick="cancel();">取消</a>
    </div>
</div>

</body>
</html>