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
 * 首次跟单表 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2020-03-10
 */
@Service
public class DdkhService extends ServiceImpl<DdkhMapper, Ddkh> {

    @Autowired
    private GunsProperties gunsProperties;
    //新增
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public void add(DdkhParam param){
        this.save(param);
    }
    //删除
    public void delete(DdkhParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(DdkhParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public DdkhResult findBySpec(DdkhParam param){
        List<DdkhResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<DdkhResult> findListBySpec(DdkhParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(DdkhParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DdkhParam param){
        return param.getId();
    }


    public DdkhResult getDdkhById(DdkhParam param){
        List<DdkhResult> list=this.baseMapper.getDdkhById(param);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }
    public void updateDDKH(DdkhParam ddkhParam){
        this.baseMapper.updateDDKH(ddkhParam);
    }

    public void updateUser(KNUser knUser){
        this.baseMapper.updateUser(knUser);
    }

    public List<DdkhResult> getALLJD(DdkhParam param){
        return this.baseMapper.getALLJD(param);
    }

}


