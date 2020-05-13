package cn.stylefeng.guns.modular.test.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.test.entity.Books;
import cn.stylefeng.guns.modular.test.model.params.BooksParam;
import cn.stylefeng.guns.modular.test.service.BooksService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;


/**
 * 书籍表控制器
 *
 * @author wangdong
 * @Date 2019-12-23 14:07:10
 */
@Controller
@RequestMapping("/books")
public class BooksController extends BaseController {

    private String PREFIX = "/books";

    @Autowired
    private BooksService booksService;

    /**
     * 跳转到主页面
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/books.html";
    }

    /**
     * 新增页面
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/books_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/books_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BooksParam booksParam) {
        this.booksService.add(booksParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(BooksParam booksParam) {
        this.booksService.update(booksParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(BooksParam booksParam) {
        this.booksService.delete(booksParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(BooksParam booksParam) {
        Books detail = this.booksService.getById(booksParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author wangdong
     * @Date 2019-12-23
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(BooksParam booksParam) {
        return this.booksService.findPageBySpec(booksParam);
    }

    @RequestMapping("/test")
    public void test(HttpServletResponse response){
        try{

            System.out.println("正常进入test方法");

//ojMRa5BHNavDOLvztNpmInhg01EQ
            response.setContentType("text/html;charset=utf-8");
            /* 设置响应头允许ajax跨域访问 */
            response.setHeader("Access-Control-Allow-Origin", "*");
            /* 星号表示所有的异域请求都可以接受， */
            response.setHeader("Access-Control-Allow-Methods", "GET,POST");



            //返回值给微信小程序
            Writer out = response.getWriter();
            out.write("123444");
            out.flush();
        }catch (Exception e){

        }
    }
}


