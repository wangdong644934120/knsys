package cn.stylefeng.guns.modular.kanong.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.kanong.entity.Hlsy;

/**
 * <p>
 * 婚礼司仪
 * </p>
 *
 * @author wangdong
 * @since 2020-03-16
 */
@Data
public class HlsyResult extends Hlsy implements Serializable {

    private static final long serialVersionUID = 1L;
    private String xm; //婚礼姓名

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
}
