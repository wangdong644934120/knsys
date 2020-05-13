package cn.stylefeng.guns.modular.kanong.model.result;

import lombok.Data;

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
public class KNUserResult extends KNUser implements Serializable {

    private static final long serialVersionUID = 1L;
}
