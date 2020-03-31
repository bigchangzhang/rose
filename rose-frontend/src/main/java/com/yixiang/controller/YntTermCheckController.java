package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixiang.data.entity.StdRiskWarn;
import com.yixiang.data.entity.YntTermCheck;
import com.yixiang.data.service.IYntTermCheckService;
import com.yixiang.rose.common.utils.ResultModel;
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
 * 巡检表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/termCheck")
@Api("巡检")
@Log4j2
public class YntTermCheckController {

    @Autowired
    IYntTermCheckService iYntTermCheckService;

    @ApiOperation(value = "巡检视图")
    @RequestMapping(value = "/getTermCheckList",method = RequestMethod.GET)
    public Object getDetailList(Integer size) {
        ResultModel resultModel = new ResultModel();
        QueryWrapper<YntTermCheck> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("jtm_change_date");
        Page<YntTermCheck> page = new Page<>(size,20);
        try {
            IPage<YntTermCheck> page1 = iYntTermCheckService.page(page, queryWrapper);
            List<YntTermCheck> records = page1.getRecords();
            resultModel.set(0, "success", records.size(),records);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

}
