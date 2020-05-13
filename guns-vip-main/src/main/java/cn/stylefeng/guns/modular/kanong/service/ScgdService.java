package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.*;
import cn.stylefeng.guns.modular.kanong.mapper.ScgdMapper;
import cn.stylefeng.guns.modular.kanong.model.params.ScgdParam;
import cn.stylefeng.guns.modular.kanong.model.params.TpParam;
import cn.stylefeng.guns.modular.kanong.model.result.ScgdResult;
import cn.stylefeng.guns.sys.core.properties.GunsProperties;
import cn.stylefeng.guns.util.ImageBase64;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class ScgdService extends ServiceImpl<ScgdMapper, Scgd> {

    @Autowired
    private GunsProperties gunsProperties;
    //新增
    public void add(ScgdParam param){
        this.save(param);
    }
    //删除
    public void delete(ScgdParam param){
        this.removeById(getKey(param));
    }
    //更新
    public void update(ScgdParam param){
        this.updateById(param);
    }
    //查询单条数据，Specification模式
    public ScgdResult findBySpec(ScgdParam param){
        List<ScgdResult> list=this.findListBySpec(param);
        if(list==null||list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    //查询列表，Specification模式
    public List<ScgdResult> findListBySpec(ScgdParam param){
        return this.baseMapper.customList(param);
    }
    //查询分页数据，Specification模式
    public LayuiPageInfo findPageBySpec(ScgdParam param){
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ScgdParam param){
        return param.getId();
    }


    public ScgdResult getSCGDById(ScgdParam param){
        List<ScgdResult> list=this.baseMapper.getSCGDById(param);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

    public ScgdResult getSCGDByKNUserId(String knuserid){
        List<ScgdResult> list=this.baseMapper.getSCGDByKNUserId(knuserid);
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

    public void addECGD(ScgdParam scgdParam){
        Ecgd ecgd=new Ecgd();
        ecgd.setId(UUID.randomUUID().toString());
        ecgd.setKnUserId(scgdParam.getKnUserId());
        this.baseMapper.addEcgd(ecgd);
    }

    public void addDdkh(ScgdParam scgdParam){
        Ddkh ddkh =new Ddkh();
        ddkh.setId(UUID.randomUUID().toString());
        ddkh.setKnUserId(scgdParam.getKnUserId());
        ddkh.setJfdj(scgdParam.getJfdj());
        this.baseMapper.addDdkh(ddkh);
    }
}


