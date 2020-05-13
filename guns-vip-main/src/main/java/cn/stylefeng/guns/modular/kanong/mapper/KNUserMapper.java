package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Scgd;
import cn.stylefeng.guns.modular.kanong.model.params.KNUserParam;
import cn.stylefeng.guns.modular.kanong.model.params.ScgdParam;
import cn.stylefeng.guns.modular.kanong.model.result.KNUserResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 婚礼用户表 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-03-09
 */
public interface KNUserMapper extends BaseMapper<KNUser> {

    /**
     * 获取列表
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    List<KNUserResult> customList(@Param("paramCondition") KNUserParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author wangdong
     * @Date 2020-03-09
     */
    Page<KNUserResult> customPageList(@Param("page") Page page, @Param("paramCondition") KNUserParam paramCondition);

    void addSCGD(@Param("scgd") Scgd scgd);

    public List<KNUserResult> getKNUserByid(String id);



}
