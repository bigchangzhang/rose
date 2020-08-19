package com.yixiang.data.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yixiang.data.entity.StdImmBt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 蓝色按钮表 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-23
 */
public interface IStdImmBtService extends IService<StdImmBt> {
    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

}
