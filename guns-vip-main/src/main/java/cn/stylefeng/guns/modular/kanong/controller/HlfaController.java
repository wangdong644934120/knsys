package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.modular.kanong.service.HlfaService;
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


/**
 * 控制器
 *
 * @author wangdong
 * @Date 2020-04-01 15:31:31
 */
@Controller
@RequestMapping("/hlfa")
public class HlfaController extends BaseController {

    private String PREFIX = "/hlfa";

    @Autowired
    private HlfaService hlfaService;
    @Autowired
    private GunsProperties gunsProperties;

    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/hlfa.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/hlfa_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/hlfa_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DdkhParam ddkhParam) {
        this.hlfaService.add(ddkhParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DdkhParam ddkhParam) {
        int re=this.hlfaService.updateJD(ddkhParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DdkhParam ddkhParam) {
        this.hlfaService.delete(ddkhParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(DdkhParam ddkhParam) {
        DdkhResult detail = this.hlfaService.findBySpec(ddkhParam);
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DdkhParam ddkhParam) {
        return this.hlfaService.findPageBySpec(ddkhParam);
    }


}


