package com.yixiang.data.service;

import com.yixiang.data.entity.YntTermCheck;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检表 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface IYntTermCheckService extends IService<YntTermCheck> {

    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

}
