package cn.stylefeng.guns.modular.kanong.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.kanong.entity.Scgd;

/**
 * <p>
 * 首次跟单表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@Data
public class ScgdResult extends Scgd implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xlxm;
    private String xnxm;
    private String hljd;
    private String jdr;
    private String hlrq;
    private String jdrq;
    private String tdjl;
    private String zt;//状态
    private String gdrid;// 跟单人id

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

    public String getJdr() {
        return jdr;
    }

    public void setJdr(String jdr) {
        this.jdr = jdr;
    }

    public String getHlrq() {
        return hlrq;
    }

    public void setHlrq(String hlrq) {
        this.hlrq = hlrq;
    }

    public String getJdrq() {
        return jdrq;
    }

    public void setJdrq(String jdrq) {
        this.jdrq = jdrq;
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

    public String getGdrid() {
        return gdrid;
    }

    public void setGdrid(String gdrid) {
        this.gdrid = gdrid;
    }
}
