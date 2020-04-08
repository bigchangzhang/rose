package com.yixiang.data.service.impl;

import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.mapper.YntDistrictPointNumMapper;
import com.yixiang.data.service.IYntDistrictPointNumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务点数据 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class YntDistrictPointNumServiceImpl extends ServiceImpl<YntDistrictPointNumMapper, YntDistrictPointNum> implements IYntDistrictPointNumService {

    @Override
    public List<YntDistrictPointNum> selectCity() {
        return this.getBaseMapper().selectCity();
    }

    @Override
    public List<YntDistrictPointNum> selectArea(String citycode) {
        return this.baseMapper.selectArea(citycode);
    }

    @Override
    public List<YntDistrictPointNum> selectVillage(String areacode) {
        return this.baseMapper.selectVillage(areacode);
    }
}
