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

    <script type="text/javascript">
        $(function () {
            $('#grid1').datagrid({
                title: '列表',
                nowrap: true,
                striped: true,
                border:false,
                fit: true,
                url: __APP__+"TuijianServlet",
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
	
                        {title: '分类', field: 'category', width: 100, sortable: true},
                        				{title: '点赞数', field: 'dianzan', width: 100, sortable: true},
                          {title: '图片', field: 'img', width: 100, sortable: true,formatter:function(value, rec){
                        	return "<a href='/upload/"+value+"'>查看图片</a>";
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
      		
				            $("#name").val("");		
							$('#msg').textbox('setValue',"");
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
            
            $("#categoryName").combobox({
		        method:"get",
		        url:__APP__+"CategoryServlet?action=select",
		        valueField: 'name',
		        textField: 'name'
		    });
        });

        function save() {
            $('#managForm').form('submit', {
                url: __APP__+"TuijianServlet",
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
        
        
//		  包含图片上传        
        function save() {
        uploadFile($("#fileForm"),function(filename){
	                   	$("#img").val(filename);
            $('#managForm').form('submit', {
                url: __APP__+"TuijianServlet",
               
                success: function (data) {
                    closeBackGround();
                    if (data == "success") {
                        $.messager.alert("提示", "操作成功！", "info", function () {
                            closeFlush();
                        });
                    }
                }
            });
            });
        }
        
        

        function edit(obj) {
            
			$('#category').combobox('setValue',obj.category);
			

       
            $("#id").val(obj.id);
            $("#managerDialog").dialog('open');
            
        }

        function deleteItem(id) {
            ajaxCallBack("TuijianServlet", {id: id,action:"delete"}, function(data){
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
                            <label for="susername">分类：</label>
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


<div id="managerDialog" class="easyui-dialog" title="管理" style="width:500px;height:290px;" toolbar="#dlg-toolbar"
     buttons="#dlg-buttons2" resizable="true" modal="true" closed='true'>
    <form id="managForm" name="managForm" method="post">
        <input type="hidden" id="action" name="action"/>
        <input type="hidden" id="id" name="id"/>
        <table cellpadding="1" cellspacing="1" class="tb_custom1">
        
         
           	<tr>
                <th width="15%" align="right"><label>分类：</label></th>
                <td width="30%">
                    <select id="category" name="category" class="easyui-combobox" panelHeight="200"
                            style="width:300px;word-wrap: break-word;word-break:break-all;" type="text" required="true"
                            validType="length[0,32]">
                        <option value="Party" selected="selected">Party</option>
                        <option value="Company">Company</option>
                        <option value="School">School</option>
                        <option value="Exercise">Exercise</option>
                        <option value="Home">Home</option>
                    </select></td>
            </tr>
            
            
       
            
            <tr>
                <th width="15%" align="right"><label>图片：</label></th>
                <td width="10%">
                	<input id="file" form="fileForm" type="file" name="file" class="easyui-validatebox" style="width:300px"/>
                	<input id="img" name="img" type="hidden">
                </td>
            </tr>


        </table>
    </form>
     <form id="fileForm" name="fileForm" method="post"  enctype="multipart/form-data"></form>
    <div id="dlg-buttons2">
        <a href="#" class="easyui-linkbutton" onclick="save();">保存</a>
        <a href="#" class="easyui-linkbutton" onclick="cancel();">取消</a>
    </div>
</div>

</body>
</html>