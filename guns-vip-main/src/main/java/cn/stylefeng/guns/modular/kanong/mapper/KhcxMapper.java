package cn.stylefeng.guns.modular.kanong.mapper;

import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.model.params.GdshParam;
import cn.stylefeng.guns.modular.kanong.model.params.KhcxParam;
import cn.stylefeng.guns.modular.kanong.model.result.GdshResult;
import cn.stylefeng.guns.modular.kanong.model.result.KhcxResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户查询Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
public interface KhcxMapper extends BaseMapper<Ecgd> {

    List<KhcxResult> getKhcxById(@Param("paramCondition") KhcxParam paramCondition);

    Page<KhcxResult> customPageListKHCX(@Param("page") Page page, @Param("paramCondition") KhcxParam paramCondition);
}
