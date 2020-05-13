/**
 * 详情对话框
 */
var EcgdInfoDlg = {
    data: {
        xm: "",
        knUserId:"",
        hljd:"",
        jdr:"",
        hlrq:"",
        jdrq:"",
        tdjl:"",
        gdr: "",
        gdjg: "",
        gdjl: "",
        cjrq: "",
        jfdj: "",
        zt:""
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



    //让当前iframe弹层高度适应
    //admin.iframeAuto();


    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/ecgd/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('ecgdForm', result.data);
    /*跟单人*/
    var usersax=new $ax(Feng.ctxPath+"/knuser/getallusers");
    var users=usersax.start();
    for(var i=0;i<users.data.length;i++){
        $("#ecgdr").append("<option value='"+users.data[i].userId+"'>"+users.data[i].name+"</option>");
    }
    $("#ecgdr").val(result.data.ecgdrid);
    form.render();

    if($("#ecgdjg").val()!=''){
        $("#ecgdjg").attr("disabled","disabled");
        form.render("select");
    }

    // var pics=new $ax(Feng.ctxPath+"/ecgd/getPic?tpzlId="+Feng.getUrlParam("id"));
    // var respics=pics.start();
    // console.log(respics);
    // for(var i=0;i<respics.data.length;i++){
    //     $('#demo2').append('<img src="data:image/jpeg;base64,'+ respics.data[i] +'"  class="layui-upload-img" style="width:50px" onclick="openFile(this.src);"" ></img>');
    // }

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/ecgd/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/ecgd'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/ecgd'
    });

    // //多图片上传
    // upload.render({
    //     elem: '#test2'
    //     ,url: '/ecgd/uploadPicture' //改成您自己的上传接口
    //     ,multiple: true
    //     ,before: function(obj){
    //         //预读本地文件示例，不支持ie8
    //
    //     }
    //     ,done: function(res){
    //         //上传完毕
    //         console.log(res.data.imgBase64);
    //         //$("#activityTypeIcon").attr("src", "data:image/jpeg;base64," + res.data.imgBase64);
    //         $('#demo2').append('<img src="data:image/jpeg;base64,'+ res.data.imgBase64 +'"  class="layui-upload-img" style="width:50px;margin-left:3px;margin-right: 3px" onclick="openFile(this.src);"" ></img>');
    //         var ajaxfile = new $ax(Feng.ctxPath + "/ecgd/addPic?tpzlId=" + Feng.getUrlParam("id")+"&tplj="+res.data.fileUUID);
    //         var res = ajaxfile.start();
    //     }
    // });
    //日期控件
    laydate.render({
        elem: '#hlrq'
    });
    laydate.render({
        elem: '#eccjrq'
    });






});

function openFile(e){
/*    var src = '<img id="imgfd" src="'+e+'" style="max-width:100%; max-height:100%" />';

   layer.open({
        type: 1,
        title: false,
        area:['auto','auto'],
        closeBtn: 0,
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: src,
        resize:true,
        maxmin:true
    });*/

        //调用示例
        layer.photos({
            photos: '#demo2',
            anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
        });



    $(document).on("mousewheel", "#demo2", function (e) {
        console.log("gudnong");
         var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) || // chrome & ie
             (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1)); // firefox
         var imagep = $("#demo2").parent();
         var image = $("#demo2");
         var h = image.height();
         var w = image.width();
         if (delta > 0) {
             if (h < (window.innerHeight)) {
                 h = h * 1.05;
                 w = w * 1.05;
             }
         } else if (delta < 0) {
             if (h > 100) {
                 h = h * 0.95;
                 w = w * 0.95;
             }
         }
         /*imagep.css("top", (window.innerHeight - h) / 2);
         imagep.css("left", (window.innerWidth - w) / 2);*/
         image.height(h);
         image.width(w);
         imagep.height(h);
         imagep.width(w);
         e.stopPropagation();
         e.cancelBubble=true;
         e.preventDefault()
    });

}


