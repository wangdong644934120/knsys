package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.mapper.HlfaMapper;
import cn.stylefeng.guns.modular.kanong.mapper.SdjgMapper;
import cn.stylefeng.guns.modular.kanong.model.params.DdkhParam;
import cn.stylefeng.guns.modular.kanong.model.result.DdkhResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2020-04-01
 */
@Service
public class HlfaService extends ServiceImpl<HlfaMapper, Ddkh> {

    //新增
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
        return null;
    }

    public int updateJD(DdkhParam param){
        return this.baseMapper.updateJD(param);
    }
}
