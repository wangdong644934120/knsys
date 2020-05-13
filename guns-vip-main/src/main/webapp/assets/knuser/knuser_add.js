/**
 * 添加或者修改页面
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

    //让当前iframe弹层高度适应
    //admin.iframeAuto();
    var usersax=new $ax(Feng.ctxPath+"/knuser/getallusers");
    var users=usersax.start();
    for(var i=0;i<users.data.length;i++){
        $("#jdr").append("<option value='"+users.data[i].userId+"'>"+users.data[i].name+"</option>");
    }
    renderForm();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/knuser/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/knuser'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
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
            var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
            form.render();
        });
    }

});