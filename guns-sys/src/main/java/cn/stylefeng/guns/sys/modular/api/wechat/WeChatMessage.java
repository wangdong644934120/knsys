package cn.stylefeng.guns.sys.modular.api.wechat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WeChatMessage extends Thread{


    public static String MESSAGE_TYPE_RWFP="1";//任务分配
    public static String MESSAGE_TYPE_RWWC="2";//任务完成
    public static String MESSAGE_TYPE_RWTX="3";//任务提醒
    public static String MESSAGE_TYPE_RWCQ="4";//任务超期
    private String type;//消息类型
    private List<WXMessage> list;
    private String appid;
    private String secret;

    public WeChatMessage(String appid,String secret,String type,List<WXMessage> list){
        this.type=type;
        this.list=list;
        this.appid=appid;
        this.secret=secret;
    }

    public void run(){
        for(WXMessage message : list){
            WXMessageTemplate template=new WXMessageTemplate();
            template.setTouser(message.getOpenid());
            template.setTemplate_id("GlnVWbB_W95kjkzZnEaWVraP8Ie-cLHxoXDORyA7OFA");
            Map<String,WXMessageTemplateParam> mapParam=new HashMap<>();
            String color="";
            if(type.equals(MESSAGE_TYPE_RWFP)){
                color="";
            }else if(type.equals(MESSAGE_TYPE_RWWC)){
                color="#008000";
            }else if(type.equals(MESSAGE_TYPE_RWTX)){
                color="#FFCC00";
            }else if(type.equals(MESSAGE_TYPE_RWCQ)){
                color="\t#FF0000";
            }
            mapParam.put("first",new WXMessageTemplateParam(message.getFirst(),color));
            mapParam.put("keyword1",new WXMessageTemplateParam(message.getKeyword1(),color));
            mapParam.put("keyword2",new WXMessageTemplateParam(message.getKeyword2(),color));
            template.setData(mapParam);
            Object obj = JSONArray.toJSON(template);
            String js = obj.toString();
            AccessToken.appid=this.appid;
            AccessToken.secret=this.secret;
            String dyres=HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+AccessToken.getToken(),js);

            System.out.println(dyres);
        }

//        WXMessageTemplate template=new WXMessageTemplate();
//        template.setTouser("ovTKeuLPpliv5X1buOFxRcPvDl9U");
//        template.setTemplate_id("GlnVWbB_W95kjkzZnEaWVraP8Ie-cLHxoXDORyA7OFA");
//        Map<String,WXMessageTemplateParam> mapParam=new HashMap<>();
//        mapParam.put("first",new WXMessageTemplateParam("张三-李四",""));
//        mapParam.put("keyword1",new WXMessageTemplateParam("婚礼方案",""));
//        mapParam.put("keyword2",new WXMessageTemplateParam("完成",""));
//        template.setData(mapParam);
//        Object obj = JSONArray.toJSON(template);
//        String js = obj.toString();
//
//        System.out.println(js);
//        String dyres=HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+AccessToken.getToken(),js);
//
//        System.out.println(dyres);

    }
//    private String getToken(){
//        String resultToken= HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret);
//        System.out.println(resultToken);
//        JSONObject obj=null;
//        String token="";
//        try{
//            obj = new JSONObject(resultToken);
//            token=obj.get("access_token").toString();
//        }catch (Exception e){
//        }
//        return token;
//    }

    /**
     * 任务完成
     * @return
     */
//    private String getRWWC(WXMessage message){
//        WXMessageTemplate template=new WXMessageTemplate();
//        template.setTouser(message.getOpenid());
//        template.setTemplate_id("s4I1AOcGMeacEE1479l0LXZVOLWNnct-btuchXTAT3E");
//        List<WXMessageTemplateParam> listParam=new ArrayList<>();
//        listParam.add(new WXMessageTemplateParam("phrase1",message.getRwmc()));
//        listParam.add(new WXMessageTemplateParam("date2",message.getWcrq()));
//        listParam.add(new WXMessageTemplateParam("thing4",message.getWxts()));
//        template.setData(listParam);
//        return template.toJSON();
//
//    }
}
