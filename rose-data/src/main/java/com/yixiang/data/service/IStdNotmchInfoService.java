package com.yixiang.data.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yixiang.data.entity.StdNotmchInfo;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 不动服务点明细 服务类
 * </p>
 *
 * @author 肉鸡子
 * @since 2020-11-03
 */
public interface IStdNotmchInfoService extends IService<StdNotmchInfo> {

    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

}
