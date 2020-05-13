package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.modular.kanong.service.DjsxmService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author wangdong
 * @Date 2020-04-01 15:31:31
 */
@Controller
@RequestMapping("/djsxm")
public class DjsxmController extends BaseController {

    private String PREFIX = "/djsxm";

    @Autowired
    private DjsxmService djsxmService;

    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/djsxm.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/djsxm_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/djsxm_edit.html";
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
        this.djsxmService.add(ddkhParam);
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
        int re=this.djsxmService.updateJD(ddkhParam);
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
        this.djsxmService.delete(ddkhParam);
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
        DdkhResult detail = this.djsxmService.findBySpec(ddkhParam);
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
        return this.djsxmService.findPageBySpec(ddkhParam);
    }

}


