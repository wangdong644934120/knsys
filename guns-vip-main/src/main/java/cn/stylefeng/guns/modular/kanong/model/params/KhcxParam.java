package cn.stylefeng.guns.modular.kanong.model.params;

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
public class KhcxParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;
    private String id;
    private String xlxm;//新郎姓名

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

    @Override
    public String checkParam() {
        return null;
    }
}
