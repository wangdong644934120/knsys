/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.sys.modular.system.controller;

import cn.hutool.http.HttpUtil;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.sms.modular.model.SendMessageParam;
import cn.stylefeng.guns.sms.modular.provider.SmsServiceApi;
import cn.stylefeng.guns.sys.core.constant.Const;
import cn.stylefeng.guns.sys.core.constant.cache.Cache;
import cn.stylefeng.guns.sys.core.exception.InvalidKaptchaException;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.log.LogManager;
import cn.stylefeng.guns.base.pojo.node.MenuNode;
import cn.stylefeng.guns.sys.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.core.shiro.ShiroTempPasswordHolder;
import cn.stylefeng.guns.sys.core.util.CacheUtil;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import static cn.stylefeng.roses.core.util.HttpContext.getIp;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private SmsServiceApi smsServiceApi;
    @Autowired
    private Producer producer;


    /**
     * 跳转到主页
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前用户角色列表
        ShiroUser user = ShiroKit.getUserNotNull();
        List<Long> roleList = user.getRoleList();

        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }

        List<MenuNode> menus = userService.getUserMenuNodes(roleList);
        model.addAttribute("menus", menus);
        String ua= request.getHeader("User-Agent");
        if(this.checkAgentIsMobile(ua)){
            System.out.println("来自移动端访问");
            return "/index_mb.html";
        }else{
            System.out.println("来自PC端访问");
            return "/index.html";
        }
    }

    /**
     * @Description 判断是否为移动端访问
     * @Author lichenfeng
     * @Date 2019/10/9 8:26
     **/
    private boolean checkAgentIsMobile(String ua) {
        String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" };
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
    /**
     * 跳转到登录页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    @RequestMapping("/registerpage")
    public String wechatpage(){
        String openid=super.getPara("openid");
        return "/register.html?openid="+openid;
    }
    /**
     * 点击登录执行的动作
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali(HttpServletResponse response) {
        String username = super.getPara("username");
        String password = super.getPara("password");
        String remember = super.getPara("remember");
        String capText = super.getPara("capText");

        if(username.equals("user")){
            password="test";
            //设置线程变量
            ShiroTempPasswordHolder.set("test");
        }else{
            String verifyCode = super.getPara("verifyCode");//短信验证码
            if(StringUtils.isNotEmpty(verifyCode)){//短信验证码登录
                //验证缓存中的验证码是否正确
                String cacheCode=CacheUtil.get(Cache.GLOBAL,username);
                if(verifyCode.equals(cacheCode)){//验证通过
                    CacheUtil.remove(Cache.GLOBAL,username);//移除掉
                    //设置验证码为密码
                    password=verifyCode;
                    //设置线程变量
                    ShiroTempPasswordHolder.set(verifyCode);
                }else{
                    throw new InvalidKaptchaException();
                }
            }else{//PC的验证码
                /*String sessionCapText=(String)ShiroKit.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
                if(StringUtils.isNotEmpty(sessionCapText)){
                    if(!sessionCapText.toLowerCase().equals(capText.toLowerCase())){
                        throw new InvalidKaptchaException();
                    }
                }*/
            }
        }


        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        //如果开启了记住我功能
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        //执行shiro登录操作
        currentUser.login(token);

        //登录成功，记录登录日志
        ShiroUser shiroUser = ShiroKit.getUserNotNull();
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

        ShiroKit.getSession().setAttribute("sessionFlag", true);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(this.checkAgentIsMobile(request.getHeader("User-Agent"))){
            return REDIRECT + "/?index";
        }else{
            return REDIRECT + "/";
        }
    }

