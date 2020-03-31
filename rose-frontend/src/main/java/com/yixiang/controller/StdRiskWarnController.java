package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixiang.data.entity.StdRiskWarn;
import com.yixiang.data.entity.YntDetail;
import com.yixiang.data.service.IStdRiskWarnService;
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

/**
 * <p>
 * 风险预警表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/riskWarn")
@Api("风险预警")
@Log4j2
public class StdRiskWarnController {

    @Autowired
    IStdRiskWarnService iStdRiskWarnService;


    @ApiOperation(value = "风险预警")
    @RequestMapping(value = "/getRiskWarnList",method = RequestMethod.GET)
    public Object getDetailList(Integer size) {
        ResultModel resultModel = new ResultModel();
        QueryWrapper<StdRiskWarn> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("trs_date");
        Page<StdRiskWarn> page = new Page<>(size,20);
        try {
            IPage<StdRiskWarn> page1 = iStdRiskWarnService.page(page, queryWrapper);
            List<StdRiskWarn> records = page1.getRecords();
            resultModel.set(0, "success", records.size(),records);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

}
