<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<jsp:include page="/view/commons.jsp"></jsp:include>


<head>

    <title>管理</title>

</head>

<body>

<div style="margin: 20px 0;"></div>
<div class="easyui-layout" style="width: 100%; height: 660px;">

    <div data-options="region:'north'" style="height: 50px">

        当前用户为：

    </div>
    <div data-options="region:'south',split:true" style="height: 50px;"></div>
    <div data-options="region:'east',split:true" title="East" style="width: 100px;"></div>

    <div data-options="region:'west',split:true" title="左侧" style="width: 200px;">
        <a href="/pages/resume/list.jsp" style="border-left: 1">订单页面</a>
        </br>
        <a href="/pages/resume/list.jsp" style="border-left: 2">订单页面</a>
        </br>
        <a href="/pages/resume/list.jsp" style="border-left: 3">订单页面</a>
        </br>
        <a href="/pages/resume/list.jsp" style="border-left: 4">订单页面</a>
        </br>
    </div>




    <div data-options="region:'center',title:'客户列表',iconCls:'icon-ok'"
         style="width: 100px;">

        <div>
            <table>
                <tr>
                    <td>
                        查询条件
                    </td>

                </tr>
            </table>
        </div>

        <table id="dg" style="height: 100%; width: 100%;"></table>
    </div>


    <script type="text/javascript">

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
                    frozenColumns: [[{
                        field: 'ck',
                        checkbox: true
                    }]],
                    pagination: true, //包含分页
                    pageSize: 10,
                    pageList: [10, 20, 30],//可以设置每页记录条数的列表
                    rownumbers: true,
                    singleSelect: false,//只能单选
                    columns: [[{
                        field: 'id',
                        title: 'Id',
                        align: 'center'
                        // hidden : true
                    }, {
                        field: 'name',
                        title: '客户名称',
                        align: 'center'
                    }, {
                        field: 'phone',
                        title: 'phone',
                        align: 'center'
                    }]],

                    onLoadError: function () {
                        $.messager.alert('温馨提示',
                            '数据加载失败!', 'error');
                    }
                });
        });


    </script>
</body>
</html>
