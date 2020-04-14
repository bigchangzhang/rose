package com.yixiang.data.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.mapper.YntDistrictPointNumMapper;
import com.yixiang.data.service.IYntDistrictPointNumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Boolean saveExcel(List<List<Object>> listByExcel) throws Exception{
        List<YntDistrictPointNum> yntDistrictPointNums = new ArrayList<>();
        Boolean back = true;
        Map map = new HashMap<>();
        for (List<Object> list : listByExcel) {
            YntDistrictPointNum yntDistrictPointNum = new YntDistrictPointNum();
            String servNum = String.valueOf(list.get(0));
            String cityName = String.valueOf(list.get(1));
            String areaName= String.valueOf(list.get(2));
            String villigeNme = String.valueOf(list.get(3));
            String cityCode = String.valueOf(list.get(4));
            String areaCode = String.valueOf(list.get(5));
            String villigeCode = String.valueOf(list.get(6));
            if(StringUtils.isEmpty(villigeCode)){
                continue;
            }
            String hgNum = String.valueOf(list.get(7));
            yntDistrictPointNum.setArea(areaName);
            yntDistrictPointNum.setAreacode(areaCode);
            yntDistrictPointNum.setCity(cityName);
            yntDistrictPointNum.setCitycode(cityCode);
            yntDistrictPointNum.setVillage(villigeNme);
            yntDistrictPointNum.setVillagecode(villigeCode);
            yntDistrictPointNum.setPointNum(servNum);
            yntDistrictPointNum.setHyPointNum(hgNum);
            yntDistrictPointNums.add(yntDistrictPointNum);
            boolean b = this.saveOrUpdate(yntDistrictPointNum);
            if (b){

            }else {
                back = false;
                break;
            }
        }
        return back;
    }
}
