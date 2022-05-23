$(document).ready(function () {
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

function deleteButton(e) {
    // console.log("获取到了当前定义的data-type：",e.getAttribute("data-deleteNum"));
    window.location.href = "CourseManageServlet?delete=" + e.getAttribute("data-deleteNum");
}

function searchCourse() {
    let message = $('#search-input').val();
    console.log(message);
    window.location.href = "CourseManageServlet?search=" + message;
}

function pageForwardStudent() {
    let page = $('#pageNow').text().trim();
    if (page > 1) {
        page--;
    }
    window.location.href = "UserManageServlet?userType=Student&page=" + page;
}

function pageBackwardStudent() {
    let page = $('#pageNow').text().trim();
    let pageAll = $('#pageAll').text().trim();
    if (page + 1 <= pageAll) {
        page++;
    }
    window.location.href = "UserManageServlet?userType=Student&page=" + page;
}

function searchStudent() {
    let message = $('#search-input').val();
    console.log(message);
    window.location.href = "AddStudentServlet?search=" + message;
}

function pageForwardTeacher() {
    let page = $('#pageNow').text().trim();
    if (page > 1) {
        page--;
    }
    window.location.href = "UserManageServlet?userType=Teacher&page=" + page;
}

function pageBackwardTeacher() {
    let page = $('#pageNow').text().trim();
    let pageAll = $('#pageAll').text().trim();
    if (page + 1 <= pageAll) {
        page++;
    }
    window.location.href = "UserManageServlet?userType=Teacher&page=" + page;
}

function searchTeacher() {
    let message = $('#search-input').val();
    console.log(message);
    window.location.href = "AddTeacherServlet?search=" + message;
}

function addStudent() {
    let username = $('#add-userName').val();
    let password = $('#add-password').val();
    let studentName = $('#add-studentName').val();
    let gender = $('#add-gender').val();
    let facultyNum = $('#add-facultyNum').val();
    let birth = $('#add-birth').val();

    $.ajax({
        url: 'AddStudentServlet',
        data:{username:username,password:password,studentName:studentName,gender:gender,facultyNum:facultyNum,birth:birth},
        dataType: 'json',
        type:'post',
        success:function (data){
            console.log(data.exist);
            if(data.exist){
                alert("用户名重复，请重新输入");
            }else{
                window.location.href = "UserManageServlet?userType=Student";
            }
        }
    });

}

function addTeacher() {
    let username = $('#add-username').val();
    let password = $('#add-password').val();
    let teacherName = $('#add-teacherName').val();
    let gender = $('#add-gender').val();
    let facultyNum = $('#add-facultyNum').val();
    let birth = $('#add-birth').val();

    $.ajax({
        url: 'AddTeacherServlet',
        data:{username:username,password:password,teacherName:teacherName,gender:gender,facultyNum:facultyNum,birth:birth},
        dataType: 'json',
        type:'post',
        success:function (data){
            console.log(data.exist);
            if(data.exist){
                alert("用户名重复，请重新输入");
            }else{
                window.location.href = "UserManageServlet?userType=Teacher";
            }
        }
    });

}

