package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Tp;
import cn.stylefeng.guns.modular.kanong.model.params.EcgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.GdshParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.model.result.EcgdResult;
import cn.stylefeng.guns.modular.kanong.model.result.GdshResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 二次跟单表 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
public interface GdshMapper extends BaseMapper<Ecgd> {

    List<GdshResult> getGdshById(@Param("paramCondition") GdshParam paramCondition);

    Page<GdshResult> customPageListSH(@Param("page") Page page, @Param("paramCondition") GdshParam paramCondition);

    void updateJXGD(@Param("paramCondition") GdshParam paramCondition);

    void updateJXGDSC(@Param("paramCondition") GdshParam paramCondition);

    void updateJXGDEC(@Param("paramCondition") GdshParam paramCondition);

    void updateQRFQ(@Param("paramCondition") GdshParam paramCondition);
}
