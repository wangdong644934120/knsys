package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Tp;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单客户 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
public interface DdkhMapper extends BaseMapper<Ddkh> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    List<DdkhResult> customList(@Param("paramCondition") DdkhParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    Page<DdkhResult> customPageList(@Param("page") Page page, @Param("paramCondition") DdkhParam paramCondition);

    List<DdkhResult> getDdkhById(@Param("paramCondition") DdkhParam paramCondition);

    public void updateDDKH(@Param("paramCondition") DdkhParam paramCondition);

    public void updateUser(@Param("paramCondition") KNUser paramCondition);

    List<DdkhResult> getALLJD(@Param("paramCondition") DdkhParam paramCondition);

    List<DdkhResult> getDDKHSH(@Param("paramCondition") DdkhParam paramCondition);

    List<DdkhResult> getDdshById(@Param("paramCondition") DdkhParam paramCondition);

    public void updateDDSHUser(@Param("paramCondition") DdkhParam paramCondition);


}
