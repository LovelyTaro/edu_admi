
$(document).ready(function(){
    //登录检查
    // checkLogin();

});

function checkLogin() {
    //ajax检查登录
    $.ajax({
        url: "CheckLoginServlet",
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.isLogin) {
            } else {
                alert("您未登录，请返回登陆界面登录");
                window.location.href = 'login.html';
            }
        }
    })
}

//退出登录
function logout(){
    $.ajax({
        url:'LoginServlet',
        type:'get',
        success: function (){
            window.location.reload();
        }
    })

}

$(function(){
    $("#ad_setting").click(function(){
        $("#ad_setting_ul").show();
    });
    $("#ad_setting_ul").mouseleave(function(){
        $(this).hide();
    });
    $("#ad_setting_ul li").mouseenter(function(){
        $(this).find("a").attr("class","ad_setting_ul_li_a");
    });
    $("#ad_setting_ul li").mouseleave(function(){
        $(this).find("a").attr("class","");
    });
});

