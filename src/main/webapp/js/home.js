$(document).ready(function () {

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
function logout() {
    $.ajax({
        url: 'LoginServlet',
        type: 'get',
        success: function () {
            window.location.reload();
        }
    })
}

function pageForward() {
    let page = $('#pageNow').text().trim();
    if (page > 1) {
        page--;
    }
    window.location.href = "CourseManageServlet?page=" + page;
}

function pageBackward() {
    let page = $('#pageNow').text().trim();
    let pageAll = $('#pageAll').text().trim();
    if (page + 1 <= pageAll) {
        page++;
    }
    window.location.href = "CourseManageServlet?page=" + page;
}


