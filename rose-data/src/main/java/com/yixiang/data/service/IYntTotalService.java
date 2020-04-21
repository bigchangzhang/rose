package com.yixiang.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.entity.YntTotal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    Map saveExcel(MultipartFile file, String batchCode) throws Exception;

}
