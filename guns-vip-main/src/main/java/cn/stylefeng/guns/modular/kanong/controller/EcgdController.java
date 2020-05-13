package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.model.params.EcgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.service.EcgdService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
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
import java.util.HashMap;
import java.util.List;


/**
 * 首次跟单表控制器
 *
 * @author wangdong
 * @Date 2020-03-10 14:38:13
 */
@Controller
@RequestMapping("/ecgd")
public class EcgdController extends BaseController {

    private String PREFIX = "/ecgd";

    @Autowired
    private EcgdService ecgdService;

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
        return PREFIX + "/ecgd.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/ecgd_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/ecgd_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(EcgdParam ecgdParam) {
        this.ecgdService.add(ecgdParam);
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
    public ResponseData editItem(EcgdParam ecgdParam) {
        //1、跟单信息更新
        this.ecgdService.update(ecgdParam);
        if(ecgdParam.getEcgdjg().equals("订单成交") && ecgdParam.getZt().equals(KNUser.ZT_ECGD)){
            //添加数据到订单表中
            this.ecgdService.addDdkh(ecgdParam);
        }
        //2、人员信息更新
        //更新婚礼酒店、婚礼日期
        KNUser knUser=new KNUser();
        if(ecgdParam.getEcgdjg().equals("订单成交")){
            //如果订单成功，添加到订单表中
            knUser.setZt(KNUser.ZT_DDCJ);
        }else if(ecgdParam.getEcgdjg().equals("放弃跟单")){
            knUser.setZt(KNUser.ZT_DDFQ);
        }
        knUser.setId(ecgdParam.getKnUserId());
        this.ecgdService.updateUser(knUser);
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
    public ResponseData delete(EcgdParam ecgdParam) {
        this.ecgdService.delete(ecgdParam);
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
    public ResponseData detail(EcgdParam ecgdParam) {
        Ecgd detail = this.ecgdService.getECGDById(ecgdParam);
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
    public LayuiPageInfo list(EcgdParam ecgdParam) {
        return this.ecgdService.findPageBySpec(ecgdParam);
    }

    @RequestMapping("/uploadPicture")
    @ResponseBody
    public ResponseData uploadPicture(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        HashMap map = uploadFile(file, request, "png");
        return ResponseData.success(0, "上传成功", map);
    }

    @RequestMapping("/addPic")
    @ResponseBody
    public ResponseData addPic(TpParam tpParam){
        this.ecgdService.addPic(tpParam);
        return ResponseData.success(0, "图片上传成功", null);
    }

    @RequestMapping("/getPic")
    @ResponseBody
    public ResponseData getPic(TpParam tpParam){

        List<String> list=this.ecgdService.getPic(tpParam);
        return ResponseData.success(0, "图片上传成功", list);
    }
    /**
     * 上传文件方法
     *
     * @param file
     * @param request
     * @param fileType
     * @return
     */
    private HashMap uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request, String fileType) {
        String idStr = IdWorker.getIdStr();
        String name = file.getOriginalFilename();
        String fileSaveFold = gunsProperties.getFileUploadPath() + PREFIX + "/";
        if (!new File(fileSaveFold).exists()) {
            new File(fileSaveFold).mkdirs();
        }
        String fileSavePath = gunsProperties.getFileUploadPath() + PREFIX + "/" + idStr + "." + fileType;
        try {
            file.transferTo(new File(fileSavePath));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileUUID",  PREFIX + "/" + idStr + "." + fileType);
        if (!fileType.equals("dwg")) {
            map.put("imgBase64", ImageBase64.getPhotoBase64(fileSavePath));
        }
        return map;
    }
}


