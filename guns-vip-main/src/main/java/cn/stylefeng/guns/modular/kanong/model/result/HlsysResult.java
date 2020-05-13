package cn.stylefeng.guns.modular.kanong.model.result;

import cn.stylefeng.guns.modular.kanong.entity.Hlhz;
import cn.stylefeng.guns.modular.kanong.entity.Hlsys;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 婚礼摄影师
 * </p>
 *
 * @author wangdong
 * @since 2020-03-16
 */
@Data
public class HlsysResult extends Hlsys implements Serializable {

    private static final long serialVersionUID = 1L;
    private String xm; //婚礼姓名

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
}
