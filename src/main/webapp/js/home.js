//当登录结束进入主页面之后执行的js

//页面加载时检测登录
$(document).ready(function (){
    checkLogin();
});

function checkLogin() {
    $.ajax({
        url:"CheckLoginServlet",
        type:'get',
        dataType:'json',
        success: function (data){
            if(data.isLogin){
            }else{
                alert("您未登录，请返回登陆界面登录");
                window.location.href = 'login.html';
            }
        }
    })
}