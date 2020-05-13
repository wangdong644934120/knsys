package cn.stylefeng.guns.modular.kanong.model.params;

import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class GdshParam  implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;
    private String id;
    private String xlxm;//新郎姓名
    private String xnxm;//新娘姓名
    private String zt;//人员状态

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

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Override
    public String checkParam() {
        return null;
    }
}
