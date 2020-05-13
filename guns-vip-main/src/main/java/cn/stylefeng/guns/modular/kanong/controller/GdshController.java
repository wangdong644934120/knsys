package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.model.params.EcgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.GdshParam;
import cn.stylefeng.guns.modular.kanong.model.result.EcgdResult;
import cn.stylefeng.guns.modular.kanong.model.result.GdshResult;
import cn.stylefeng.guns.modular.kanong.model.result.KNUserResult;
import cn.stylefeng.guns.modular.kanong.model.result.ScgdResult;
import cn.stylefeng.guns.modular.kanong.service.EcgdService;
import cn.stylefeng.guns.modular.kanong.service.GdshService;
import cn.stylefeng.guns.modular.kanong.service.KNUserService;
import cn.stylefeng.guns.modular.kanong.service.ScgdService;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.modular.api.wechat.WXMessage;
import cn.stylefeng.guns.sys.modular.api.wechat.WeChatMessage;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 首次跟单表控制器
 *
 * @author wangdong
 * @Date 2020-03-10 14:38:13
 */
@Controller
@RequestMapping("/gdsh")
public class GdshController extends BaseController {

    private String PREFIX = "/gdsh";

    @Autowired
    private GdshService gdshService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScgdService scgdService;

    @Autowired
    private EcgdService ecgdService;

    @Autowired
    private KNUserService knUserService;

    @Autowired
    private GunsProperties gunsProperties;
    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/gdsh.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/gdsh_edit.html";
    }



    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/jxgd")
    @ResponseBody
    public ResponseData editItemJXGD(GdshParam gdshParam) {
        //发送通知
        int flag=0; //flag:1-首次跟单，flag：2-二次跟单
        List<String> listOpenid= new ArrayList<>();
        //查询接待人
        KNUserResult knUserResult=knUserService.getKNUserByid(gdshParam.getId());
        if(knUserResult!=null){
            User user=userService.getUserByid(knUserResult.getJdr());
            if(user!=null && user.getOpenId() !=null && !user.getOpenId().equals("")){
                if(!listOpenid.contains(user.getOpenId())){
                    listOpenid.add(user.getOpenId());
                }

            }
        }
        //查询首次跟单人id及openid
        ScgdResult scgdResult=scgdService.getSCGDByKNUserId(gdshParam.getId());
        if(scgdResult!=null){
            flag=1;
            User user=userService.getUserByid(scgdResult.getGdr());
            if(user!=null && user.getOpenId() !=null && !user.getOpenId().equals("")){
                if(!listOpenid.contains(user.getOpenId())){
                    listOpenid.add(user.getOpenId());
                }

            }
        }
        //查询二次跟单人id及openid
        EcgdResult ecgdResult=ecgdService.getECGDByKNUserId(gdshParam.getId());
        if(ecgdResult!=null){
            flag=2;
            User user=userService.getUserByid(ecgdResult.getEcgdr());
            if(user!=null && user.getOpenId() !=null && !user.getOpenId().equals("")){
                if(!listOpenid.contains(user.getOpenId())){
                    listOpenid.add(user.getOpenId());
                }
            }
        }
        //查询新郎新娘姓名
        List<WXMessage> list=new ArrayList<>();
        if(!listOpenid.isEmpty()){
            for(String openid  : listOpenid){
                WXMessage message =new WXMessage();
                message.setOpenid(openid);
                message.setFirst(gdshParam.getXlxm()+"&"+gdshParam.getXnxm());
                message.setKeyword1(gdshParam.getXlxm()+"&"+gdshParam.getXnxm());
                message.setKeyword2("请继续跟单");
                list.add(message);
            }

        }
        if(!list.isEmpty()){
            new WeChatMessage(gunsProperties.getAppId(),gunsProperties.getAppSecret(), WeChatMessage.MESSAGE_TYPE_RWWC,list).start();
        }
        //1、跟单信息更新
        //如果是首次跟单后放弃跟单，则修改人员状态为首次跟单，跟单结果为空
        //如果是二次跟单放弃跟单，则修改人员状态为首次跟单，跟单结果为空
        this.gdshService.updateJXGD(gdshParam);
        if(flag==1){
            gdshParam.setZt(KNUser.ZT_SCGD);
            this.gdshService.updateJXGDSC(gdshParam);
        }else if(flag==2){
            gdshParam.setZt(KNUser.ZT_ECGD);
            this.gdshService.updateJXGDEC(gdshParam);
        }
        this.gdshService.updateJXGD(gdshParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/qrfq")
    @ResponseBody
    public ResponseData editItemQRFQ(GdshParam gdshParam) {
        //发送通知
        List<String> listOpenid= new ArrayList<>();
        //查询接待人
        KNUserResult knUserResult=knUserService.getKNUserByid(gdshParam.getId());
        if(knUserResult!=null){
            User user=userService.getUserByid(knUserResult.getJdr());
            if(user!=null && user.getOpenId() !=null && !user.getOpenId().equals("")){
                if(!listOpenid.contains(user.getOpenId())){
                    listOpenid.add(user.getOpenId());
                }

            }
        }
        //查询首次跟单人id及openid
        ScgdResult scgdResult=scgdService.getSCGDByKNUserId(gdshParam.getId());
        if(scgdResult!=null){
            User user=userService.getUserByid(scgdResult.getGdr());
            if(user!=null && user.getOpenId() !=null && !user.getOpenId().equals("")){
                if(!listOpenid.contains(user.getOpenId())){
                    listOpenid.add(user.getOpenId());
                }

            }
        }
        //查询二次跟单人id及openid
        EcgdResult ecgdResult=ecgdService.getECGDByKNUserId(gdshParam.getId());
        if(ecgdResult!=null){
            User user=userService.getUserByid(ecgdResult.getEcgdr());
            if(user!=null && user.getOpenId() !=null && !user.getOpenId().equals("")){
                if(!listOpenid.contains(user.getOpenId())){
                    listOpenid.add(user.getOpenId());
                }
            }
        }
        //查询新郎新娘姓名
        List<WXMessage> list=new ArrayList<>();
        if(!listOpenid.isEmpty()){
            for(String openid  : listOpenid){
                WXMessage message =new WXMessage();
                message.setOpenid(openid);
                message.setFirst(gdshParam.getXlxm()+"&"+gdshParam.getXnxm());
                message.setKeyword1(gdshParam.getXlxm()+"&"+gdshParam.getXnxm());
                message.setKeyword2("请放弃跟单");
                list.add(message);
            }

        }
        if(!list.isEmpty()){
            new WeChatMessage(gunsProperties.getAppId(),gunsProperties.getAppSecret(), WeChatMessage.MESSAGE_TYPE_RWWC,list).start();
        }
        //1、跟单信息更新
        this.gdshService.updateQRFQ(gdshParam);
        return ResponseData.success();
    }
    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(GdshParam gdshParam) {
        GdshResult detail = this.gdshService.getGdshById(gdshParam);
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(GdshParam gdshParam) {
        return this.gdshService.findPageBySpec(gdshParam);
    }



}


