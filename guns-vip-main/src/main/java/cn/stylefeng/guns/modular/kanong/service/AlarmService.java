package cn.stylefeng.guns.modular.kanong.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.kanong.entity.Ddkh;
import cn.stylefeng.guns.modular.kanong.entity.KNUser;
import cn.stylefeng.guns.modular.kanong.mapper.AlarmMapper;
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
public class AlarmService extends ServiceImpl<AlarmMapper, Ddkh> {


    public List<DdkhResult> find(){
        List<DdkhResult> list=this.baseMapper.alarmList();
        return list;
    }


}


