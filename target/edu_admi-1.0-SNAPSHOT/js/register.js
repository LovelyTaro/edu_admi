/**
 * @author Dcy
 */

//提交登录前的判断，如果都不为空则提交登录
function loginSubmit() {
    if (checkUsernameIsEmpty() && checkPassword()) {
        let usernameInfo = $('#usernameInfo').val();
        let passwordInfo = $('#passwordInfo').val();
        let idInfo = $('#idInfo').val();

        //用ajax向后台提交表单
        $.ajax({
            url: 'LoginServlet',
            type: 'post',
            data: {usernameInfo: usernameInfo, passwordInfo: passwordInfo, idInfo: idInfo},
            dataType: 'json',
            success: function (data) {
                if (data.loginResult) {
                    if(idInfo === "管理员"){
                        window.location.href = 'home_adm.jsp';
                    }
                    if(idInfo === "老师"){
                        window.location.href = 'home_tea.jsp';
                    }
                    if(idInfo === "学生"){
                        window.location.href = 'home_stu.jsp';
                    }
                } else {
                    if ($('#loginError').length > 0) {
                    } else {
                        const $p = $("<p id='loginError' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*用户名或密码错误</p>");
                        $p.insertAfter($('#passwordInfo'));
                    }
                }
            }
        })

    } else {
        alert("请检查所有信息")
    }

}

//注册：检查注册时所有项是否正确
function checkAll() {
    if (checkUsernameIsEmpty() && checkPassword() && checkPasswordAgain() && checkUsername()) {

        let usernameInfo = $('#usernameInfo').val();
        let passwordInfo = $('#passwordInfo').val();
        let idInfo = $('#idInfo').val();

        //用ajax向后台提交表单
        $.ajax({
            url: 'RegisterServlet',
            type: 'post',
            data: {usernameInfo: usernameInfo, passwordInfo: passwordInfo, idInfo: idInfo},
            dataType: 'json',
            success: function (data) {
                if (data.registerResult) {
                    console.log()
                    window.location.href = 'home_adm.jsp';
                } else {
                    if ($('#registerError').length > 0) {
                    } else {
                        const $p = $("<p id='registerError' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*注册失败</p>");
                        $p.insertAfter($('#passwordAgain'));
                    }
                }
            }
        })
    } else {
        alert("请检查所有信息")
        return false;
    }
}


//登录：注册：检查密码是否为空
function checkPassword() {
    //检查密码是否为空
    let password = $('#passwordInfo').val();
    if (password === '') {
        if ($('#passwordIsEmpty').length > 0) {
            return false;
        } else {
            const $p = $("<p id='passwordIsEmpty' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*密码不能为空</p>");
            $p.insertAfter($('#passwordInfo'));
            return false;
        }
    }
    //如果不是空则删除提示信息
    else if ($('#passwordIsEmpty').length > 0) {
        $('#passwordIsEmpty').remove();
        return true;
    } else {
        return true;
    }

}

