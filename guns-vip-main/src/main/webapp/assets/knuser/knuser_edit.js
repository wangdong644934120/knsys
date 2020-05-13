/**
 * 详情对话框
 */
var UserInfoDlg = {
    data: {
        xlxm: "",
        xldh:"",
        xfdz:"",
        xnxm:"",
        xndh:"",
        njdz:"",
        hljd: "",
        jdr: "",
        hlrq: "",
        jdrq: "",
        tdjl: "",
        description: "",
        cjrq: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var $=layui.$;

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/knuser/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('userForm', result.data);


    var usersax=new $ax(Feng.ctxPath+"/knuser/getallusers");
    var users=usersax.start();
    for(var i=0;i<users.data.length;i++){
        $("#jdr").append("<option value='"+users.data[i].userId+"'>"+users.data[i].name+"</option>");
    }
    $("#jdr").val(result.data.jdr);
    renderForm();


    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/knuser/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/knuser'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/knuser'
    });

    //日期控件
    laydate.render({
        elem: '#hlrq'
    });
    laydate.render({
        elem: '#jdrq'
    });
    function renderForm(){
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
    }
});