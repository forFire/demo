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
                },
                {
                    field: 'idCardImg',
                    title: '身份证照片',
                    align: 'center',
                    width: 30,
                    styler: function (value, record) {
                        if (value == 'admin') {
                            return 'background:blue;';
                        }
                    },formatter: function(value){
                              return "<img style='width:24px;height:24px;' border='1' src='"+value+"'/>";  ;
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
        $("#idCardImgShow").attr("src",row.idCardImg);//显示图片
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

                    console.info(data)
                    alert(data)

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



function setImg(obj){//用于进行图片上传，返回地址
    var f=$(obj).val();
    if(f == null || f ==undefined || f == ''){
        return false;
    }
    if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
    {
        alertLayel("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return false;
    }
    var data = new FormData();
    $.each($(obj)[0].files,function(i,file){
        data.append('file', file);
    });
    $.ajax({
        type: "POST",
        url: "cumstomer/uploadImg.do",
        data: data,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        dataType:"json",
        success: function(suc) {
            if(suc.code==0){
                $("#idCardImg").val(suc.message);//将地址存储好
                $("#idCardImgShow").attr("src",suc.message);//显示图片
            }else{
                alertLayel("上传失败");
                $("#idCardImg").val("");
                $(obj).val('');
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alertLayel("上传失败，请检查网络后重试");
            $("#url").val("");
            $(obj).val('');
        }
    });
}