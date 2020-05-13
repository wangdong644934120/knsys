package cn.stylefeng.guns.modular.kanong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 二次跟单表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@TableName("kn_ecgd")
public class Ecgd implements Serializable {

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
    @TableField("ecgdr")
    private String ecgdr;

    /**
     * 跟单结果
     */
    @TableField("ecgdjg")
    private String ecgdjg;

    /**
     * 跟单记录
     */
    @TableField("ecgdjl")
    private String ecgdjl;

    /**
     * 创建日期
     */
    @TableField("eccjrq")
    private String eccjrq;

    /**
     * 交付定金
     */
    @TableField("ecjfdj")
    private BigDecimal ecjfdj;


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

    public String getEccjrq() {
        return eccjrq;
    }

    public void setEccjrq(String eccjrq) {
        this.eccjrq = eccjrq;
    }

    public BigDecimal getEcjfdj() {
        return ecjfdj;
    }

    public void setEcjfdj(BigDecimal ecjfdj) {
        this.ecjfdj = ecjfdj;
    }

    @Override
    public String toString() {
        return "Ecgd{" +
        "id=" + id +
        ", knUserId=" + knUserId +
        ", gdr=" + ecgdr +
        ", gdjg=" + ecgdjg +
        ", gdjl=" + ecgdjl +
        ", cjrq=" + eccjrq +
        "}";
    }
}
