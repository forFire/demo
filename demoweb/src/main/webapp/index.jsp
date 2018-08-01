<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="/view/commons/commons.jsp"></jsp:include>
<head>
    <title>管理</title>
    <script type="text/javascript" src="view/customer/index.js"></script>
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

            <div class="row">
                <span class="personattr">身份证正面照片:</span>
                <input type="hidden" name="idCardImg"  id="idCardImg"/>
                <input type="file" name="logoFile" id="logoFile" onchange="setImg(this);">
                <span><img id="idCardImgShow" src="" width="120" height="120"/></span>
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
        <a href="index.jsp" >用户页面</a>
        </br>
        <a href="/view/register/register.html" >注册页面</a>
        </br>
        <a href="/view/login/login.html" >登陆页面</a>
        </br>



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

    </script>
</body>
</html>
