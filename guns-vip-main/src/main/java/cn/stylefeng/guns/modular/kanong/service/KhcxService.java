package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.mapper.KhcxMapper;
import cn.stylefeng.guns.modular.kanong.model.params.GdshParam;
import cn.stylefeng.guns.modular.kanong.model.params.KhcxParam;
import cn.stylefeng.guns.modular.kanong.model.result.GdshResult;
import cn.stylefeng.guns.modular.kanong.model.result.KhcxResult;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首次跟单表 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@Service
public class KhcxService extends ServiceImpl<KhcxMapper, Ecgd> {

    @Autowired
    private GunsProperties gunsProperties;

    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(KhcxParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageListKHCX(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    public KhcxResult getKhcxById(KhcxParam param){
        List<KhcxResult> list=this.baseMapper.getKhcxById(param);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

}


