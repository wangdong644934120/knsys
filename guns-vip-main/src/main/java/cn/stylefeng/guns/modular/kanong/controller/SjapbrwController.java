package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.service.SjapbrwService;
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
@RequestMapping("/sjapbrw")
public class SjapbrwController extends BaseController {

    private String PREFIX = "sjapbrw";
    @Autowired
    private SjapbrwService sjapbService;
    @Autowired
    private UserService userService;
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
        return PREFIX + "/sjapbrw.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/sjapbrw_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/sjapbrw_edit.html";
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
        this.sjapbService.updateRW(ddkhParam);
        if(ddkhParam.getSjapbwczt().equals("完成")){
            //发送通知
            List<User> listUsers=this.userService.getAllUserOpenid();
            List<WXMessage> list=new ArrayList<>();
            if(listUsers!=null && !listUsers.isEmpty()){
                for(User useropen : listUsers){
                    WXMessage message =new WXMessage();
                    message.setOpenid(useropen.getOpenId());
                    message.setFirst(ddkhParam.getXlxm()+"&"+ddkhParam.getXnxm());
                    message.setKeyword1(ddkhParam.getXlxm()+"&"+ddkhParam.getXnxm());
                    message.setKeyword2("时间安排表完成");
                    list.add(message);
                }

            }
            if(!list.isEmpty()){
                new WeChatMessage(gunsProperties.getAppId(),gunsProperties.getAppSecret(), WeChatMessage.MESSAGE_TYPE_RWWC,list).start();
            }
        }
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
        Ddkh detail = this.sjapbService.getSjapbById(ddkhParam);
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
        return this.sjapbService.findPageBySpec(ddkhParam);
    }

}


