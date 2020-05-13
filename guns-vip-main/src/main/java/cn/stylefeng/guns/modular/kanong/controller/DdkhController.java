package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.modular.kanong.service.DdkhService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.modular.api.wechat.WXMessage;
import cn.stylefeng.guns.sys.modular.api.wechat.WeChatMessage;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.guns.util.ImageBase64;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 首次跟单表控制器
 *
 * @author wangdong
 * @Date 2020-03-10 14:38:13
 */
@Controller
@RequestMapping("/ddkh")
public class DdkhController extends BaseController {

    private String PREFIX = "/ddkh";

    @Autowired
    private DdkhService ddkhService;

    @Autowired
    private UserService userService;

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
        return PREFIX + "/ddkh.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/ddkh_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/ddkh_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DdkhParam ddkhParam) {
        this.ddkhService.add(ddkhParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DdkhParam ddkhParam) {
       //1、判断负责人是否变化
        DdkhResult ddkh=this.ddkhService.getDdkhById(ddkhParam);
        if(!ddkhParam.getZfzr().equals(ddkh.getZfzrid())){
            //总负责人变化,需要发送通知
            //从数据库查询出openid
            User user = this.userService.getById(ddkhParam.getZfzr());
            List<User> listUsers=this.userService.getAllUserOpenid();
            List<WXMessage> list=new ArrayList<>();
            if(listUsers!=null && !listUsers.isEmpty()){
                for(User useropen : listUsers){
                    WXMessage message = new WXMessage();
                    message.setFirst(ddkhParam.getXlxm()+"&"+ddkhParam.getXnxm());
                    message.setKeyword1("总负责人分配");
                    message.setKeyword2(user.getName()+"为\""+ddkhParam.getXlxm()+"&"+ddkhParam.getXnxm()+"\"婚礼总负责人");
                    message.setOpenid(useropen.getOpenId());
                    list.add(message);
                }

            }
           if(!list.isEmpty()){
               new WeChatMessage(gunsProperties.getAppId(),gunsProperties.getAppSecret(),WeChatMessage.MESSAGE_TYPE_RWFP,list).start();
           }
        }
        //2、跟单信息更新
        ddkhParam.setGdr(ddkhParam.getGdrid());
        //this.ddkhService.update(ddkhParam);
        this.ddkhService.updateDDKH(ddkhParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DdkhParam ddkhParam) {
        this.ddkhService.delete(ddkhParam);
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
    public ResponseData detail(DdkhParam ddkhParam) {
        Ddkh detail = this.ddkhService.getDdkhById(ddkhParam);
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
    public LayuiPageInfo list(DdkhParam ddkhParam) {
        return this.ddkhService.findPageBySpec(ddkhParam);
    }

}


