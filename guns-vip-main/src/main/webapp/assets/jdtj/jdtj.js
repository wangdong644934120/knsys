layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var ajax = new $ax(Feng.ctxPath + "/jdtj/list");
    var result = ajax.start();
    var isMobile="0";
    if(Common.isMobile()){
        isMobile=1;
    }
    var total="";
    for(var i=0;i<result.length;i++){
        var divhlrq="&nbsp;&nbsp;婚礼日期:"+result[i].hlrq;
        var divxlxnname=result[i].xlxm+"&"+result[i].xnxm;
        var rwl=getRWL(result[i])+"/14";
        var divheader="<div class=\"layui-card-header\">"+divxlxnname+"&nbsp;&nbsp;"+divhlrq+"&nbsp;&nbsp;&nbsp;&nbsp;婚礼酒店:"+result[i].hljd+
            // "任务完成:"+rwl+"</div>";
         "&nbsp;&nbsp;&nbsp;&nbsp;<span style='float: right'>任务完成:"+rwl+"</span></div>";
        if(isMobile==1){
            divheader="<div class=\"layui-card-header\" style='height:50px;line-height: 25px'>"+divxlxnname+"&nbsp;&nbsp;"+divhlrq+"&nbsp;&nbsp;&nbsp;&nbsp;</br>婚礼酒店:"+result[i].hljd+
                "&nbsp;&nbsp;&nbsp;&nbsp;<span style='float: right'>任务完成:"+rwl+"</span></div>";
        }

        var divtimeline="";
        divtimeline=divtimeline+getli("订单成交",result[i].ddrq,'','总负责人:'+result[i].zfzr,'完成');//订单日期
        //将需要排序的属性放到datas中
        var datas={'syjhwcrq':result[i].syjhwcrq,'hzsjhwcrq':result[i].hzsjhwcrq,'sysjhwcrq':result[i].sysjhwcrq,'sxsjhwcrq':result[i].sxsjhwcrq,
        'hlfajhwcrq':result[i].hlfajhwcrq,'jthyydrq':result[i].jthyydrq,'hchydrq':result[i].hchydrq,'djhy':result[i].djhy,
            'pmsjydrq':result[i].pmsjydrq,'sgzzydrq':result[i].sgzzydrq,'fawcydrq':result[i].fawcydrq,'dzqtydrq':result[i].dzqtydrq,
        'sjapbydrq':result[i].sjapbydrq,'wlcgydrq':result[i].wlcgydrq};
        //排序
        var array = Object.keys(datas).sort(function(a,b){
            var datea=new Date(datas[a]);
            var dateb=new Date(datas[b]);
            return datea-dateb;
        });
        for(var j=0;j<array.length;j++){
            if(array[j]=='syjhwcrq'){
                divtimeline=divtimeline+getli("司仪配置",result[i].syjhwcrq,result[i].syydrq,result[i].sypz,result[i].sywczt);
            }else if(array[j]=='hzsjhwcrq'){
                divtimeline=divtimeline+getli("化妆师配置",result[i].hzsjhwcrq,result[i].hzsydrq,result[i].hzspz,result[i].hzswczt);
            }else if(array[j]=='sysjhwcrq'){
                divtimeline=divtimeline+getli("摄影师配置",result[i].sysjhwcrq,result[i].sysydrq,result[i].syspz,result[i].syswczt);
            }else if(array[j]=='sxsjhwcrq'){
                divtimeline=divtimeline+getli("摄像师配置",result[i].sxsjhwcrq,result[i].sxsydrq,result[i].sxspz,result[i].sxswczt);
            }else if(array[j]=='hlfajhwcrq'){
                divtimeline=divtimeline+getli("婚礼方案",result[i].hlfajhwcrq,result[i].hlfaydrq,"",result[i].hlfazt);
            }else if(array[j]=='jthyydrq'){
                divtimeline=divtimeline+getli("家庭会议",result[i].jthyydrq,"","",result[i].jthywczt);
            }else if(array[j]=='hchydrq'){
                divtimeline=divtimeline+getli("婚车花",result[i].hchydrq,"","",result[i].hchwczt);
            }else if(array[j]=='djhy'){
                divtimeline=divtimeline+getli("搭建会议",result[i].djhy,"","",result[i].djhywczt);
            }else if(array[j]=='pmsjydrq'){
                divtimeline=divtimeline+getli("平面设计",result[i].pmsjydrq,"","",result[i].pmsjwczt);
            }else if(array[j]=='sgzzydrq'){
                divtimeline=divtimeline+getli("手工制作",result[i].sgzzydrq,"","",result[i].sgzzwczt);
            }else if(array[j]=='fawcydrq'){
                divtimeline=divtimeline+getli("方案完成",result[i].fawcydrq,"","",result[i].fawcwczt);
            }else if(array[j]=='dzqtydrq'){
                divtimeline=divtimeline+getli("电子请帖完成",result[i].dzqtydrq,"","",result[i].dzqtwczt);
            }else if(array[j]=='sjapbydrq'){
                divtimeline=divtimeline+getli("时间安排表完成",result[i].sjapbydrq,"","",result[i].sjapbwczt);
            }else if(array[j]=='wlcgydrq'){
                divtimeline=divtimeline+getli("物料采购完成",result[i].wlcgydrq,"","",result[i].wlcgwczt);
            }

        }

        var divbody="<div class=\"layui-card-body\"><ul class=\"layui-timeline\">"+divtimeline+"</ul></div>";
        var divcard="<div class=\"layui-col-md6\" style=\"padding: 10px\"><div class=\"layui-card\">"+divheader+divbody+"</div></div>";
        var divrow="";
        if(i%2==0){
            divrow="<div class=\"layui-row layui-col-space15\">"+divcard;
        }else{
            divrow=divcard+"</div>";
        }
        total=total+divrow;
    }

    $("#all").append(total);
});

