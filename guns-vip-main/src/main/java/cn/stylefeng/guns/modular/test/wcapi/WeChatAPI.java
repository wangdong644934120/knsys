package cn.stylefeng.guns.modular.test.wcapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

@Controller
@RequestMapping("/wcapi")
public class WeChatAPI {
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

