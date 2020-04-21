package com.yixiang.data.service;

import com.yixiang.data.entity.YqEcpGpfPoint;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务点表 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface IYqEcpGpfPointService extends IService<YqEcpGpfPoint> {

    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

}
