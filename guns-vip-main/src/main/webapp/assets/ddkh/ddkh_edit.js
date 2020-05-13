/**
 * 详情对话框
 */
var DdkhInfoDlg = {
    data: {
        xm: "",
        knUserId:"",
        hljd:"",
        hlrq:"",
        zfzr:"",
        jfdj: "",
        bz:""
    }
};

layui.use(['form', 'admin', 'ax','upload','laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;
    var laydate = layui.laydate;
    var $ = layui.$;


    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/ddkh/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('ddkhForm', result.data);

    /*总负责人*/
    var usersax=new $ax(Feng.ctxPath+"/knuser/getallusers");
    var users=usersax.start();
    for(var i=0;i<users.data.length;i++){
        $("#zfzr").append("<option value='"+users.data[i].userId+"'>"+users.data[i].name+"</option>");
    }
    $("#zfzr").val(result.data.zfzrid);
    form.render();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/ddkh/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/ddkh'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/ddkh'
    });

    //日期控件
    laydate.render({
        elem: '#hlrq'
    });

});



