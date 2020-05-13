package cn.stylefeng.guns.modular.kanong.model.params;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 首次跟单表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@Data
public class DdkhParam extends Ddkh implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;
    private String hlrq;//婚礼日期
    private String hljd;//婚礼酒店
    private String xlxm;//新郎姓名
    private String xnxm;//新娘姓名
    private String gdrid;//跟单人id
    private String zfzrid;//总负责人id
    private String xgtsjsid;//效果图设计师id
    private String zt;//人员状态


    public String getHlrq() {
        return hlrq;
    }

    public void setHlrq(String hlrq) {
        this.hlrq = hlrq;
    }

    public String getHljd() {
        return hljd;
    }

    public void setHljd(String hljd) {
        this.hljd = hljd;
    }

    public String getXlxm() {
        return xlxm;
    }

    public void setXlxm(String xlxm) {
        this.xlxm = xlxm;
    }

    public String getGdrid() {
        return gdrid;
    }

    public void setGdrid(String gdrid) {
        this.gdrid = gdrid;
    }

    public String getZfzrid() {
        return zfzrid;
    }

    public void setZfzrid(String zfzrid) {
        this.zfzrid = zfzrid;
    }

    public String getXnxm() {
        return xnxm;
    }

    public void setXnxm(String xnxm) {
        this.xnxm = xnxm;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getXgtsjsid() {
        return xgtsjsid;
    }

    public void setXgtsjsid(String xgtsjsid) {
        this.xgtsjsid = xgtsjsid;
    }

    @Override
    public String checkParam() {
        return null;
    }

}
