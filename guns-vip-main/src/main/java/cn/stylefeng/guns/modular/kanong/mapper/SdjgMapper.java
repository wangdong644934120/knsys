package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Sdjg;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.params.SdjgParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.modular.kanong.model.result.SdjgResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-04-01
 */
public interface SdjgMapper extends BaseMapper<Ddkh> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    List<DdkhResult> customList(@Param("paramCondition") DdkhParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2020-04-01
     */
    Page<DdkhResult> customPageList(@Param("page") Page page, @Param("paramCondition") DdkhParam paramCondition);

    public int updateJD(@Param("paramCondition") DdkhParam paramCondition);

}