function getli(xmmc,ydrq,wcrq,pz,wczt){
    if(ydrq==undefined){
        ydrq="";
    }
    if(wcrq==undefined){
        wcrq="";
    }
    if(pz==undefined){
        pz="";
    }
    if(wczt==undefined){
        wczt="";
    }
    var time=ydrq;
    var contentitem="";
    /*if(time!=''){
        contentitem=contentitem+"<p>计划完成日期:"+time+"</p>"
    }*/
    if(wczt!=''){
        contentitem=contentitem+"<p>完成状态:"+wczt+"</p>";
    }
    if(wcrq!=''){
        contentitem=contentitem+"<p>完成日期:"+wcrq+"</p>";
    }
    if(pz!=''){
        contentitem=contentitem+"<p>"+pz+"</p>";
    }

    var timelinestyle="style='color:black'";


    if(wczt!="完成" && ydrq!=undefined && ydrq!=''){
        var day=GetNumberOfDays(ydrq);
        if(day<=0){
            timelinestyle="style='color:red'";
        }else if(day>0 && day<=3){
            timelinestyle="style='color:#FFCC00'";
        }
    }else if(wczt=='完成'){
        timelinestyle="style='color:green'";
    }
    var content="<div class=\"layui-timeline-content layui-text\"><h4 class=\"layui-timeline-title\" "+timelinestyle+">"+ydrq+"&nbsp;&nbsp"+xmmc+"</h4><p>"+contentitem+"</p>";

    var timelineaxis="<i class=\"layui-icon layui-timeline-axis\""+timelinestyle+"></i>";
    var divtimeline=" <li class=\"layui-timeline-item\">"+timelineaxis+content+"</li>";
    return divtimeline;

}
function GetNumberOfDays(jhrq){//获得天数
    //date1：开始日期，date2结束日期
    var a1 = Date.parse(new Date());
    var a2 = Date.parse(new Date(jhrq));
    var day = parseInt((a2-a1)/ (1000 * 60 * 60 * 24));//核心：时间戳相减，然后除以天数
    //console.log(jhrq,day);
    return day+1;
};
function getRWL(data){
    var wcl=0;
    if(data.sxswczt=='完成'){
        wcl=wcl+1;
    }
    if(data.syswczt=='完成'){
        wcl=wcl+1;
    }
    if(data.sywczt=='完成'){
        wcl=wcl+1;
    }
    if(data.hzswczt=='完成'){
        wcl=wcl+1;
    }
    if(data.hlfazt=='完成'){
        wcl=wcl+1;
    }
    if(data.jthywczt=='完成'){
        wcl=wcl+1;
    }
    if(data.djhywczt=='完成'){
        wcl=wcl+1;
    }
    if(data.pmsjwczt=='完成'){
        wcl=wcl+1;
    }
    if(data.sgzzwczt=='完成'){
        wcl=wcl+1;
    }
    if(data.hchwczt=='完成'){
        wcl=wcl+1;
    }
    if(data.fawcwczt=='完成'){
        wcl=wcl+1;
    }
    if(data.dzqtwczt=='完成'){
        wcl=wcl+1;
    }
    if(data.sjapbwczt=='完成'){
        wcl=wcl+1;
    }
    if(data.wlcgwczt=='完成'){
        wcl=wcl+1;
    }
    return wcl;
}
