package cn.stylefeng.guns.modular.kanong.controller;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Scgd;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.params.ScgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.service.DdkhService;
import cn.stylefeng.guns.modular.kanong.service.ScgdService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.util.ImageBase64;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/scgd")
public class ScgdController extends BaseController {

    private String PREFIX = "/scgd";

    @Autowired
    private ScgdService scgdService;

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private DdkhService ddkhService;
    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/scgd.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/scgd_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/scgd_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ScgdParam scgdParam) {
        this.scgdService.add(scgdParam);
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
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public ResponseData editItem(ScgdParam scgdParam) {
        try{
            this.scgdService.update(scgdParam);

            if(scgdParam.getZt().equals(KNUser.ZT_SCGD) && scgdParam.getGdjg().equals("二次跟单")){
                //如果是二次跟单，将数据添加到二次跟单数据表中
                this.scgdService.addECGD(scgdParam);
            }
            if(scgdParam.getZt().equals(KNUser.ZT_SCGD) && scgdParam.getGdjg().equals("订单成交")){
                //如果是订单成交，将数据添加到订单用户表中
                DdkhParam ddkhParam = new DdkhParam();
                ddkhParam.setHljd(scgdParam.getHljd());
                ddkhParam.setHlrq(scgdParam.getHlrq());
                ddkhParam.setBz("");
                ddkhParam.setGdr(scgdParam.getGdr());
                ddkhParam.setCjsj(new DateTime());
                ddkhParam.setDdrq(scgdParam.getCjrq());
                ddkhParam.setJfdj(scgdParam.getJfdj());
                ddkhParam.setKnUserId(scgdParam.getKnUserId());
                ddkhParam.setZfzr("");
                this.ddkhService.add(ddkhParam);
            }
            //更新婚礼酒店、婚礼日期
            KNUser knUser=new KNUser();
            if(scgdParam.getGdjg().equals("二次跟单")){
                knUser.setZt(KNUser.ZT_ECGD);
            }else if(scgdParam.getGdjg().equals("订单成交")){
                //如果订单成功，添加到订单表中
                knUser.setZt(KNUser.ZT_DDCJ);
            }else if(scgdParam.getGdjg().equals("放弃跟单")){
                knUser.setZt(KNUser.ZT_DDFQ);
            }
            knUser.setId(scgdParam.getKnUserId());
            this.scgdService.updateUser(knUser);
            return ResponseData.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseData.error("保存失败");
        }

    }

    /**
     * 删除接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ScgdParam scgdParam) {
        this.scgdService.delete(scgdParam);
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
    public ResponseData detail(ScgdParam scgdParam) {
        //Scgd detail = this.scgdService.getById(scgdParam.getId());
        Scgd detail = this.scgdService.getSCGDById(scgdParam);
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
    public LayuiPageInfo list(ScgdParam scgdParam) {
        return this.scgdService.findPageBySpec(scgdParam);
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
        this.scgdService.addPic(tpParam);
        return ResponseData.success(0, "图片上传成功", null);
    }

    @RequestMapping("/getPic")
    @ResponseBody
    public ResponseData getPic(TpParam tpParam){

        List<String> list=this.scgdService.getPic(tpParam);
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


