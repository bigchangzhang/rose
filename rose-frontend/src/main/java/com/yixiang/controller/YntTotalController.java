package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdDistrict;
import com.yixiang.data.entity.YntTotal;
import com.yixiang.data.service.IStdDistrictService;
import com.yixiang.data.service.IYntTotalService;
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
import java.util.stream.Collectors;

/**
 * <p>
 * 服务点统计表-左屏 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/yntTotal")
@Log4j2
@Api("左屏按钮")
public class YntTotalController {

    @Autowired
    IYntTotalService iYntTotalService;

    @Autowired
    IStdDistrictService iStdDistrictService;


    /**
     * 点击市查询 活跃数量和双十占比
     */
    @ApiOperation(value = "饼图占比")
    @RequestMapping(value = "/getPieChattInfo",method = RequestMethod.GET)
    public Object getPieChattInfo(String area) {
        ResultModel resultModel = new ResultModel();
        try {

            QueryWrapper<YntTotal> queryWrapper = new QueryWrapper<>();

            if(StringUtils.isNotEmpty(area)){
                queryWrapper.eq("area_code", area);
            }else{
                queryWrapper.eq("area_code", "130000");
            }

            List<YntTotal> list = iYntTotalService.list(queryWrapper);
            resultModel.set(0, "success", list);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

    /**
     * 点击黄色按钮查询对应笔数
     */
    @ApiOperation(value = "黄色按钮")
    @RequestMapping(value = "/getPointList",method = RequestMethod.GET)
    public Object getPointList(String pointIde) {
        ResultModel resultModel = new ResultModel();
        try {
            QueryWrapper<StdDistrict> queryWrapperstd = new QueryWrapper<>();
            queryWrapperstd.eq("PARENT_CODE","13000000");
            List<StdDistrict> stdDistricts = iStdDistrictService.list(queryWrapperstd);
            if(stdDistricts.isEmpty()){
                resultModel.set(1, "获取父级信息为空，当前地区{}",null);
                return resultModel;
            }
            List<String> collect = stdDistricts.stream().map(s -> s.getCodeValue()).collect(Collectors.toList());



            QueryWrapper<YntTotal> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("area_code",collect);
            //1助农贷款、2现金汇款、3转账汇款、4定活互转、5裕农快贷、6社保卡、7etc、8药品追溯、9社保缴费
            //TODO
            if("1".equals(pointIde)){
                queryWrapper.orderByDesc("znqk_year_num");
            }
            if("2".equals(pointIde)){
                queryWrapper.orderByDesc("xjhk_year_num");
            }
            if("3".equals(pointIde)){
                queryWrapper.orderByDesc("zzhk_year_num");
            }
            if("4".equals(pointIde)){
                queryWrapper.orderByDesc("dhhz_year_num");
            }
            if("5".equals(pointIde)){
                queryWrapper.orderByDesc("ynkd_dkkh_num");
            }
            if("6".equals(pointIde)){
                queryWrapper.orderByDesc("sbk_num");
            }
            if("7".equals(pointIde)){
                queryWrapper.orderByDesc("etc_qgkh_num");
            }
            if("8".equals(pointIde)){
                queryWrapper.orderByDesc("yp_zs_num");
            }
            if("9".equals(pointIde)){
                queryWrapper.orderByDesc("sbjf_num");
            }

            List<YntTotal> list = iYntTotalService.list(queryWrapper);
            resultModel.set(0, "success", list);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }


}
