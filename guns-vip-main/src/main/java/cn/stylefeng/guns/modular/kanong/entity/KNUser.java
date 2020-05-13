package cn.stylefeng.guns.modular.kanong.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 婚礼用户表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-09
 */
@TableName("kn_user")
public class KNUser implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String ZT_SCGD="1";//首次跟单
    public static String ZT_ECGD="2";//二次跟单
    public static String ZT_DDCJ="3";//订单成交
    public static String ZT_DDFQ="4";//订单放弃
    public static String ZT_QRFQ="5";//确认放弃
    public static String ZT_DDWC="6";//订单完成



    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 新郎姓名
     */
    @TableField("xlxm")
    private String xlxm;

    /**
     * 新郎电话
     */
    @TableField("xldh")
    private String xldh;

    /**
     * 新房地址
     */
    @TableField("xfdz")
    private String xfdz;

    /**
     * 新娘姓名
     */
    @TableField("xnxm")
    private String xnxm;

    /**
     * 新娘电话
     */
    @TableField("xndh")
    private String xndh;

    /**
     * 娘家地址
     */
    @TableField("njdz")
    private String njdz;
    /**
     * 婚礼酒店
     */
    @TableField("hljd")
    private String hljd;

    /**
     * 接待人
     */
    @TableField("jdr")
    private String jdr;

    /**
     * 婚礼日期
     */
    @TableField("hlrq")
    private String hlrq;

    /**
     * 进店日期
     */
    @TableField("jdrq")
    private String jdrq;

    /**
     * 谈单记录
     */
    @TableField("tdjl")
    private String tdjl;

    /**
     * 状态 1-首次跟单，2二次跟单，0删除
     */
    @TableField("zt")
    private String zt;

    /**
     * 创建日期
     */
    @TableField("cjrq")
    private Date cjrq;

    /**
     * 创建人
     */
    @TableField("cjr")
    private Long cjr;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCjrq() {
        return cjrq;
    }

    public void setCjrq(Date cjrq) {
        this.cjrq = cjrq;
    }


    public Long getCjr() {
        return cjr;
    }

    public void setCjr(Long cjr) {
        this.cjr = cjr;
    }

    public String getXlxm() {
        return xlxm;
    }

    public void setXlxm(String xlxm) {
        this.xlxm = xlxm;
    }

    public String getXldh() {
        return xldh;
    }

    public void setXldh(String xldh) {
        this.xldh = xldh;
    }

    public String getXfdz() {
        return xfdz;
    }

    public void setXfdz(String xfdz) {
        this.xfdz = xfdz;
    }

    public String getXnxm() {
        return xnxm;
    }

    public void setXnxm(String xnxm) {
        this.xnxm = xnxm;
    }

    public String getXndh() {
        return xndh;
    }

    public void setXndh(String xndh) {
        this.xndh = xndh;
    }

    public String getNjdz() {
        return njdz;
    }

    public void setNjdz(String njdz) {
        this.njdz = njdz;
    }

    @Override
    public String toString() {
        return "KNUser{" +
                "id='" + id + '\'' +
                ", xlxm='" + xlxm + '\'' +
                ", xldh='" + xldh + '\'' +
                ", xfdz='" + xfdz + '\'' +
                ", xnxm='" + xnxm + '\'' +
                ", xndh='" + xndh + '\'' +
                ", njdz='" + njdz + '\'' +
                ", hljd='" + hljd + '\'' +
                ", jdr='" + jdr + '\'' +
                ", hlrq='" + hlrq + '\'' +
                ", jdrq='" + jdrq + '\'' +
                ", tdjl='" + tdjl + '\'' +
                ", zt='" + zt + '\'' +
                ", cjrq=" + cjrq +
                ", cjr=" + cjr +
                '}';
    }
}
