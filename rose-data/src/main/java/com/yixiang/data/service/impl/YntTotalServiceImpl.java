package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.entity.YntTotal;
import com.yixiang.data.mapper.YntTotalMapper;
import com.yixiang.data.service.IYntTotalService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务点统计表-左屏 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-04-14
 */
@Service
public class YntTotalServiceImpl extends ServiceImpl<YntTotalMapper, YntTotal> implements IYntTotalService {

    @Override
    public YntDistrictPointNum   selectCity(String citycode) {
        return this.baseMapper.selectCity(citycode);
    }

    @Override
    public YntDistrictPointNum selectArea(String areacode) {
        return this.baseMapper.selectArea(areacode);
    }

    @Override
    public YntDistrictPointNum selectVillage(String villagecode) {
        return this.baseMapper.selectVillage(villagecode);
    }

    @Override
    public YntDistrictPointNum selectProvaince() {
        return this.baseMapper.selectProvaince();
    }
}
