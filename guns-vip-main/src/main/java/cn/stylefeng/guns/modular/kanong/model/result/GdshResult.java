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
public class GdshResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String xlxm;
    private String xnxm;
    private String hljd;
    private String hlrq;
    private String jdr;
    private String jdrq;
    private String tdjl;
    private String zt;//状态
    private String scgdr;
    private String scgdjg;
    private String scgdjl;
    private String scgdcjrq;
    private String ecgdr;
    private String ecgdjg;
    private String ecgdjl;
    private String ecgdcjrq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getScgdr() {
        return scgdr;
    }

    public void setScgdr(String scgdr) {
        this.scgdr = scgdr;
    }

    public String getScgdjg() {
        return scgdjg;
    }

    public void setScgdjg(String scgdjg) {
        this.scgdjg = scgdjg;
    }

    public String getScgdjl() {
        return scgdjl;
    }

    public void setScgdjl(String scgdjl) {
        this.scgdjl = scgdjl;
    }

    public String getScgdcjrq() {
        return scgdcjrq;
    }

    public void setScgdcjrq(String scgdcjrq) {
        this.scgdcjrq = scgdcjrq;
    }

    public String getEcgdr() {
        return ecgdr;
    }

    public void setEcgdr(String ecgdr) {
        this.ecgdr = ecgdr;
    }

    public String getEcgdjg() {
        return ecgdjg;
    }

    public void setEcgdjg(String ecgdjg) {
        this.ecgdjg = ecgdjg;
    }

    public String getEcgdjl() {
        return ecgdjl;
    }

    public void setEcgdjl(String ecgdjl) {
        this.ecgdjl = ecgdjl;
    }

    public String getEcgdcjrq() {
        return ecgdcjrq;
    }

    public void setEcgdcjrq(String ecgdcjrq) {
        this.ecgdcjrq = ecgdcjrq;
    }

    public String getJdr() {
        return jdr;
    }

    public void setJdr(String jdr) {
        this.jdr = jdr;
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
}
