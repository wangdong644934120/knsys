package cn.stylefeng.guns.modular.kanong.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class HlsyParam extends Hlsy implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    @Override
    public String checkParam() {
        return null;
    }

}