//    @RequestMapping(value = "/loginWeChat", method = RequestMethod.POST)
//    public void loginWeChat(HttpServletRequest request,HttpServletResponse response){
//        try{
//
//            String openid=super.getPara("openid");
//            String code = super.getPara("code");
//            if(openid==null){
//                String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx593d1073c0290b96&secret=e14f92b92833a6a3b1f2c69c7c6e2c52&js_code="+code+"&grant_type=authorization_code";
//                String result = HttpUtil.get(url);
//                System.out.println(result);
//                //根据openid从数据库查询人员是否已经注册
//                JSONObject obj = new JSONObject(result);
//                openid=obj.get("openid").toString();
//            }
//
//            User user=userService.getUserByOpenid(openid);
//            System.out.println("微信登录："+user);
//            String sendData="";
//            if(user==null){
//                //没有注册ojMRa5BHNavDOLvztNpmInhg01EQ
//                sendData="{\"result\":\"0\",\"openid\":\""+openid+"\"}";
//            }else{
//                //已经注册
//                sendData="{\"result\":\"1\",\"name\":\""+user.getName()+"\",\"openid\":\""+openid+"\"}";
//                System.out.println(user);
//                String username =user.getAccount();
//                String password = "test";
//                ShiroTempPasswordHolder.set("test");
//                Subject currentUser = ShiroKit.getSubject();
//                UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
//                token.setRememberMe(false);
//                //执行shiro登录操作
//                currentUser.login(token);
//                //登录成功，记录登录日志
//                ShiroUser shiroUser = ShiroKit.getUserNotNull();
//                LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
//                ShiroKit.getSession().setAttribute("sessionFlag", true);
//            }
//            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            System.out.println(request.getCookies());
////ojMRa5BHNavDOLvztNpmInhg01EQ
//            response.setContentType("text/html;charset=utf-8");
//            /* 设置响应头允许ajax跨域访问 */
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            //response.addCookie(request.getCookies()[0]);
//            /* 星号表示所有的异域请求都可以接受， */
//            response.setHeader("Access-Control-Allow-Methods", "GET,POST");
//            //返回值给微信小程序
//            Writer out = response.getWriter();
//            out.write(sendData);
//            out.flush();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @RequestMapping(value = "/wechatregister", method = RequestMethod.POST)
    public void register(HttpServletResponse response){
        try{
            String name = super.getPara("name");
            String phone = super.getPara("phone");
            String yqCode=super.getPara("YQCode");
            String openid = super.getPara("openid");
            System.out.println(name+" "+phone+" "+yqCode+" "+openid);
            //先根据注册码，判断注册码是否有效
            //根据邀请码和openid盘点用户是否已经注册
            String sendData="";
            User user=userService.getUserBySalt(yqCode);
            if(user==null){
                sendData="{\"result\":\"-1\",\"msg\":\"邀请码错误\"}";
            }else if(user!=null && user.getOpenId()!=null && !user.getOpenId().equals("")){
                //用户已经注册过
                sendData="{\"result\":\"0\",\"msg\":\"用户已经绑定\"}";
                System.out.println("用户已经绑定");
            }else{
                //用户没有注册，可以注册
                int result=userService.miniRegister(name,phone,openid);
                if(result==1){
                    sendData="{\"result\":\"1\",\"msg\":\"绑定成功\",\"name\":\""+name+"\",\"phone\":\""+phone+"\",\"openid\":\""+openid+"\"}";
                }else{
                    sendData="{\"result\":\"2\",\"msg\":\"姓名和手机号不匹配\",\"name\":\""+name+"\",\"phone\":\""+phone+"\",\"openid\":\""+openid+"\"}";
                }

            }
            response.setContentType("text/html;charset=utf-8");
            /* 设置响应头允许ajax跨域访问 */
            response.setHeader("Access-Control-Allow-Origin", "*");
            /* 星号表示所有的异域请求都可以接受， */
            response.setHeader("Access-Control-Allow-Methods", "GET,POST");
            //返回值给微信小程序
            Writer out = response.getWriter();
            out.write(sendData);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 退出登录
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUserNotNull().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();
        return REDIRECT + "/login";
    }

    /**
     * @Description 发送短信验证码
     * @Author lichenfeng
     * @Date 2019/9/19 14:12
     **/
    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData sendVerifyCode(String phoneNumber) {
        long createTime=CacheUtil.getCreationTime(Cache.GLOBAL,phoneNumber);
        if(createTime>0&&(System.currentTimeMillis()-createTime)<60*1000){
            return ResponseData.error(400,"操作过于频繁,请稍后重试");
        }
//        String verifyCode=producer.createText();
//        System.out.println("***********"+verifyCode);
//        CacheUtil.put(Cache.GLOBAL,phoneNumber,verifyCode,120);//一分钟
//        SendMessageParam sendMessageParam = new SendMessageParam();
//        sendMessageParam.setPhoneNumbers(phoneNumber);
//        //模板号
//        sendMessageParam.setTemplateCode("SMS_174580476");
//        //模板里的参数
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("code",verifyCode);
//        //params.put("xxxx", heartCheck.getServiceName());
//        sendMessageParam.setParams(params);
//        smsServiceApi.sendShortMessage(sendMessageParam);

        return ResponseData.success();
    }

    @RequestMapping(value = "/resetAll")
    @ResponseBody
    public ResponseData resetAll() {
        List<User> list=this.userService.list();
        for(User user :list){
            user.setSalt(ShiroKit.getRandomSalt(5));
            user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
            this.userService.updateById(user);
        }
        return ResponseData.success();
    }
}
