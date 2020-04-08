package com.yixiang.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdCompositeViewCity;
import com.yixiang.data.entity.StdCompositeViewProvince;
import com.yixiang.data.service.IStdCompositeViewCityService;
import com.yixiang.data.service.IStdCompositeViewProvinceService;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 省视图表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/stdCompositeViewProvince")
@Api("省视图页面")
@Log4j2
public class StdCompositeViewProvinceController {

    @Autowired
    IStdCompositeViewProvinceService iStdCompositeViewProvinceService;

    @Autowired
    IStdCompositeViewCityService iStdCompositeViewCityService;

    @ApiOperation(value = "获取地区")
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public ResultModel query(String reportPlace, String provinceCode){
        ResultModel resultModel = new ResultModel();
        if (StringUtils.isBlank(reportPlace)) {
            return new ResultModel();
        }
        QueryWrapper qr = new QueryWrapper();
        qr.eq("report_place", reportPlace);

        if (StringUtils.isBlank(provinceCode) || "130000".equals(provinceCode)){
            List<StdCompositeViewProvince> list = iStdCompositeViewProvinceService.list(qr);
            Map<String, List<StdCompositeViewProvince>> collect = list.stream().collect(Collectors.groupingBy(s -> s.getReportPlace()));
            resultModel.set(0, "success", collect);
        }else {
            QueryWrapper qee = new QueryWrapper();
            qee.eq("report_place",reportPlace);
            qee.eq("city_code",provinceCode);
            List<StdCompositeViewCity> list = iStdCompositeViewCityService.list(qee);
            Map<String, List<StdCompositeViewCity>> collect = list.stream().collect(Collectors.groupingBy(s -> s.getReportPlace()));
            resultModel.set(0, "success", collect);
        }
        return resultModel;
    }


}
