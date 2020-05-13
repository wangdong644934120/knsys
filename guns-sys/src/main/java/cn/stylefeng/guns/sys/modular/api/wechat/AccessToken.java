package cn.stylefeng.guns.sys.modular.api.wechat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;


public class AccessToken {
    private static String token="";
    private static long time=0l;
    public static  String appid="";
    public static  String secret="";
    public static String getToken()
    {
        if(System.currentTimeMillis()-time>1000*60*60){
            //获取token
            System.out.println("获取新的token");
            String result= HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret);
            System.out.println("获取token返回值："+result);
            JSONObject obj=null;
            try{
                obj = new JSONObject(result);
                token=obj.get("access_token").toString();
                System.out.println("获取到token:"+token);
                time=System.currentTimeMillis();
            }catch (Exception e){
            }
        }else{
            //使用现有token
        }
        System.out.println("token:"+token);
        return token;
    }
}
