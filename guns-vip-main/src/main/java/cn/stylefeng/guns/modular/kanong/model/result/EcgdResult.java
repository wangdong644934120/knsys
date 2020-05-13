package cn.stylefeng.guns.modular.kanong.model.result;

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
public class EcgdResult extends Ecgd implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xlxm;
    private String xnxm;
    private String hljd;
    private String hlrq;
    private String gdr;
    private String cjrq;
    private  String gdjl;
    private String tdjl;
    private String zt;//状态
    private String ecgdrid;

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

    public String getGdr() {
        return gdr;
    }

    public void setGdr(String gdr) {
        this.gdr = gdr;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public String getGdjl() {
        return gdjl;
    }

    public void setGdjl(String gdjl) {
        this.gdjl = gdjl;
    }

    public String getTdjl() {
        return tdjl;
    }

    public void setTdjl(String tdjl) {
        this.tdjl = tdjl;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getEcgdrid() {
        return ecgdrid;
    }

    public void setEcgdrid(String ecgdrid) {
        this.ecgdrid = ecgdrid;
    }
}
