package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.mapper.FKXMMapper;
import cn.stylefeng.guns.modular.kanong.mapper.HlfarwMapper;
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
 * 婚礼司仪 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2020-03-16
 */
@Service
public class FKXMService extends ServiceImpl<FKXMMapper, Ddkh> {

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

    public DdkhResult getFKXMById(DdkhParam param){
        List<DdkhResult> list=this.baseMapper.getFKXMById(param);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }
    public int updateFKXM(DdkhParam param){
        return this.baseMapper.updateFKXM(param);
    }

}
