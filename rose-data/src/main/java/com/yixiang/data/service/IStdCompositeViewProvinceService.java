package com.yixiang.data.service;

import com.yixiang.data.entity.StdCompositeViewProvince;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 省视图表 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface IStdCompositeViewProvinceService extends IService<StdCompositeViewProvince> {

    Map saveExcel(MultipartFile file, String batchCode) throws Exception;

}
