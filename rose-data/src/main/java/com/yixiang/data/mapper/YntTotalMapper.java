package com.yixiang.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.entity.YntTotal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 服务点统计表-左屏 Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2020-04-14
 */
public interface YntTotalMapper extends BaseMapper<YntTotal> {

    @Select("select sum(hy_point_num) as hy_point_num ,sum(point_num) as point_num,citycode,city from ynt_district_point_num where citycode =#{citycode} GROUP BY citycode,city")
    YntDistrictPointNum selectCity(@Param("citycode") String citycode);

    @Select("select sum(hy_point_num) as hy_point_num ,sum(point_num) as point_num,areacode,area from ynt_district_point_num  where areacode =#{areacode} GROUP BY areacode,area")
    YntDistrictPointNum selectArea(@Param("areacode") String areacode);

    @Select("select sum(hy_point_num) as hy_point_num ,sum(point_num) as point_num,villagecode,village from ynt_district_point_num  where villagecode =#{villagecode} GROUP BY villagecode,village")
    YntDistrictPointNum selectVillage(@Param("villagecode") String villagecode);

    @Select("select sum(hy_point_num) as hy_point_num ,sum(point_num) as point_num from ynt_district_point_num ")
    YntDistrictPointNum selectProvaince();
}
