function valForm(){
    var userName=document.getElementById("userName");
    var pwd=document.getElementById("password");
    var repwd=document.getElementById("password1");
    var email=document.getElementById("email");
    // var age=document.getElementById("age");
    // if(userName.value==""){
    //     alert("用户名不能为空");
    //     return false;
    // }else if(userName.value.lenth<4||userName.value.lenth>16){
    //     alert("用户名长度不符合要求\n用户名长度为4-16个字符");
    //     return false;
    // }else if(pwd.value!=repwd.value){
    //     alert("两次密码不一致");
    //     return false;
    // }
    // else if(email.value.indexOf(".")<0||email.value.indexOf("@")<0){
    //     alert("邮箱名错误")
    //     return false;
    // }else if(parseInt(age.value)<18||parseInt(age.value)>80){
    //     alert("不符合年龄");
    //     return false;
    // }else{
        document.forms[0].action="../../../login/login.do";
        document.forms[0].submit();
        return false;
    // }
}