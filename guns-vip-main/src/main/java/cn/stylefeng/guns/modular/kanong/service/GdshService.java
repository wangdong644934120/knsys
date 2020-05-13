package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Tp;
import cn.stylefeng.guns.modular.kanong.mapper.EcgdMapper;
import cn.stylefeng.guns.modular.kanong.mapper.GdshMapper;
import cn.stylefeng.guns.modular.kanong.model.params.EcgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.GdshParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.model.result.EcgdResult;
import cn.stylefeng.guns.modular.kanong.model.result.GdshResult;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.util.ImageBase64;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 首次跟单表 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@Service
public class GdshService extends ServiceImpl<GdshMapper, Ecgd> {

    @Autowired
    private GunsProperties gunsProperties;

    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(GdshParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageListSH(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    public GdshResult getGdshById(GdshParam param){
        List<GdshResult> list=this.baseMapper.getGdshById(param);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }
    public void updateJXGD(GdshParam param){
        this.baseMapper.updateJXGD(param);
    }

    public void updateJXGDSC(GdshParam param){
        this.baseMapper.updateJXGDSC(param);
    }

    public void updateJXGDEC(GdshParam param){
        this.baseMapper.updateJXGDEC(param);
    }

    public void updateQRFQ(GdshParam param){
        this.baseMapper.updateQRFQ(param);
    }
}


