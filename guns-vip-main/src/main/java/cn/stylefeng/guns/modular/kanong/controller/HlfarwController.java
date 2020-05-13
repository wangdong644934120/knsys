package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.api.wechat.WXMessage;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.service.HlfarwService;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
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
 * 婚礼摄影师控制器
 *
 * @author wangdong
 * @Date 2020-03-16 14:41:08
 */
@Controller
@RequestMapping("/hlfarw")
public class HlfarwController extends BaseController {

    private String PREFIX = "hlfarw";
    @Autowired
    private HlfarwService hlfarwService;
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
        return PREFIX + "/hlfarw.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/hlfarw_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/hlfarw_edit.html";
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
        this.hlfarwService.updateRW(ddkhParam);
        if(ddkhParam.getHlfazt().equals("完成")){
            //发送通知
            List<User> listUsers=this.userService.getAllUserOpenid();
            List<WXMessage> list=new ArrayList<>();
            if(listUsers!=null && !listUsers.isEmpty()){
                for(User useropen : listUsers){
                    WXMessage message =new WXMessage();
                    message.setOpenid(useropen.getOpenId());
                    message.setFirst(ddkhParam.getXlxm()+"&"+ddkhParam.getXnxm());
                    message.setKeyword1(ddkhParam.getXlxm()+"&"+ddkhParam.getXnxm());
                    message.setKeyword2("婚礼方案完成，请到系统下载");
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
        Ddkh detail = this.hlfarwService.getHlfarwById(ddkhParam);
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
        return this.hlfarwService.findPageBySpec(ddkhParam);
    }

    @RequestMapping("/uploadPicture")
    @ResponseBody
    public ResponseData uploadPicture(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String res= uploadPic(file, request, "png");
        if(res.equals("1")){
            return ResponseData.success(1, "上传成功", null);
        }else{
            return ResponseData.success(0, "上传失败", null);
        }

    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResponseData uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String res = uploadFile(file, request, "xls");
        if(res.equals("1")){
            return ResponseData.success(1, "上传成功", null);
        }else{
            return ResponseData.success(0, "上传失败", null);
        }
    }

    /**
     * 上传文件方法
     *
     * @param file
     * @param request
     * @param fileType
     * @return
     */
    private String uploadPic(@RequestPart("file") MultipartFile file, HttpServletRequest request, String fileType) {
        String id=request.getParameter("id");
        String fileSaveFold = gunsProperties.getFileUploadPath() + PREFIX + "/";
        if (!new File(fileSaveFold).exists()) {
            new File(fileSaveFold).mkdirs();
        }
        String fileSavePath = gunsProperties.getFileUploadPath() + PREFIX + "/" + id + "." + fileType;
        try {
            file.transferTo(new File(fileSavePath));
        } catch (Exception e) {
            return "-1";
        }
        DdkhParam param = new DdkhParam();
        param.setId(id);
        param.setHlfaxgt("<a href=\"download/picture/?id="+id+"\" target=\"_blank\">点此下载</a>");
        int res=hlfarwService.updateXGT(param);
        if(res==1){
            return "1";
        }else{
            return "-1";
        }
    }
    /**
     * 上传文件方法
     *
     * @param file
     * @param request
     * @param fileType
     * @return
     */
    private String uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request, String fileType) {
        String id=request.getParameter("id");
        String fileSaveFold = gunsProperties.getFileUploadPath() + PREFIX + "/";
        if (!new File(fileSaveFold).exists()) {
            new File(fileSaveFold).mkdirs();
        }
        String fileSavePath = gunsProperties.getFileUploadPath() + PREFIX + "/" + id + "." + fileType;
        try {
            file.transferTo(new File(fileSavePath));
        } catch (Exception e) {
            return "-1";
        }
        DdkhParam param = new DdkhParam();
        param.setId(id);
        param.setHlfahtqd("<a href=\"download/file/?id="+id+"\" target=\"_blank\">点此下载</a>");
        int res=hlfarwService.updateHTQD(param);
        if(res==1){
            return "1";
        }else{
            return "-1";
        }
    }
}


