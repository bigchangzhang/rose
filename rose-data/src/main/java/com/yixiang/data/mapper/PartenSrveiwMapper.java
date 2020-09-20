package com.yixiang.data.mapper;

import com.yixiang.data.entity.PartenSrveiw;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixiang.data.entity.YntDistrictPointNum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收入表 Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2020-04-28
 */
public interface PartenSrveiwMapper extends BaseMapper<PartenSrveiw> {


    @Select("select parten_nm from parten_srveiw GROUP BY parten_nm")
    List<PartenSrveiw> selectNm();

    @Select("SELECT" +
            " DATE_FORMAT(srtime, '%Y') years," +
            " sum(" +
            " cast(parten_sr AS DECIMAL(17, 3))" +
            " ) parten_sr," +
            " '纵向收入' as zhanghao" +
            " FROM" +
            " parten_srveiw" +
            " WHERE" +
            " (LEFT (parten_zh_bs, 1) = 'A'" +
            " OR LEFT (parten_zh_bs, 1) = 'B'" +
            " OR LEFT (parten_zh_bs, 1) = 'C'" +
            " OR LEFT (parten_zh_bs, 1) = 'D'" +
            " OR LEFT (parten_zh_bs, 1) = 'E')" +
            " and parten_nm = #{parten_nm}" +
            "and (LEFT (parten_zh_bs, 2) != 'A8')" +
            "and (LEFT (parten_zh_bs, 2) != 'AJ')" +
            "and (LEFT (parten_zh_bs, 2) != 'AM')" +
            "and (LEFT (parten_zh_bs, 2) != 'AP')" +
            "and (LEFT (parten_zh_bs, 2) != 'AR')" +
            "and (LEFT (parten_zh_bs, 2) != 'AT')" +
            "and (LEFT (parten_zh_bs, 2) != 'AZ')" +
            "and (LEFT (parten_zh_bs, 2) != 'AW')"+
            " GROUP BY" +
            " years;")
    List<Map> selectZong(@Param("parten_nm") String parten_nm);

    @Select("SELECT" +
            " DATE_FORMAT(srtime, '%Y') years," +
            " sum(" +
            " cast(parten_sr AS DECIMAL(17, 3))" +
            " ) parten_sr," +
            " '横向收入' as zhanghao" +
            " FROM" +
            " parten_srveiw" +
            " WHERE" +
            " (LEFT (parten_zh_bs, 1) = 'F'" +
            " OR LEFT (parten_zh_bs, 1) = 'G'" +
            " OR LEFT (parten_zh_bs, 2) = 'K1'" +
            " OR LEFT (parten_zh_bs, 2) = 'K2'" +
            " OR LEFT (parten_zh_bs, 2) = 'K3'" +
            " OR LEFT (parten_zh_bs, 2) = 'K4'" +
            " OR LEFT (parten_zh_bs, 2) = 'K5'" +
            " OR LEFT (parten_zh_bs, 2) = 'K6'" +
            " OR LEFT (parten_zh_bs, 2) = 'K7'" +
            " OR LEFT (parten_zh_bs, 2) = 'K8')" +
            " and parten_nm = #{parten_nm}" +
            " GROUP BY" +
            " years;")
    List<Map> selectHeng(@Param("parten_nm") String parten_nm);

    @Select("SELECT" +
            " DATE_FORMAT(srtime, '%Y') years," +
            " sum(" +
            " cast(parten_sr AS DECIMAL(17, 3))" +
            " ) parten_sr," +
            " '高技术收入' as zhanghao" +
            " FROM" +
            " parten_srveiw" +
            " WHERE" +
            " ((LEFT (parten_zh_bs, 1) = 'Z')" +
            " or (LEFT (parten_zh_bs, 2) = 'KA')" +
            " or (LEFT (parten_zh_bs, 2) = 'KB')" +
            " or (LEFT (parten_zh_bs, 2) = 'KC')" +
            "or (LEFT (parten_zh_bs, 2) = 'A8')" +
            "or (LEFT (parten_zh_bs, 2) = 'AJ')" +
            "or (LEFT (parten_zh_bs, 2) = 'AM')" +
            "or (LEFT (parten_zh_bs, 2) = 'AP')" +
            "or (LEFT (parten_zh_bs, 2) = 'AR')" +
            "or (LEFT (parten_zh_bs, 2) = 'AT')" +
            "or (LEFT (parten_zh_bs, 2) = 'AZ')" +
            "or (LEFT (parten_zh_bs, 2) = 'AW')"+
            " or (LEFT (parten_zh_bs, 2) = 'KD'))" +
            " and parten_nm = #{parten_nm}" +
            " GROUP BY" +
            " years;")
    List<Map> selectGao(@Param("parten_nm") String parten_nm);

    @Select("SELECT" +
            " DATE_FORMAT(srtime, '%Y') years," +
            " sum(" +
            " cast(parten_sr AS DECIMAL(17, 3))" +
            " ) parten_sr," +
            " '所内收入' as zhanghao" +
            " FROM" +
            " parten_srveiw" +
            " WHERE" +
            " (LEFT (parten_zh_bs, 1) = 'Y')" +
            " AND parten_nm = #{parten_nm}" +
            " GROUP BY" +
            " years;")
    List<Map> selectSuo(@Param("parten_nm") String parten_nm);


}
