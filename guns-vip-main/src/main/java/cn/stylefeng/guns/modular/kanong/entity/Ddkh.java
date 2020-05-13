package cn.stylefeng.guns.modular.kanong.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.poi.hpsf.Date;
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单客户表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@TableName("kn_ddkh")
public class Ddkh implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 婚礼用户表id
     */
    @TableField("kn_user_id")
    private String knUserId;

    /**
     * 总负责人
     */
    @TableField("zfzr")
    private String zfzr;

    /**
     * 订单日期
     */
    @TableField("ddrq")
    private String ddrq;

    /**
     * 创建时间
     */
    @TableField("cjsj")
    private DateTime cjsj;

    /**
     * 交付定金
     */
    @TableField("jfdj")
    private BigDecimal jfdj;

    @TableField("jfdjwczt")
    private String jfdjwczt;

    /**
     * 备注
     */
    @TableField("bz")
    private String bz;

    @TableField("htje")
    private BigDecimal htje;

    @TableField("gdr")
    private String gdr;

    @TableField("sxsjhwcrq")
    private String sxsjhwcrq;

    @TableField("sxsydrq")
    private String sxsydrq;

    @TableField("sxspz")
    private String sxspz;

    @TableField("sxswczt")
    private String sxswczt;

    @TableField("sysjhwcrq")
    private String sysjhwcrq;

    @TableField("sysydrq")
    private String sysydrq;

    @TableField("syspz")
    private String syspz;

    @TableField("syswczt")
    private String syswczt;

    @TableField("syjhwcrq")
    private String syjhwcrq;

    @TableField("syydrq")
    private String syydrq;

    @TableField("sypz")
    private String sypz;

    @TableField("sywczt")
    private String sywczt;

    @TableField("hzsjhwcrq")
    private String hzsjhwcrq;

    @TableField("hzsydrq")
    private String hzsydrq;

    @TableField("hzspz")
    private String hzspz;

    @TableField("hzswczt")
    private String hzswczt;

    @TableField("hlfaydrq")
    private String hlfaydrq;

    @TableField("hlfazt")
    private String hlfazt;

    @TableField("hlfaxgt")
    private String hlfaxgt;

    @TableField("hlfahtqd")
    private String hlfahtqd;

    @TableField("hlfajhwcrq")
    private String hlfajhwcrq;

    @TableField("xgtsjs")
    private String xgtsjs;

    @TableField("jthyydrq")
    private String jthyydrq;

    @TableField("jthywczt")
    private String jthywczt;

    @TableField("hchydrq")
    private String hchydrq;

    @TableField("hchwczt")
    private String hchwczt;

    @TableField("djhy")
    private String djhy;

    @TableField("djhywczt")
    private String djhywczt;

    @TableField("pmsjydrq")
    private String pmsjydrq;

    @TableField("pmsjwczt")
    private String pmsjwczt;

    @TableField("sgzzydrq")
    private String sgzzydrq;

    @TableField("sgzzwczt")
    private String sgzzwczt;

    @TableField("fawcydrq")
    private String fawcydrq;

    @TableField("fawcwczt")
    private String fawcwczt;

    @TableField("dzqtydrq")
    private String dzqtydrq;

    @TableField("dzqtwczt")
    private String dzqtwczt;

    @TableField("sjapbydrq")
    private String sjapbydrq;

    @TableField("sjapbwczt")
    private String sjapbwczt;

    @TableField("wlcgydrq")
    private String wlcgydrq;

    @TableField("wlcgwczt")
    private String wlcgwczt;


    @TableField("ddj")
    private BigDecimal ddj;

    @TableField("ddjwczt")
    private String ddjwczt;

    @TableField("jdk")
    private BigDecimal jdk;

    @TableField("jdkwczt")
    private String jdkwczt;

    @TableField("wk")
    private BigDecimal wk;

    @TableField("wkwczt")
    private String wkwczt;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnUserId() {
        return knUserId;
    }

    public void setKnUserId(String knUserId) {
        this.knUserId = knUserId;
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

    public DateTime getCjsj() {
        return cjsj;
    }

    public void setCjsj(DateTime cjsj) {
        this.cjsj = cjsj;
    }

    public BigDecimal getJfdj() {
        return jfdj;
    }

    public void setJfdj(BigDecimal jfdj) {
        this.jfdj = jfdj;
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

    public String getGdr() {
        return gdr;
    }

    public void setGdr(String gdr) {
        this.gdr = gdr;
    }

    public String getSxsjhwcrq() {
        return sxsjhwcrq;
    }

    public void setSxsjhwcrq(String sxsjhwcrq) {
        this.sxsjhwcrq = sxsjhwcrq;
    }

    public String getSxsydrq() {
        return sxsydrq;
    }

    public void setSxsydrq(String sxsydrq) {
        this.sxsydrq = sxsydrq;
    }

    public String getSxspz() {
        return sxspz;
    }

    public String getHlfajhwcrq() {
        return hlfajhwcrq;
    }

    public void setHlfajhwcrq(String hlfajhwcrq) {
        this.hlfajhwcrq = hlfajhwcrq;
    }

    public void setSxspz(String sxspz) {
        this.sxspz = sxspz;
    }

    public String getSxswczt() {
        return sxswczt;
    }

    public void setSxswczt(String sxswczt) {
        this.sxswczt = sxswczt;
    }

    public String getSysjhwcrq() {
        return sysjhwcrq;
    }

    public void setSysjhwcrq(String sysjhwcrq) {
        this.sysjhwcrq = sysjhwcrq;
    }

    public String getSysydrq() {
        return sysydrq;
    }

    public void setSysydrq(String sysydrq) {
        this.sysydrq = sysydrq;
    }

    public String getSyspz() {
        return syspz;
    }

    public void setSyspz(String syspz) {
        this.syspz = syspz;
    }

    public String getSyswczt() {
        return syswczt;
    }

    public void setSyswczt(String syswczt) {
        this.syswczt = syswczt;
    }

    public String getSyjhwcrq() {
        return syjhwcrq;
    }

    public void setSyjhwcrq(String syjhwcrq) {
        this.syjhwcrq = syjhwcrq;
    }

    public String getSyydrq() {
        return syydrq;
    }

    public void setSyydrq(String syydrq) {
        this.syydrq = syydrq;
    }

    public String getSypz() {
        return sypz;
    }

    public void setSypz(String sypz) {
        this.sypz = sypz;
    }

    public String getSywczt() {
        return sywczt;
    }

    public void setSywczt(String sywczt) {
        this.sywczt = sywczt;
    }

    public String getHzsjhwcrq() {
        return hzsjhwcrq;
    }

    public void setHzsjhwcrq(String hzsjhwcrq) {
        this.hzsjhwcrq = hzsjhwcrq;
    }

    public String getHzsydrq() {
        return hzsydrq;
    }

    public void setHzsydrq(String hzsydrq) {
        this.hzsydrq = hzsydrq;
    }

    public String getHzspz() {
        return hzspz;
    }

    public void setHzspz(String hzspz) {
        this.hzspz = hzspz;
    }

    public String getHzswczt() {
        return hzswczt;
    }

    public void setHzswczt(String hzswczt) {
        this.hzswczt = hzswczt;
    }

    public String getHlfaydrq() {
        return hlfaydrq;
    }

    public void setHlfaydrq(String hlfaydrq) {
        this.hlfaydrq = hlfaydrq;
    }

    public String getHlfaxgt() {
        return hlfaxgt;
    }

    public void setHlfaxgt(String hlfaxgt) {
        this.hlfaxgt = hlfaxgt;
    }

    public String getHlfahtqd() {
        return hlfahtqd;
    }

    public void setHlfahtqd(String hlfahtqd) {
        this.hlfahtqd = hlfahtqd;
    }

    public String getHlfazt() {
        return hlfazt;
    }

    public void setHlfazt(String hlfazt) {
        this.hlfazt = hlfazt;
    }

    public String getJthyydrq() {
        return jthyydrq;
    }

    public void setJthyydrq(String jthyydrq) {
        this.jthyydrq = jthyydrq;
    }

    public String getJthywczt() {
        return jthywczt;
    }

    public void setJthywczt(String jthywczt) {
        this.jthywczt = jthywczt;
    }

    public String getHchydrq() {
        return hchydrq;
    }

    public void setHchydrq(String hchydrq) {
        this.hchydrq = hchydrq;
    }

    public String getHchwczt() {
        return hchwczt;
    }

    public void setHchwczt(String hchwczt) {
        this.hchwczt = hchwczt;
    }

    public String getDjhy() {
        return djhy;
    }

    public void setDjhy(String djhy) {
        this.djhy = djhy;
    }

    public String getDjhywczt() {
        return djhywczt;
    }

    public void setDjhywczt(String djhywczt) {
        this.djhywczt = djhywczt;
    }

    public String getPmsjydrq() {
        return pmsjydrq;
    }

    public void setPmsjydrq(String pmsjydrq) {
        this.pmsjydrq = pmsjydrq;
    }

    public String getPmsjwczt() {
        return pmsjwczt;
    }

    public void setPmsjwczt(String pmsjwczt) {
        this.pmsjwczt = pmsjwczt;
    }

    public String getSgzzydrq() {
        return sgzzydrq;
    }

    public void setSgzzydrq(String sgzzydrq) {
        this.sgzzydrq = sgzzydrq;
    }

    public String getSgzzwczt() {
        return sgzzwczt;
    }

    public void setSgzzwczt(String sgzzwczt) {
        this.sgzzwczt = sgzzwczt;
    }

    public String getXgtsjs() {
        return xgtsjs;
    }

    public void setXgtsjs(String xgtsjs) {
        this.xgtsjs = xgtsjs;
    }

    public String getFawcydrq() {
        return fawcydrq;
    }

    public void setFawcydrq(String fawcydrq) {
        this.fawcydrq = fawcydrq;
    }

    public String getFawcwczt() {
        return fawcwczt;
    }

    public void setFawcwczt(String fawcwczt) {
        this.fawcwczt = fawcwczt;
    }

    public String getDzqtydrq() {
        return dzqtydrq;
    }

    public void setDzqtydrq(String dzqtydrq) {
        this.dzqtydrq = dzqtydrq;
    }

    public String getDzqtwczt() {
        return dzqtwczt;
    }

    public void setDzqtwczt(String dzqtwczt) {
        this.dzqtwczt = dzqtwczt;
    }

    public String getSjapbydrq() {
        return sjapbydrq;
    }

    public void setSjapbydrq(String sjapbydrq) {
        this.sjapbydrq = sjapbydrq;
    }

    public String getSjapbwczt() {
        return sjapbwczt;
    }

    public void setSjapbwczt(String sjapbwczt) {
        this.sjapbwczt = sjapbwczt;
    }

    public String getWlcgydrq() {
        return wlcgydrq;
    }

    public void setWlcgydrq(String wlcgydrq) {
        this.wlcgydrq = wlcgydrq;
    }

    public String getWlcgwczt() {
        return wlcgwczt;
    }

    public void setWlcgwczt(String wlcgwczt) {
        this.wlcgwczt = wlcgwczt;
    }

    public BigDecimal getDdj() {
        return ddj;
    }

    public void setDdj(BigDecimal ddj) {
        this.ddj = ddj;
    }

    public BigDecimal getJdk() {
        return jdk;
    }

    public void setJdk(BigDecimal jdk) {
        this.jdk = jdk;
    }

    public BigDecimal getWk() {
        return wk;
    }

    public void setWk(BigDecimal wk) {
        this.wk = wk;
    }

    public String getJfdjwczt() {
        return jfdjwczt;
    }

    public void setJfdjwczt(String jfdjwczt) {
        this.jfdjwczt = jfdjwczt;
    }

    public String getDdjwczt() {
        return ddjwczt;
    }

    public void setDdjwczt(String ddjwczt) {
        this.ddjwczt = ddjwczt;
    }

    public String getJdkwczt() {
        return jdkwczt;
    }

    public void setJdkwczt(String jdkwczt) {
        this.jdkwczt = jdkwczt;
    }

    public String getWkwczt() {
        return wkwczt;
    }

    public void setWkwczt(String wkwczt) {
        this.wkwczt = wkwczt;
    }

    @Override
    public String toString() {
        return "Ecgd{" +
        "id=" + id +
        ", knUserId=" + knUserId +

        "}";
    }
}
