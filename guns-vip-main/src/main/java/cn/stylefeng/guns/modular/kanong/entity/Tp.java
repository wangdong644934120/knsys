package cn.stylefeng.guns.modular.kanong.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author wandong
 * @since 2020-03-12
 */
@TableName("kn_tp")
public class Tp implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String TPZL_SCGD="1";
    public static String TPZL_ECGD="2";

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 图片种类
     */
    @TableField("tpzl")
    private String tpzl;

    /**
     * 图片种类id
     */
    @TableField("tpzl_id")
    private String tpzlId;

    /**
     * 图片路径
     */
    @TableField("tplj")
    private String tplj;

    /**
     * 创建日期
     */
    @TableField("cjrq")
    private Long cjrq;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpzl() {
        return tpzl;
    }

    public void setTpzl(String tpzl) {
        this.tpzl = tpzl;
    }

    public String getTpzlId() {
        return tpzlId;
    }

    public void setTpzlId(String tpzlId) {
        this.tpzlId = tpzlId;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
    }

    public Long getCjrq() {
        return cjrq;
    }

    public void setCjrq(Long cjrq) {
        this.cjrq = cjrq;
    }

    @Override
    public String toString() {
        return "Tp{" +
        "id=" + id +
        ", tpzl=" + tpzl +
        ", tpzlId=" + tpzlId +
        ", tplj=" + tplj +
        ", cjrq=" + cjrq +
        "}";
    }
}
