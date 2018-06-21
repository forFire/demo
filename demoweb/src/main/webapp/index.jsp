<html>

<jsp:include page="/view/commons.jsp"></jsp:include>


<head>

    <title>管理</title>

</head>

<body>

<div data-options="region:'center',title:'客户列表',iconCls:'icon-ok'"
     style="width: 100px;">
    <table id="dg" style="height: 100%; width: 100%;"></table>
</div>


<script type="text/javascript">
    $(document).ready(function() {//datagrid设置
        $('#dg').datagrid(
            { // 													title : '招聘列表', //表格标题
                iconCls : 'icon-list', //表格图标
                nowrap : false, //是否只显示一行，即文本过多是否省略部分。
                striped : true,
                fitColumns : true, //防止水平滚动
                scrollbarSize : 0, //去掉右侧滚动条列
                url: "test/test1.do",
                idField : 'code',
                collapsible : false,//是否可折叠的
                singleSelect : true,//只能单选
                frozenColumns : [ [ {
                    field : 'code',
                    checkbox : true
                } ] ],
                pagination : true, //包含分页
                pageSize : 10,
                pageList : [ 10, 20, 30 ],//可以设置每页记录条数的列表
                rownumbers : true,
                singleSelect : false,//只能单选
                columns : [ [{
                    field : 'code',
                    title : 'ID',
                    align : 'center',
                    hidden : true
                }, {
                    field : 'name',
                    title : '客户名称',
                    align : 'center'
                },{
                    field : 'price',
                    title : '地址',
                    align : 'center'
                }]],

                toolbar : [],
                onLoadError : function() {
                    $.messager.alert('温馨提示',
                        '数据加载失败!', 'error');
                }
            });
    });



</script>
</body>
</html>
