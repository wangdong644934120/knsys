$(function () {
    var sendData={"code":getUrlParam("code")};
    console.log(getUrlParam("code"));
    $.ajax({
        type:"get",
        url:"/assets/getcode",
        data:sendData,
        datatype:"json",
        success:function(msg){
            msg=eval("(" + msg + ")");
            console.log(msg+"  "+msg.openid);
            window.location.href = msg.url+"?openid="+msg.openid;
        },
        error:function(msg){
            alert("通信失败");
        }

    });
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

// console.log(getUrlParam("code"));
// layui.use(['layer', 'ax'], function () {
//     var $ = layui.jquery;
//     var layer = layui.layer;
//     var $ax = layui.ax;
//
//     var ajax = new $ax("/assets/getcode?code="+getUrlParam("code"));
//     var result = ajax.start();
//     console.log(result);
// });

