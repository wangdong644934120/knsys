package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.*;
import cn.stylefeng.guns.modular.kanong.model.params.ScgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.model.result.ScgdResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首次跟单表 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
public interface ScgdMapper extends BaseMapper<Scgd> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    List<ScgdResult> customList(@Param("paramCondition") ScgdParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    Page<ScgdResult> customPageList(@Param("page") Page page, @Param("paramCondition") ScgdParam paramCondition);

    List<ScgdResult> getSCGDById(@Param("paramCondition") ScgdParam paramCondition);

    List<ScgdResult> getSCGDByKNUserId(String knuserid);

    public void addPic(@Param("paramCondition") TpParam paramCondition);

    public List<Tp> getPic(@Param("paramCondition") TpParam paramCondition);

    public void updateUser(@Param("paramCondition") KNUser paramCondition);

    public void addEcgd(@Param("ecgd") Ecgd ecgd);

    public void addDdkh(@Param("ddkh") Ddkh ddkh);

}
