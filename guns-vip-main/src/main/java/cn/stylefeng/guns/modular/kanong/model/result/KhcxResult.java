package cn.stylefeng.guns.modular.kanong.model.result;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 首次跟单表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@Data
public class KhcxResult extends GdshResult {

    private static final long serialVersionUID = 1L;

    private String scgdjfdj;
    private String zfzr;
    private String ddrq;
    private BigDecimal jfdj;
    private String jfdjwczt;
    private String bz;
    private BigDecimal htje;
    private String sxspz;
    private String syspz;
    private String sypz;
    private String hzspz;
    private String xgtsjs;
    private BigDecimal ddj;
    private String ddjwczt;
    private BigDecimal jdk;
    private String jdkwczt;
    private BigDecimal wk;
    private String wkwczt;


    public String getScgdjfdj() {
        return scgdjfdj;
    }

    public void setScgdjfdj(String scgdjfdj) {
        this.scgdjfdj = scgdjfdj;
    }

    public String getZfzr() {
        return zfzr;
    }

    public void setZfzr(String zfzr) {
        this.zfzr = zfzr;
    }

    public String getDdrq() {
        return ddrq;
    }

    public void setDdrq(String ddrq) {
        this.ddrq = ddrq;
    }

    public BigDecimal getJfdj() {
        return jfdj;
    }

    public void setJfdj(BigDecimal jfdj) {
        this.jfdj = jfdj;
    }

    public String getJfdjwczt() {
        return jfdjwczt;
    }

    public void setJfdjwczt(String jfdjwczt) {
        this.jfdjwczt = jfdjwczt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public BigDecimal getHtje() {
        return htje;
    }

    public void setHtje(BigDecimal htje) {
        this.htje = htje;
    }

    public String getSxspz() {
        return sxspz;
    }

    public void setSxspz(String sxspz) {
        this.sxspz = sxspz;
    }

    public String getSyspz() {
        return syspz;
    }

    public void setSyspz(String syspz) {
        this.syspz = syspz;
    }

    public String getSypz() {
        return sypz;
    }

    public void setSypz(String sypz) {
        this.sypz = sypz;
    }

    public String getHzspz() {
        return hzspz;
    }

    public void setHzspz(String hzspz) {
        this.hzspz = hzspz;
    }

    public String getXgtsjs() {
        return xgtsjs;
    }

    public void setXgtsjs(String xgtsjs) {
        this.xgtsjs = xgtsjs;
    }

    public BigDecimal getDdj() {
        return ddj;
    }

    public void setDdj(BigDecimal ddj) {
        this.ddj = ddj;
    }

    public String getDdjwczt() {
        return ddjwczt;
    }

    public void setDdjwczt(String ddjwczt) {
        this.ddjwczt = ddjwczt;
    }

    public BigDecimal getJdk() {
        return jdk;
    }

    public void setJdk(BigDecimal jdk) {
        this.jdk = jdk;
    }

    public String getJdkwczt() {
        return jdkwczt;
    }

    public void setJdkwczt(String jdkwczt) {
        this.jdkwczt = jdkwczt;
    }

    public BigDecimal getWk() {
        return wk;
    }

    public void setWk(BigDecimal wk) {
        this.wk = wk;
    }

    public String getWkwczt() {
        return wkwczt;
    }

    public void setWkwczt(String wkwczt) {
        this.wkwczt = wkwczt;
    }
}
