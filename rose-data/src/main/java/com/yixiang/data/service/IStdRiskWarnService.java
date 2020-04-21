package com.yixiang.data.service;

import com.yixiang.data.entity.StdRiskWarn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 风险预警表 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface IStdRiskWarnService extends IService<StdRiskWarn> {

    Boolean saveExcel(List<List<Object>> listByExcel,String batchCode) throws Exception;
}
