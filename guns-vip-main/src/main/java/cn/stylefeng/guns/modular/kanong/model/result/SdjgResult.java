package cn.stylefeng.guns.modular.kanong.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.kanong.entity.Sdjg;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2020-04-01
 */
@Data
public class SdjgResult extends Sdjg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xlxm;
    private String xnxm;
    private String zfzrid;

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

    public String getZfzrid() {
        return zfzrid;
    }

    public void setZfzrid(String zfzrid) {
        this.zfzrid = zfzrid;
    }
}
