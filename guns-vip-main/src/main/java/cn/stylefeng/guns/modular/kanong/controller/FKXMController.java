package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.service.FKXMService;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.modular.api.wechat.WXMessage;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 婚礼摄影师控制器
 *
 * @author wangdong
 * @Date 2020-03-16 14:41:08
 */
@Controller
@RequestMapping("/fkxm")
public class FKXMController extends BaseController {

    private String PREFIX = "fkxm";
    @Autowired
    private FKXMService fKXMService;
    @Autowired
    private GunsProperties gunsProperties;

    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/fkxm.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/fkxm_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/fkxm_edit.html";
    }


    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DdkhParam ddkhParam) {
        this.fKXMService.updateFKXM(ddkhParam);
//        if(ddkhParam.getHlfazt().equals("完成")){
//            //发送通知
//            List<WXMessage> list=new ArrayList<WXMessage>();
//
//            //根据id查询负责人openid
//            User user=userService.getUserByid(ddkhParam.getZfzrid());
//            if(user!=null){
//                WXMessage message =new WXMessage();
//                message.setOpenid(user.getOpenId());
//                message.setRwmc("婚礼方案");
//                message.setWcrq(ddkhParam.getHlfaydrq());
//                message.setWxts(ddkhParam.getXlxm()+"&"+ddkhParam.getXnxm()+"  婚礼方案完成");
//                list.add(message);
////                WeChatMessage messageSend= new WeChatMessage(gunsProperties.getAppId(),gunsProperties.getAppSecret(),WeChatMessage.MESSAGE_TYPE_RWWC,list);
////                messageSend.start();
//            }
//        }
        return ResponseData.success();
    }



    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(DdkhParam ddkhParam) {
        Ddkh detail = this.fKXMService.getFKXMById(ddkhParam);
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DdkhParam ddkhParam) {
        return this.fKXMService.findPageBySpec(ddkhParam);
    }

}


