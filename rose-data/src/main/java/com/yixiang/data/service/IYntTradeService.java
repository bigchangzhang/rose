package com.yixiang.data.service;

import com.yixiang.data.entity.YntTrade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合作视图信息 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface IYntTradeService extends IService<YntTrade> {

    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

    List<Map> selectProInfo();

    List<YntTrade> orderBYcountList(String area);

}
