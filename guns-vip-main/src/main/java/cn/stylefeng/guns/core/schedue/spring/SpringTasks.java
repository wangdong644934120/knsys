package cn.stylefeng.guns.core.schedue.spring;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.modular.kanong.service.AlarmService;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.sys.modular.api.wechat.WXMessage;
import cn.stylefeng.guns.sys.modular.api.wechat.WeChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试定时任务
 *
 * @author fengshuonan
 * @Date 2019/2/24 16:29
 */
public class SpringTasks {
    @Autowired
    AlarmService alarmService;

    @Autowired
    private GunsProperties gunsProperties;

    /**
     * 上一次开始执行时间点之后5秒再执行
     *
     * @author fengshuonan
     * @Date 2019/2/24 16:31
     *//*
    @Scheduled(fixedRate = 5000)
    public void beginAfter() {
        System.err.println(System.currentTimeMillis()+"<<<<<调试信息,注释掉SchedulingConfig类中的内容来关闭这个定时任务！>>>>> spring task执行 >>>>>开始之后5秒执行！");
    }

    *//**
     * 上一次执行完毕时间点之后5秒再执行
     *
     * @author fengshuonan
     * @Date 2019/2/24 16:31
     *//*
    @Scheduled(fixedDelay = 5000)
    public void finishAfter() {
        System.err.println(System.currentTimeMillis()+"<<<<<调试信息,注释掉SchedulingConfig类中的内容来关闭这个定时任务！>>>>> spring task执行 >>>>>执行之后5秒才执行！");
    }

    *//**
     * 上一次执行完毕时间点之后5秒再执行
     *
     * @author fengshuonan
     * @Date 2019/2/24 16:31
     *//*
    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void stepAdd() {
        System.err.println(System.currentTimeMillis()+"<<<<<调试信息,注释掉SchedulingConfig类中的内容来关闭这个定时任务！>>>>> spring task执行 >>>>>第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次！");
    }*/

    /**
     * cron表达式执行
     *
     * @author fengshuonan
     * @Date 2019/2/24 16:31
     */
    @Scheduled(cron = "0 00 08 * * ?")
    public void cron() {
        List<DdkhResult> list=alarmService.find();
        if(list==null || list.isEmpty()){
            return;
        }
        for(DdkhResult result : list){
            if(result.getOpenid()!=null && !result.getOpenid().equals("")){
                send("司仪配置",result.getSyjhwcrq(),result.getSywczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("摄影师配置",result.getSysjhwcrq(),result.getSyswczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("摄像师配置",result.getSxsjhwcrq(),result.getSxswczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("化妆师配置",result.getHzsjhwcrq(),result.getHzswczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("婚礼方案",result.getHlfajhwcrq(),result.getHlfazt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("方案完成",result.getFawcydrq(),result.getFawcwczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("电子请帖",result.getDzqtydrq(),result.getDzqtwczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("时间安排表",result.getSjapbydrq(),result.getSjapbwczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("家庭会议",result.getJthyydrq(),result.getJthywczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("婚车花",result.getHchydrq(),result.getHchwczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("搭建会议",result.getDjhy(),result.getDjhywczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("物料采购",result.getWlcgydrq(),result.getWlcgwczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("平面设计",result.getPmsjydrq(),result.getPmsjwczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
                send("手工制作",result.getSgzzydrq(),result.getSgzzwczt(),result.getOpenid(),result.getXlxm()+"&"+result.getXnxm());
            }
        }


        //System.err.println(System.currentTimeMillis()+"<<<<<调试信息,注释掉SchedulingConfig类中的内容来关闭这个定时任务！>>>>> spring task执行 >>>>>每隔10秒执行一次！");
    }

    private void send(String type,String jhwcrq,String wczt,String openid,String first){
        List<WXMessage> listMessage=new ArrayList<WXMessage>();
        if((wczt==null || !wczt.equals("完成")) && jhwcrq!=null && !jhwcrq.equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String rwflag=WeChatMessage.MESSAGE_TYPE_RWWC;
            long jwhcrq=0;
            try{
                jwhcrq= sdf.parse(jhwcrq+" 23:59:59").getTime();
                if(jwhcrq-System.currentTimeMillis()>=1000*60*60*24*4){
                    return;
                }else if(jwhcrq-System.currentTimeMillis()>0 && jwhcrq-System.currentTimeMillis()<1000*60*60*24*4){
                    rwflag=WeChatMessage.MESSAGE_TYPE_RWTX;
                }else  if(jwhcrq-System.currentTimeMillis()<=0){
                    rwflag=WeChatMessage.MESSAGE_TYPE_RWCQ;
                }
            }catch (Exception e){
            }

            WXMessage message =new WXMessage();
            message.setOpenid(openid);
            message.setFirst(first);
            message.setKeyword1(first);
            message.setKeyword2(type+"计划完成日期："+jhwcrq);
            listMessage.add(message);
            new WeChatMessage(gunsProperties.getAppId(),gunsProperties.getAppSecret(), rwflag,listMessage).start();
            try{
                Thread.sleep(200);
            }catch (Exception e){

            }
        }
    }
}