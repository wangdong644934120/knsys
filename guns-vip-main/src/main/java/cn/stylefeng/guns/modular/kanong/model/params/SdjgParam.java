package cn.stylefeng.guns.modular.kanong.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class SdjgParam extends Sdjg implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    @Override
    public String checkParam() {
        return null;
    }

}
