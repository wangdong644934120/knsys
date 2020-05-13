package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.Ecgd;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.entity.Tp;
import cn.stylefeng.guns.modular.kanong.mapper.EcgdMapper;
import cn.stylefeng.guns.modular.kanong.model.params.EcgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.model.result.EcgdResult;
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
public class EcgdService extends ServiceImpl<EcgdMapper, Ecgd> {

    @Autowired
    private GunsProperties gunsProperties;
    //新增
    public void add(EcgdParam param){
        this.save(param);
    }
    //删除
    public void delete(EcgdParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(EcgdParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public EcgdResult findBySpec(EcgdParam param){
        List<EcgdResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<EcgdResult> findListBySpec(EcgdParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(EcgdParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(EcgdParam param){
        return param.getId();
    }


    public EcgdResult getECGDById(EcgdParam param){
        List<EcgdResult> list=this.baseMapper.getECGDById(param);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

    public EcgdResult getECGDByKNUserId(String knuserid){
        List<EcgdResult> list=this.baseMapper.getECGDByKNUserId(knuserid);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

    public void addPic(TpParam param){
        param.setId(UUID.randomUUID().toString());
        param.setCjrq(System.currentTimeMillis());
        param.setTpzl(Tp.TPZL_SCGD);
        this.baseMapper.addPic(param);
    }

    public List<String> getPic(TpParam param){
        //根据首次跟单id，从数据库图片关联表中读取图片位置
        param.setTpzl(Tp.TPZL_SCGD);
        List<Tp> listTP=this.baseMapper.getPic(param);
        List<String> list= new ArrayList<String>();
        for(Tp tp : listTP){
            list.add(ImageBase64.getPhotoBase64(gunsProperties.getFileUploadPath()+tp.getTplj()));
        }
        return  list;
    }

    public void updateUser(KNUser knUser){
        this.baseMapper.updateUser(knUser);
    }

    public void addDdkh(EcgdParam ecgdParam){
        Ddkh ddkh =new Ddkh();
        ddkh.setId(UUID.randomUUID().toString());
        ddkh.setKnUserId(ecgdParam.getKnUserId());
        ddkh.setJfdj(ecgdParam.getEcjfdj());
        ddkh.setGdr(ecgdParam.getEcgdr());
        this.baseMapper.addDdkh(ddkh);
    }
}


