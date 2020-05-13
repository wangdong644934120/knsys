package cn.stylefeng.guns.modular.kanong.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.stylefeng.guns.modular.kanong.entity.Tp;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author wandong
 * @since 2020-03-12
 */
@Data
public class TpParam extends Tp implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    @Override
    public String checkParam() {
        return null;
    }

}
