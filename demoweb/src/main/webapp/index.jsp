<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="/view/commons.jsp"></jsp:include>
<head>
    <title>管理</title>

</head>
<body>


<!--信息编辑弹框开始-->
<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post">

            <div class="fitem">

                <label>
                    名称:
                </label>
                 <input name="name" class="easyui-validatebox" required="true"/>
            </div>

            <div class="fitem">
                <label>
                   手机号:
                </label>
                <input name="phone" class="easyui-validatebox" required="true"/>
            </div>
            <%--<div class="fitem">--%>
                <%--<label>--%>
                    <%--创建时间</label>--%>
                <%--<input name="CreateTime" class="easyui-datebox" required="true"/>--%>
            <%--</div>--%>
            <%--<div class="fitem">--%>
                <%--<label>--%>
                    <%--创建人</label>--%>
                <%--<input name="CreateName" class="easyui-vlidatebox"/>--%>
            <%--</div>--%>
            <input type="" name="id" id="id"/>

        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"
           iconcls="icon-cancel">取消</a>
    </div>

<!--信息编辑弹框结束-->
<div style="margin: 20px 0;"></div>
<div class="easyui-layout" style="width: 100%; height: 660px;">
    <div data-options="region:'north'" style="height: 50px">
        当前用户为：
    </div>
    <div data-options="region:'south',split:true" style="height: 50px;"></div>
    <div data-options="region:'east',split:true" title="East" style="width: 100px;"></div>

    <div data-options="region:'west',split:true" title="左侧" style="width: 200px;">
        <a href="/pages/resume/list.jsp" style="border-left: 1">订单页面</a>
   <%--     </br>
        <a href="/pages/resume/list.jsp" >订单页面</a>
        </br>
        <a href="/pages/resume/list.jsp" >订单页面</a>
        </br>
        <a href="/pages/resume/list.jsp" >订单页面</a>
        </br>--%>
    </div>
    <div data-options="region:'center',title:'客户列表',iconCls:'icon-ok'" style="width: 100px;">
        <div>
            <table>
                <tr>
                    <td>
                        查询条件
                    </td>
                </tr>

                <tr>
                    <div id="tb" style="height:auto">
                       <%-- <a href="javascript:void(0)" class="easyui-linkbutton"
                           data-options="iconCls:'icon-add',plain:true" onclick="append()">行增加</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                           data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">行删除</a>--%>
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                           data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
                           <a href="javascript:void(0)" class="easyui-linkbutton"
                              data-options="iconCls:'icon-save',plain:true" onclick="edit()">编辑</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                           data-options="iconCls:'icon-save',plain:true" onclick="delete1()">删除</a>
                    </div>

                </tr>
            </table>
        </div>

        <table id="dg" style="height: 100%; width: 100%;"></table>

    </div>

    <script type="text/javascript">

        var type;

        $(document).ready(function () {//datagrid设置
            $('#dg').datagrid(
                { // 													title : '招聘列表', //表格标题
                    iconCls: 'icon-list', //表格图标
                    nowrap: false, //是否只显示一行，即文本过多是否省略部分。
                    striped: true,
                    fitColumns: true, //防止水平滚动
                    scrollbarSize: 0, //去掉右侧滚动条列
                    url: 'cumstomer/findList.do', //action地址
                    idField: 'id',
                    collapsible: false,//是否可折叠的
                    singleSelect: true,//只能单选
                    iconCls: 'icon-edit',
                    toolbar: '#tb',
                    frozenColumns: [[{
                        field: 'ck',
                        checkbox: true
                    }]],
                    pagination: true, //包含分页
                    pageSize: 10,
                    pageList: [10, 20, 30],//可以设置每页记录条数的列表
                    rownumbers: true,
                    columns: [[{
                        field: 'id',
                        title: 'Id',
                        align: 'center',
                        width: 20
                    }, {
                        field: 'name',
                        title: '客户名称',
                        align: 'center',
                        width: 30,
                        styler: function (value, record) {
                            if (value == 'admin') {
                                return 'background:blue;';
                            }
                        },
                        editor: {
                            type: 'validatebox',
                            options: {
                                required: true,
                                missingMessage: '客户名称必填!'
                            }
                        }
                    }, {
                        field: 'phone',
                        title: 'phone',
                        width: 30,
                        align: 'center'
                    }]],

                    onLoadError: function () {
                        $.messager.alert('温馨提示',
                            '数据加载失败!', 'error');
                    }
                });
        });



        function add() {
            $("#dlg").dialog("open").dialog('setTitle', '新增');
            ;
            $("#fm").form("clear");
            // url = "UserManage.aspx";   // url = "UserManage.aspx";
            // document.getElementById("hidtype").value = "submit";
        }

        function edit() {
            var row = $("#dg").datagrid("getSelected");
            if (row) {
                $("#dlg").dialog("open").dialog('setTitle', '编辑');
                $("#fm").form("load", row);
                // url = "cumstomer/save.do?id=" + row.ID;
            }
        }

        function save() {
            $("#fm").form("submit", {
                url: "cumstomer/saveorUpdate.do",
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    if (result == "1") {
                        $.messager.alert("提示信息", "操作成功");
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("load");
                    }
                    else {
                        $.messager.alert("提示信息", "操作失败");
                    }
                }
            });
        }

        function delete1() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('Confirm', 'Are you sure you want to destroy this user?', function (r) {
                    if (r) {
                        $.post('cumstomer/delete.do', {id: row.id}, function (data) {
                            alert()
                            if (data != null) {
                                alert(data)
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({   // show error message
                                    title: 'Error',
                                    msg: data.errorMsg
                                });
                            }
                        }, 'json');
                    }
                });
            }
        }


        /**--------------------------------------------------------------------------------------------------**/


     /*   var editIndex = undefined;

        function endEditing() {
            if (editIndex == undefined) {
                return true
            }
            if ($('#dg').datagrid('validateRow', editIndex)) {
                var ed = $('#dg').datagrid('getEditor', {index: editIndex, field: 'productid'});
                var productname = $(ed.target).combobox('getText');
                $('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }

        function onClickRow(index) {
            if (editIndex != index) {
                if (endEditing()) {
                    $('#dg').datagrid('selectRow', index)
                        .datagrid('beginEdit', index);
                    editIndex = index;
                } else {
                    $('#dg').datagrid('selectRow', editIndex);
                }
            }
        }

        function append() {
            if (endEditing()) {
                $('#dg').datagrid('appendRow', {status: 'P'});
                editIndex = $('#dg').datagrid('getRows').length - 1;
                $('#dg').datagrid('selectRow', editIndex)
                    .datagrid('beginEdit', editIndex);
            }
        }

        function removeit() {
            if (editIndex == undefined) {
                return
            }
            $('#dg').datagrid('cancelEdit', editIndex)
                .datagrid('deleteRow', editIndex);
            editIndex = undefined;
        }

        function accept() {
            if (endEditing()) {
                $('#dg').datagrid('acceptChanges');
            }
        }

        function reject() {
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        }

        function getChanges() {
            var rows = $('#dg').datagrid('getChanges');
            alert(rows.length + ' rows are changed!');
        }*/
    </script>
</body>
</html>
