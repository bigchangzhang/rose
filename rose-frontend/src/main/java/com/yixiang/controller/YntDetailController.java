package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixiang.data.entity.YntDetail;
import com.yixiang.data.service.IYntDetailService;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 流水表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/yntDetail")
@Api("实时监控页面")
@Log4j2
public class YntDetailController {

    @Autowired
    IYntDetailService iYntDetailService;

    /**
     * 根据区县代码获取流水列表
     */
    @ApiOperation(value = "交易监控")
    @RequestMapping(value = "/getDetailList",method = RequestMethod.GET)
    public Object getDetailList(String area,Integer size) {
        ResultModel resultModel = new ResultModel();
        QueryWrapper<YntDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("txnamt","cntprtacc");
        Page<YntDetail> page = new Page<>(size,20);
        try {

            if (StringUtils.isEmpty(area) ||  "河北省".equals(area)) {
                IPage<YntDetail> page1 = iYntDetailService.page(page, queryWrapper);
                List<YntDetail> records = page1.getRecords();
                resultModel.set(0, "success",records.size(), records);
            }else {
                queryWrapper.eq("city_name",area);
                IPage<YntDetail> page1 = iYntDetailService.page(page, queryWrapper);
                List<YntDetail> records = page1.getRecords();
                resultModel.set(0, "success", records.size(),records);
            }
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }


}
