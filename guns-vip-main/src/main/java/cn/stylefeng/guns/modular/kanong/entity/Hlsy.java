package cn.stylefeng.guns.modular.kanong.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 婚礼司仪
 * </p>
 *
 * @author wangdong
 * @since 2020-03-16
 */
@TableName("kn_hlsy")
public class Hlsy implements Serializable {

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
     * 司仪姓名
     */
    @TableField("syxm")
    private String syxm;

    /**
     * 司仪电话
     */
    @TableField("sydh")
    private String sydh;

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

    public String getSyxm() {
        return syxm;
    }

    public void setSyxm(String syxm) {
        this.syxm = syxm;
    }

    public String getSydh() {
        return sydh;
    }

    public void setSydh(String sydh) {
        this.sydh = sydh;
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
        ", syxm=" + syxm +
        ", sydh=" + sydh +
        ", tdpz=" + tdpz +
        ", ydrq=" + ydrq +
        ", fzr=" + fzr +
        ", sfwc=" + sfwc +
        ", cjsj=" + cjsj +
        "}";
    }
}
