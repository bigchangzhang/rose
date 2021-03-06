package com.yixiang.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixiang.data.entity.StdTop;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * <p>
 * Top50表 服务类
 * </p>
 *
 * @author zc
 * @since 2020-03-24
 */
public interface IStdTopService extends IService<StdTop> {

    Map saveExcel(MultipartFile file, String batchCode) throws Exception;

}
