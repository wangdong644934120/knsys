package cn.stylefeng.guns.modular.kanong.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2020-04-01
 */
@TableName("kn_sdjg")
public class Sdjg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;

    /**
     * 客户id
     */
    @TableField("kn_user_id")
    private String knUserId;

    /**
     * 总负责人
     */
    @TableField("zfzr")
    private String zfzr;

    /**
     * 摄像师负责人
     */
    @TableField("sxsfzr")
    private String sxsfzr;

    /**
     * 摄像师完成日期
     */
    @TableField("sxswcrq")
    private Date sxswcrq;

    /**
     * 摄影师负责人
     */
    @TableField("sysfzr")
    private String sysfzr;

    /**
     * 摄影师完成日期
     */
    @TableField("syswcrq")
    private Date syswcrq;

    /**
     * 司仪负责人
     */
    @TableField("syfzr")
    private String syfzr;

    /**
     * 司仪完成日期
     */
    @TableField("sywcrq")
    private Date sywcrq;

    /**
     * 化妆师负责人
     */
    @TableField("hzsfzr")
    private String hzsfzr;

    /**
     * 化妆师完成日期
     */
    @TableField("hzswcrq")
    private Date hzswcrq;


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

    public String getSxsfzr() {
        return sxsfzr;
    }

    public void setSxsfzr(String sxsfzr) {
        this.sxsfzr = sxsfzr;
    }

    public Date getSxswcrq() {
        return sxswcrq;
    }

    public void setSxswcrq(Date sxswcrq) {
        this.sxswcrq = sxswcrq;
    }

    public String getSysfzr() {
        return sysfzr;
    }

    public void setSysfzr(String sysfzr) {
        this.sysfzr = sysfzr;
    }

    public Date getSyswcrq() {
        return syswcrq;
    }

    public void setSyswcrq(Date syswcrq) {
        this.syswcrq = syswcrq;
    }

    public String getSyfzr() {
        return syfzr;
    }

    public void setSyfzr(String syfzr) {
        this.syfzr = syfzr;
    }

    public Date getSywcrq() {
        return sywcrq;
    }

    public void setSywcrq(Date sywcrq) {
        this.sywcrq = sywcrq;
    }

    public String getHzsfzr() {
        return hzsfzr;
    }

    public void setHzsfzr(String hzsfzr) {
        this.hzsfzr = hzsfzr;
    }

    public Date getHzswcrq() {
        return hzswcrq;
    }

    public void setHzswcrq(Date hzswcrq) {
        this.hzswcrq = hzswcrq;
    }

    @Override
    public String toString() {
        return "Sdjg{" +
        "id=" + id +
        ", knUserId=" + knUserId +
        ", zfzr=" + zfzr +
        ", sxsfzr=" + sxsfzr +
        ", sxswcrq=" + sxswcrq +
        ", sysfzr=" + sysfzr +
        ", syswcrq=" + syswcrq +
        ", syfzr=" + syfzr +
        ", sywcrq=" + sywcrq +
        ", hzsfzr=" + hzsfzr +
        ", hzswcrq=" + hzswcrq +
        "}";
    }
}
