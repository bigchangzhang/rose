package com.yixiang.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixiang.data.entity.StdNotmchAll;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 不动服务点数量 服务类
 * </p>
 *
 * @author zc
 * @since 2020-11-03
 */
public interface IStdNotmchAllService extends IService<StdNotmchAll> {

    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

}
