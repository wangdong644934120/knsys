package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.model.params.KNUserParam;
import cn.stylefeng.guns.modular.kanong.service.KNUserService;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 婚礼用户表控制器
 *
 * @author wangdong
 * @Date 2020-03-09 23:23:44
 */
@Controller
@RequestMapping("/knuser")
public class KNUserController extends BaseController {

    private String PREFIX = "/knuser/";

    @Autowired
    private KNUserService knuserService;

    @Autowired
    private UserService userService;

    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/knuser.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/knuser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/knuser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(KNUserParam userParam) {
        ShiroUser shiroUser = ShiroKit.getUserNotNull();
        userParam.setCjr(shiroUser.getId());
        userParam.setCjrq(new Date());

        this.knuserService.add(userParam);
//        //测试消息发送
//        String resultToken=HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx593d1073c0290b96&secret=e14f92b92833a6a3b1f2c69c7c6e2c52");
//        System.out.println(resultToken);
//        JSONObject obj=null;
//        String token="";
//        try{
//            obj = new JSONObject(resultToken);
//            token=obj.get("access_token").toString();
//        }catch (Exception e){
//
//        }
//        Map<String,Object> map= new HashMap<String, Object>();
//        map.put("access_token",token);
//        map.put("touser","ojMRa5BHNavDOLvztNpmInhg01EQ");
//        map.put("template_id","s4I1AOcGMeacEE1479l0LXZVOLWNnct-btuchXTAT3E");
//        map.put("data","{\"phrase1\":\"测试任务\",\"date2\":\"2020-4-26\",\"thing4\":\"注意完成时间\"}");
//        map.put("lang","zh_CN");
//        String dyres=HttpUtil.post("https://api.weixin.qq.com/c;gi-bin/message/subscribe/send?access_token="+token,map);
//        System.out.println(dyres);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(KNUserParam userParam) {
        this.knuserService.update(userParam);
        //测试消息发送
//        String resultToken=HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx593d1073c0290b96&secret=e14f92b92833a6a3b1f2c69c7c6e2c52");
//        System.out.println(resultToken);
//        JSONObject obj=null;
//        String token="";
//        try{
//            obj = new JSONObject(resultToken);
//            token=obj.get("access_token").toString();
//        }catch (Exception e){
//
//        }
//
//        WXMessageTemplate template=new WXMessageTemplate();
//        template.setTouser("ojMRa5BHNavDOLvztNpmInhg01EQ");
//        template.setTemplate_id("s4I1AOcGMeacEE1479l0LXZVOLWNnct-btuchXTAT3E");
//        List<WXMessageTemplateParam> listParam=new ArrayList<>();
//        listParam.add(new WXMessageTemplateParam("phrase1","配送中"));
//        listParam.add(new WXMessageTemplateParam("date2","2019年10月1日"));
//        listParam.add(new WXMessageTemplateParam("thing4","test1"));
//        template.setData(listParam);
//        String js=template.toJSON();
//        System.out.println(js);
//        String dyres=HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+token,js);
//
//        System.out.println(dyres);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(KNUserParam userParam) {
        this.knuserService.delete(userParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(KNUserParam userParam) {
        KNUser detail = this.knuserService.getById(userParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(KNUserParam userParam) {
        return this.knuserService.findPageBySpec(userParam);
    }

    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    @RequestMapping("/getallusers")
    @ResponseBody
    public ResponseData getAllUsers() {
        List<User> list= this.userService.getAllUsers();
        return ResponseData.success(list);
    }
}


