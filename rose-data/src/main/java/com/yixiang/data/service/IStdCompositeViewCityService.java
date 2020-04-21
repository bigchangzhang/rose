package com.yixiang.data.service;

import com.yixiang.data.entity.StdCompositeViewCity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 市视图表 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
public interface IStdCompositeViewCityService extends IService<StdCompositeViewCity> {

    Map saveExcel(MultipartFile file, String batchCode) throws Exception;
}
