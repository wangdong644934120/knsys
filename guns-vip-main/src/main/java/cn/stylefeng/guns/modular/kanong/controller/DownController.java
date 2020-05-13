package cn.stylefeng.guns.modular.kanong.controller;

import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping("/download")
public class DownController extends BaseController {

    private String PREFIX = "hlfarw";

    @Autowired
    private GunsProperties gunsProperties;

    @RequestMapping("/picture")
    @ResponseBody
    public String downloadPicture(HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("id");
        String fileSavePath = gunsProperties.getFileUploadPath() + PREFIX + "/" + id + ".png";

        if (id != null && !id.equals("")) {
            try{
                // 获取输入流
                InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileSavePath)));
                // 假如以中文名下载的话
                String filename = id+".png";
                // 转码，免得文件名中文乱码
                filename = URLEncoder.encode(filename, "UTF-8");
                // 设置文件下载头
                response.addHeader("Content-Disposition", "attachment;filename=" + filename);
                // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                int len = 0;
                while ((len = bis.read()) != -1) {
                    out.write(len);
                    out.flush();
                }
                out.close();
                return "下载成功";
            }catch (Exception e){

            }
            //设置文件路径
//            File file = new File(fileSavePath);
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName=" + id+".png");// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    return "下载成功";
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
        }
        return "下载失败";
    }
    @RequestMapping("/file")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("id");
        String fileSavePath = gunsProperties.getFileUploadPath() + PREFIX + "/" + id + ".xls";

        if (id != null && !id.equals("")) {
            try{
                // 获取输入流
                InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileSavePath)));
                // 假如以中文名下载的话
                String filename = id+".xls";
                // 转码，免得文件名中文乱码
                filename = URLEncoder.encode(filename, "UTF-8");
                // 设置文件下载头
                response.addHeader("Content-Disposition", "attachment;filename=" + filename);
                // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                int len = 0;
                while ((len = bis.read()) != -1) {
                    out.write(len);
                    out.flush();
                }
                out.close();
                return "下载成功";
            }catch (Exception e){

            }

//            //设置文件路径
//            File file = new File(fileSavePath);
//            if (file.exists()) {
//                response.setContentType("application/force-download;charset=utf-8");// 设置强制下载不打开
//                response.setCharacterEncoding("UTF-8");
//                //response.setContentType("multipart/form-data;charset=UTF-8");
//                try{
//                    response.addHeader("Content-Disposition", "attachment;fileName=" +new String((id+".xls").getBytes("UTF-8"),"ISO-8859-1"));// 设置文件名
//                }catch (Exception e){
//
//                }
//
//                byte[] buffer = new byte[1024*100];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    return "下载成功";
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
        }
        return "下载失败";
    }
}

