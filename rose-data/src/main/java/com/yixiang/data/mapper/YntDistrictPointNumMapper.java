package com.yixiang.data.mapper;

import com.yixiang.data.entity.YntDistrictPointNum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 服务点数据 Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface YntDistrictPointNumMapper extends BaseMapper<YntDistrictPointNum> {


    @Select("select sum(hy_point_num) as hy_point_num ,sum(point_num) as point_num,citycode,city from ynt_district_point_num GROUP BY citycode,city")
    List<YntDistrictPointNum> selectCity();

    @Select("select sum(hy_point_num) as hy_point_num ,sum(point_num) as point_num,areacode,area from ynt_district_point_num  where citycode =#{citycode} GROUP BY areacode,area")
    List<YntDistrictPointNum> selectArea(@Param("citycode") String citycode);

    @Select("select sum(hy_point_num) as hy_point_num ,sum(point_num) as point_num,villagecode,village from ynt_district_point_num  where areacode =#{areacode} GROUP BY villagecode,village")
    List<YntDistrictPointNum> selectVillage(@Param("areacode") String areacode);

}
