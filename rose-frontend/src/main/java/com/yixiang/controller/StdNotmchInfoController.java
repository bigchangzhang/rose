package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixiang.data.entity.StdNotmchInfo;
import com.yixiang.data.entity.StdRiskWarn;
import com.yixiang.data.service.IStdNotmchInfoService;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 不动服务点明细 前端控制器
 * </p>
 *
 * @author 肉鸡子
 * @since 2020-11-03
 */
@RestController
@RequestMapping("/stdNotmchInfo")
@Log4j2
public class StdNotmchInfoController {


    @Autowired
    IStdNotmchInfoService iStdNotmchInfoService;

    @ApiOperation(value = "不动服务点明细")
    @RequestMapping(value = "/getNotmchList",method = RequestMethod.GET)
    public Object getNotmchList(String orgCode ,Integer size) {
        ResultModel resultModel = new ResultModel();
        QueryWrapper<StdNotmchInfo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isEmpty(orgCode)){

        }else {
            queryWrapper.eq("org_code",orgCode);
        }
        Page<StdNotmchInfo> page = new Page<>(size,20);
        try {
            IPage<StdNotmchInfo> page1 = iStdNotmchInfoService.page(page, queryWrapper);
            List<StdNotmchInfo> records = page1.getRecords();
            resultModel.set(0, "success", records.size(),records);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

}
