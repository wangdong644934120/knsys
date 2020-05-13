package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Scgd;
import cn.stylefeng.guns.modular.kanong.mapper.KNUserMapper;
import cn.stylefeng.guns.modular.kanong.model.params.KNUserParam;
import cn.stylefeng.guns.modular.kanong.model.params.ScgdParam;
import cn.stylefeng.guns.modular.kanong.model.result.KNUserResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 婚礼用户表 服务实现类
 * </p>
 *
 * @author wangdong
 * @since 2020-03-09
 */
@Service
public class KNUserService extends ServiceImpl<KNUserMapper, KNUser> {

    //新增
    public void add(KNUserParam param){
        //保存用户信息
        param.setZt(KNUser.ZT_SCGD);
        this.save(param);
        //添加首次跟单表记录
        Scgd scgd = new Scgd();
        scgd.setId(UUID.randomUUID().toString());
        scgd.setKnUserId(param.getId());
        this.baseMapper.addSCGD(scgd);


    }
    //删除
    public void delete(KNUserParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(KNUserParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public KNUserResult findBySpec(KNUserParam param){
        List<KNUserResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    public KNUserResult getKNUserByid(String id){
        List<KNUserResult> list=this.baseMapper.getKNUserByid(id);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    //查询列表，Specification模式
    public List<KNUserResult> findListBySpec(KNUserParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(KNUserParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(KNUserParam param){
        return param.getId();
    }

}
