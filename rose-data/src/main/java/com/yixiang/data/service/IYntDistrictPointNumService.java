package com.yixiang.data.service;

import com.yixiang.data.entity.YntDistrictPointNum;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务点数据 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface IYntDistrictPointNumService extends IService<YntDistrictPointNum> {

    List<YntDistrictPointNum> selectCity();

    List<YntDistrictPointNum> selectArea(String citycode);

    List<YntDistrictPointNum> selectVillage(String areacode);

    Boolean saveExcel(List<List<Object>> listByExcel) throws Exception;


}
