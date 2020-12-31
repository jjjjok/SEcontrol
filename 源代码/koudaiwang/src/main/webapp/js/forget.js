var flag=true;
function FocusItem(obj){
    $(obj).next('span').html('').removeClass('empty');


}

function CheckItem(obj) {
    var msgBox = $(obj).next('span');
    switch ($(obj).attr('name')) {
        case "username":
            if (obj.value == "") {
                msgBox.html('用户名不能为空');
                msgBox.addClass('empty');
                flag = false;

            } else {
                var url = "usernamecheck?username=" + encodeURI($(obj).val()) + "&" + new Date().getTime();
                $.get(url, function (data) {

                    if (data == "true") {
                        msgBox.html('用户名不存在');
                        msgBox.addClass('empty');
                        flag = false;
                    } else {
                        msgBox.html().removeClass('empty')
                        flag = true;
                    }
                });
            }
            break;
        case "userphone":

            if (obj.value == "") {
                msgBox.html('手机号不能为空');
                msgBox.addClass('empty');
                flag = false;
            }else if(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(obj.value))){
                msgBox.html('手机号必须为11位数字');
                msgBox.addClass('empty');
                flag = false;
            }

            else{
                var url = "userphonecheck?userphone=" + encodeURI($(obj).val()) + "&" + new Date().getTime()+"&username="+document.getElementById("username").value;
                $.get(url, function (data) {

                    if (data == "true") {
                        msgBox.html('手机号与用户名不匹配');
                        msgBox.addClass('empty');
                        flag = false;
                    } else {
                        msgBox.html().removeClass('empty')
                        flag = true;
                    }
                });
            }
            break;

        case "userpwd":
            if (obj.value == "") {
                msgBox.html('密码不能为空');
                msgBox.addClass('empty');
                flag = false;
            }else{
                flag = true;
            }
            break;
        case "userpwd1":
            if (obj.value == "") {
                msgBox.html('确认密码不能为空');
                msgBox.addClass('empty');
                flag = false;
            } else if ($(obj).val() != $('input[name="userpwd"]').val()) {
                msgBox.html('两次密码必须一致');
                msgBox.addClass('empty');
                flag = false;
            }else{
                flag = true;
            }
            break;
    }
}
function CheckForm(frm){
    var els= frm.getElementsByTagName('input');
    for(var i=0;i<els.length;i++){
        if(els[i]!=null){
            if(els[i].getAttribute("onblur")){
                CheckItem(els[i]);
            }
        }
    }
    return flag;
}
