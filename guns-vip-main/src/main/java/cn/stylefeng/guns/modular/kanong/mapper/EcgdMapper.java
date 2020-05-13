package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Tp;
import cn.stylefeng.guns.modular.kanong.model.params.EcgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.model.result.EcgdResult;
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
public interface EcgdMapper extends BaseMapper<Ecgd> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    List<EcgdResult> customList(@Param("paramCondition") EcgdParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2020-03-10
     */
    Page<EcgdResult> customPageList(@Param("page") Page page, @Param("paramCondition") EcgdParam paramCondition);

    List<EcgdResult> getECGDById(@Param("paramCondition") EcgdParam paramCondition);

    List<EcgdResult> getECGDByKNUserId(String knuserid);

    public void addPic(@Param("paramCondition") TpParam paramCondition);

    public List<Tp> getPic(@Param("paramCondition") TpParam paramCondition);

    public void updateUser(@Param("paramCondition") KNUser paramCondition);

    public void addDdkh(@Param("ddkh") Ddkh ddkh);

    Page<EcgdResult> customPageListSH(@Param("page") Page page, @Param("paramCondition") EcgdParam paramCondition);

}
