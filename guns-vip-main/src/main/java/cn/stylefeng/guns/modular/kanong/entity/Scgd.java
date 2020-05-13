package cn.stylefeng.guns.modular.kanong.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import org.apache.poi.hpsf.Decimal;

import java.io.Serializable;

/**
 * <p>
 * 首次跟单表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@TableName("kn_scgd")
public class Scgd implements Serializable {

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
     * 跟单人
     */
    @TableField("gdr")
    private String gdr;

    /**
     * 跟单结果
     */
    @TableField("gdjg")
    private String gdjg;

    /**
     * 跟单记录
     */
    @TableField("gdjl")
    private String gdjl;

    /**
     * 创建日期
     */
    @TableField("cjrq")
    private String cjrq;

    /**
     * 交付定金
     */
    @TableField("jfdj")
    private BigDecimal jfdj;


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

    public String getGdr() {
        return gdr;
    }

    public void setGdr(String gdr) {
        this.gdr = gdr;
    }

    public String getGdjg() {
        return gdjg;
    }

    public void setGdjg(String gdjg) {
        this.gdjg = gdjg;
    }

    public String getGdjl() {
        return gdjl;
    }

    public void setGdjl(String gdjl) {
        this.gdjl = gdjl;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public BigDecimal getJfdj() {
        return jfdj;
    }

    public void setJfdj(BigDecimal jfdj) {
        this.jfdj = jfdj;
    }

    @Override
    public String toString() {
        return "Scgd{" +
        "id=" + id +
        ", knUserId=" + knUserId +
        ", gdr=" + gdr +
        ", gdjg=" + gdjg +
        ", gdjl=" + gdjl +
        ", cjrq=" + cjrq +
        ", jfdj=" + jfdj +
        "}";
    }
}
