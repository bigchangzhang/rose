package com.yixiang.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.entity.YntTotal;

import java.util.List;

/**
 * <p>
 * 服务点统计表-左屏 服务类
 * </p>
 *
 * @author zc
 * @since 2020-04-14
 */
public interface IYntTotalService extends IService<YntTotal> {

    YntDistrictPointNum selectCity(String citycode);

    YntDistrictPointNum selectArea(String areacode);

    YntDistrictPointNum selectVillage(String villagecode);

    YntDistrictPointNum selectProvaince();

}
