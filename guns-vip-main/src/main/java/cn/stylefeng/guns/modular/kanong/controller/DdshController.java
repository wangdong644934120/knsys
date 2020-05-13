package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.modular.kanong.service.DdkhService;
import cn.stylefeng.guns.modular.kanong.service.DdshService;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 首次跟单表控制器
 *
 * @author wangdong
 * @Date 2020-03-10 14:38:13
 */
@Controller
@RequestMapping("/ddsh")
public class DdshController extends BaseController {

    private String PREFIX = "/ddsh";

    @Autowired
    private DdshService ddshService;

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
        return PREFIX + "/ddsh.html";
    }



    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/ddsh_edit.html";
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
        this.ddshService.updateDDSHUser(ddkhParam);
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
        Ddkh detail = this.ddshService.getDdkhshById(ddkhParam);
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
        return this.ddshService.findPageBySpec(ddkhParam);
    }


}


