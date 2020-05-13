package cn.stylefeng.guns.modular.kanong.model.result;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
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
public class DdkhResult extends Ddkh implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xlxm;
    private String xnxm;
    private String hljd;
    private String hlrq;
    private String zfzrid;
    private String gdrid;
    private String openid;
    private String zt;//knuser中的状态
    private String xgtsjsid;

    public String getXlxm() {
        return xlxm;
    }

    public void setXlxm(String xlxm) {
        this.xlxm = xlxm;
    }

    public String getXnxm() {
        return xnxm;
    }

    public void setXnxm(String xnxm) {
        this.xnxm = xnxm;
    }

    public String getHljd() {
        return hljd;
    }

    public void setHljd(String hljd) {
        this.hljd = hljd;
    }

    public String getHlrq() {
        return hlrq;
    }

    public void setHlrq(String hlrq) {
        this.hlrq = hlrq;
    }

    public String getZfzrid() {
        return zfzrid;
    }

    public void setZfzrid(String zfzrid) {
        this.zfzrid = zfzrid;
    }

    public String getGdrid() {
        return gdrid;
    }

    public void setGdrid(String gdrid) {
        this.gdrid = gdrid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
}
