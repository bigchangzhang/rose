package com.yixiang.data.mapper;

import com.yixiang.data.entity.YntTrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合作视图信息 Mapper 接口
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface YntTradeMapper extends BaseMapper<YntTrade> {

    @Select("<script>"
            +"select trade_type,trade_name,SUM(count) as count from ynt_trade GROUP BY trade_type,trade_name;"
            +"</script>")
    List<Map> selectProInfo();


    @Select("<script>"
            +"select * from ynt_trade WHERE area = #{area} ORDER BY (count+0) DESC"
            +"</script>")
    List<YntTrade> orderBYcountList(@Param("area") String area);

}
