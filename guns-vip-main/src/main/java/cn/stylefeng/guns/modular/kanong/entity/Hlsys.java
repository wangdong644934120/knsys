package cn.stylefeng.guns.modular.kanong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 婚礼摄影师
 * </p>
 *
 * @author wangdong
 * @since 2020-03-16
 */
@TableName("kn_hlsys")
public class Hlsys implements Serializable {

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
     * 摄影师姓名
     */
    @TableField("sysxm")
    private String sysxm;

    /**
     *  摄影师电话
     */
    @TableField("sysdh")
    private String sysdh;

    /**
     * 团队配置
     */
    @TableField("tdpz")
    private String tdpz;

    /**
     * 预定日期
     */
    @TableField("ydrq")
    private String ydrq;

    /**
     * 负责人
     */
    @TableField("fzr")
    private String fzr;

    /**
     * 是否完成
     */
    @TableField("sfwc")
    private String sfwc;

    /**
     * 创建时间
     */
    @TableField("cjsj")
    private Date cjsj;


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

    public String getSysxm() {
        return sysxm;
    }

    public void setSysxm(String sysxm) {
        this.sysxm = sysxm;
    }

    public String getSysdh() {
        return sysdh;
    }

    public void setSysdh(String sysdh) {
        this.sysdh = sysdh;
    }

    public String getTdpz() {
        return tdpz;
    }

    public void setTdpz(String tdpz) {
        this.tdpz = tdpz;
    }

    public String getYdrq() {
        return ydrq;
    }

    public void setYdrq(String ydrq) {
        this.ydrq = ydrq;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getSfwc() {
        return sfwc;
    }

    public void setSfwc(String sfwc) {
        this.sfwc = sfwc;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    @Override
    public String toString() {
        return "Hlsy{" +
        "id=" + id +
        ", knUserId=" + knUserId +
        ", sysxm=" + sysxm +
        ", sysdh=" + sysdh +
        ", tdpz=" + tdpz +
        ", ydrq=" + ydrq +
        ", fzr=" + fzr +
        ", sfwc=" + sfwc +
        ", cjsj=" + cjsj +
        "}";
    }
}
