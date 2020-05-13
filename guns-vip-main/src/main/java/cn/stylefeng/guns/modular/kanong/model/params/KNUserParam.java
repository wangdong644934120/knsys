package cn.stylefeng.guns.modular.kanong.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;

import java.io.Serializable;

import cn.stylefeng.guns.modular.kanong.entity.KNUser;

/**
 * <p>
 * 婚礼用户表
 * </p>
 *
 * @author wangdong
 * @since 2020-03-09
 */
@Data
public class KNUserParam extends KNUser implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    @Override
    public String checkParam() {
        return null;
    }

}