//注册：检查两次密码输入是否正确
function checkPasswordAgain() {
    let password = $('#passwordInfo').val();
    let passwordAgain = $('#passwordAgain').val();
    //先验证第二次输入的是否为空
    if (passwordAgain === '') {
        if ($('#passwordError').length > 0) {
            $('#passwordError').remove();
            const $p = $("<p id='passwordAgainIsEmpty' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*请再次输入密码</p>");
            $p.insertAfter($('#passwordAgain'));
        } else if ($('#passwordAgainSuccess').length > 0) {
            $('#passwordAgainSuccess').remove();
            const $p = $("<p id='passwordAgainIsEmpty' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*请再次输入密码</p>");
            $p.insertAfter($('#passwordAgain'));
        } else if ($('#passwordAgainIsEmpty').length > 0) {
        } else {
            const $p = $("<p id='passwordAgainIsEmpty' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*请再次输入密码</p>");
            $p.insertAfter($('#passwordAgain'));
        }
        return false;
    }
    //验证两次密码是否相同
    else if (password === passwordAgain) {
        if ($('#passwordError').length > 0) {
            $('#passwordError').remove();
            const $p = $("<p id='passwordAgainSuccess' style='color: green;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*两次密码输入正确</p>");
            $p.insertAfter($('#passwordAgain'));
        } else if ($('#passwordAgainIsEmpty').length > 0) {
            $('#passwordAgainIsEmpty').remove();
            const $p = $("<p id='passwordAgainSuccess' style='color: green;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*两次密码输入正确</p>");
            $p.insertAfter($('#passwordAgain'));
        } else if ($('#passwordAgainSuccess').length > 0) {
        } else {
            const $p = $("<p id='passwordAgainSuccess' style='color: green;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*两次密码输入正确</p>");
            $p.insertAfter($('#passwordAgain'));
        }
        return true;
    }
    //剩余的情况则就是两次密码不相同了
    else {
        if ($('#passwordAgainIsEmpty').length > 0) {
            $('#passwordAgainIsEmpty').remove();
            const $p = $("<p id='passwordError' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>两次密码不相同</p>");
            $p.insertAfter($('#passwordAgain'));
        } else if ($('#passwordAgainSuccess').length > 0) {
            $('#passwordAgainSuccess').remove();
            const $p = $("<p id='passwordError' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>两次密码不相同</p>");
            $p.insertAfter($('#passwordAgain'));
        } else if ($('#passwordError').length > 0) {
        } else {
            const $p = $("<p id='passwordError' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>两次密码不相同</p>");
            $p.insertAfter($('#passwordAgain'));
        }
        return false;
    }
}

//注册：检查用户名是否重复
function checkUsername() {
    let username = $('#usernameInfo').val();
    //先验证用户名是否为空
    if (username === '') {
        if ($('#usernameIsEmpty').length > 0) {
        } else {
            const $p = $("<p id='usernameIsEmpty' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*用户名不能为空</p>");
            $p.insertAfter($('#usernameInfo'));
        }
        return false;
    } else {
        if ($('#usernameIsEmpty').length > 0) {
            $('#usernameIsEmpty').remove();
        }
        //如果用户名不是空则使用ajax去验证用户名是否重复
        $.ajax({
            url: 'CheckNameAjaxServlet',
            type: 'get',
            data: {username: username},
            dataType: 'json',
            success: function (data) {
                //通过返回data判断用户名是否存在，如果存在输出*用户名可用，反之输出*用户名重复
                if (data.exist) {
                    //创建错误信息
                    //判断当前界面上是否存在在error-message或success-message,如果有error-message则不变，如果有success-message则删除
                    if ($('#success-message').length > 0) {
                        $('#success-message').remove()
                        const $p = $("<p id='error-message' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*用户名重复</p>");
                        $p.insertAfter($('#usernameInfo'));
                    }
                    if ($('#error-message').length > 0) {
                    } else {
                        //创建error-message结点并加入到usernameInfo后面
                        const $p = $("<p id='error-message' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*用户名重复</p>");
                        $p.insertAfter($('#usernameInfo'));
                    }
                } else {
                    //创建成功信息
                    //判断当前界面上是否存在在error-message或success-message，如果有error-message则删除，如果有success-message则不变
                    if ($('#error-message').length > 0) {
                        $('#error-message').remove()
                        const $p = $("<p id='success-message' style='color: green;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*用户名可用</p>");
                        $p.insertAfter($('#usernameInfo'));
                    } else if ($('#success-message').length > 0) {
                    } else {
                        //创建success-message结点并加入到usernameInfo后面
                        const $p = $("<p id='success-message' style='color: green;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*用户名可用</p>");
                        $p.insertAfter($('#usernameInfo'));
                    }
                }
            }
        })
        return $('#success-message').length > 0;
    }
}

//检查用户名是否为空
function checkUsernameIsEmpty() {
    let username = $('#usernameInfo').val();
    if (username === '') {
        if ($('#usernameIsEmpty').length > 0) {
            return false;
        } else {
            const $p = $("<p id='usernameIsEmpty' style='color: red;font-size: 16px;margin-top: 5px;margin-bottom: -2px'>*用户名不能为空</p>");
            $p.insertAfter($('#usernameInfo'));
            return false;
        }
    } else {
        if ($('#usernameIsEmpty').length > 0) {
            $('#usernameIsEmpty').remove();
            return true;
        }
        return true;
    }
}

