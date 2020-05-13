package cn.stylefeng.guns.modular.kanong.controller;

import cn.hutool.http.HttpUtil;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.sys.core.log.LogManager;
import cn.stylefeng.guns.sys.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.core.shiro.ShiroTempPasswordHolder;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/assets")
public class WeChatServiceController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    private GunsProperties gunsProperties;

    @RequestMapping(value="/getcode", method = RequestMethod.GET)
    @ResponseBody
    public String getCode(HttpServletRequest request){
        //微信服务器回调，获取code
        String code=request.getParameter("code");
        System.out.println("code:"+code);
        //获取token
        String openid="";
        String token="";
        if(code!=null && !code.equals("")){
            String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+gunsProperties.getAppId()+"&secret="+gunsProperties.getAppSecret()+"&code="+code+"&grant_type=authorization_code";
            String result = HttpUtil.get(url);
            System.out.println(result);
            //根据openid从数据库查询人员是否已经注册
            try{
                JSONObject obj = new JSONObject(result);
                openid=obj.get("openid").toString();
                token=obj.get("access_token").toString();
            }catch (Exception e){
            }
            //System.out.println("openid:"+openid);
        }
        /*if(!openid.equals("") && !token.equals("")){
            String url="https://api.weixin.qq.com/sns/userinfo?access_token="+token+"&openid="+openid+"&lang=zh_CN";
            String result = HttpUtil.get(url);
            System.out.println(result);
        }*/
        //根据openid判断该人员是否有权限
        User user=userService.getUserByOpenid(openid);
        if(user==null){
            return "{\"openid\":\""+openid+"\",\"url\":\"/registerpage\"}";
        }else{
            String username =user.getAccount();
            String password = "test";
            ShiroTempPasswordHolder.set("test");
            Subject currentUser = ShiroKit.getSubject();
            UsernamePasswordToken tokenpass = new UsernamePasswordToken(username, password.toCharArray());
            tokenpass.setRememberMe(false);
            //执行shiro登录操作
            currentUser.login(tokenpass);
            //登录成功，记录登录日志
            ShiroUser shiroUser = ShiroKit.getUserNotNull();
            //LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
            ShiroKit.getSession().setAttribute("sessionFlag", true);
            return "{\"openid\":\""+openid+"\",\"url\":\"/jdtj\"}";
        }
    }
}
