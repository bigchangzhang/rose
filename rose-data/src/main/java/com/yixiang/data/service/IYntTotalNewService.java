package com.yixiang.data.service;

import com.yixiang.data.entity.YntTotalNew;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zc
 * @since 2020-08-19
 */
public interface IYntTotalNewService extends IService<YntTotalNew> {

    List<YntTotalNew> getYnt(String areaCode);

    Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception;

    Map saveExcelCity(List<List<Object>> listByExcel, String batchCode) throws Exception;



}
