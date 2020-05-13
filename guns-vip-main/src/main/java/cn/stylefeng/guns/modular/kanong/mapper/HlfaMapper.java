package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-04-01
 */
public interface HlfaMapper extends BaseMapper<Ddkh> {

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
