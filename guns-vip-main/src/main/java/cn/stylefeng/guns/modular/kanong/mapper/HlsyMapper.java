package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Hlsy;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.params.HlhzParam;
import cn.stylefeng.guns.modular.kanong.model.params.HlsyParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.modular.kanong.model.result.HlhzResult;
import cn.stylefeng.guns.modular.kanong.model.result.HlsyResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 婚礼司仪 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-03-16
 */
public interface HlsyMapper extends BaseMapper<Ddkh> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    List<DdkhResult> customList(@Param("paramCondition") DdkhParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2020-03-16
     */
    Page<DdkhResult> customPageList(@Param("page") Page page, @Param("paramCondition") DdkhParam paramCondition);


    List<DdkhResult> getHlsyById(@Param("paramCondition") DdkhParam paramCondition);

    public int updateRW(@Param("paramCondition") DdkhParam paramCondition);

}
