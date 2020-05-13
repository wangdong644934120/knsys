package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Tp;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.model.result.TpResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图片表 Mapper 接口
 * </p>
 *
 * @author wandong
 * @since 2020-03-12
 */
public interface TpMapper extends BaseMapper<Tp> {

    /**
     * 获取列表
     *
     * @author wandong
     * @Date 2020-03-12
     */
    List<TpResult> customList(@Param("paramCondition") TpParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wandong
     * @Date 2020-03-12
     */
    Page<TpResult> customPageList(@Param("page") Page page, @Param("paramCondition") TpParam paramCondition);

}
