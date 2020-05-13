package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.mapper.DdkhMapper;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 订单审核服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@Service
public class DdshService extends ServiceImpl<DdkhMapper, Ddkh> {

    @Autowired
    private GunsProperties gunsProperties;
    //新增
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)

    public LayuiPageInfo findPageBySpec(DdkhParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }
    public void updateDDSHUser(DdkhParam ddkhParam){
        this.baseMapper.updateDDSHUser(ddkhParam);
    }

    public DdkhResult getDdkhshById(DdkhParam ddkhParam){
        List<DdkhResult> list=this.baseMapper.getDdshById(ddkhParam);
        if(list!=null && !list.isEmpty()){
            list.get(0).setZt("订单成交");
            return list.get(0);
        }else{
            return null;
        }
    }
}


