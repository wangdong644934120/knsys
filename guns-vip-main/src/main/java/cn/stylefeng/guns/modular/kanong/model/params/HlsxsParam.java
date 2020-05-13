package cn.stylefeng.guns.modular.kanong.model.params;

import cn.stylefeng.guns.modular.kanong.entity.Hlhz;
import cn.stylefeng.guns.modular.kanong.entity.Hlsxs;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 婚礼摄像师
 * </p>
 *
 * @author wangdong
 * @since 2020-03-16
 */
@Data
public class HlsxsParam extends Hlsxs implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    @Override
    public String checkParam() {
        return null;
    }

}
