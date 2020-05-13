package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.model.params.KhcxParam;
import cn.stylefeng.guns.modular.kanong.model.result.KhcxResult;
import cn.stylefeng.guns.modular.kanong.service.KhcxService;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 首次跟单表控制器
 *
 * @author wangdong
 * @Date 2020-03-10 14:38:13
 */
@Controller
@RequestMapping("/khcx")
public class KhcxController extends BaseController {

    private String PREFIX = "/khcx";

    @Autowired
    private KhcxService khcxService;

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
        return PREFIX + "/khcx.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/khcx_edit.html";
    }



    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(KhcxParam khcxParam) {
        KhcxResult detail = this.khcxService.getKhcxById(khcxParam);
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
    public LayuiPageInfo list(KhcxParam khcxParam) {
        return this.khcxService.findPageBySpec(khcxParam);
    }



}


