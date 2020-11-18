package com.yixiang.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixiang.data.entity.YntTotalPt;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2020-11-18
 */
public interface IYntTotalPtService extends IService<YntTotalPt> {

    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

}
