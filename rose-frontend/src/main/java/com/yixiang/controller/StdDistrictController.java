package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdDistrict;
import com.yixiang.data.service.IStdDistrictService;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 县区代码表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/district")
@Api("地区页面")
@Log4j2
public class StdDistrictController {

    @Autowired
    IStdDistrictService iStdDistrictService;


    @ApiOperation(value = "获取地区")
    @RequestMapping("/getList")
    public Object getList() {
        ResultModel resultModel = new ResultModel();
        try {
            List<StdDistrict> ecpJxtRegions = iStdDistrictService.getBaseMapper()
                    .selectList(null);

            Map<String, List<StdDistrict>> areaGroup = ecpJxtRegions.stream()
                    .collect(Collectors.groupingBy(r -> r.getCodeValue()));

            List<StdDistrict> collect = ecpJxtRegions.stream().filter(r -> "2".equals(r.getType()))
                    .collect(Collectors.toList());
            HashMap<String, Object> cityMap = new HashMap<>();
            collect.forEach(r -> {
                cityMap.put(r.getCodeValue(), r.getCodeName());
            });

            List<StdDistrict> collect1 = ecpJxtRegions.stream().filter(r -> "3".equals(r.getType()))
                    .collect(Collectors.toList());
            HashMap<String, Object> areaMap = new HashMap<>();
            collect1.forEach(r -> {
                areaMap.put(StringUtils.subString(r.getCodeValue(), 0, 4) + "_" + StringUtils
                                .subString(r.getCodeValue(), 4, 6),
                        areaGroup.get(r.getParentCode()).get(0).getCodeName() + "_" + r.getCodeName());
            });

            List<StdDistrict> collect2 = ecpJxtRegions.stream().filter(r -> "4".equals(r.getType()))
                    .collect(Collectors.toList());
            HashMap<String, Object> streetMap = new HashMap<>();
            collect2.forEach(r -> {

                log.info(StringUtils.subString(r.getCodeValue(), 0, 6) + "_" +
                        StringUtils.subString(r.getCodeValue(), 6, 9) + "   " +
                        (areaGroup.get(r.getParentCode()) != null ? areaGroup.get(r.getParentCode())
                                .get(0).getCodeName() : "") + "_" + r.getCodeName());
                streetMap.put(StringUtils.subString(r.getCodeValue(), 0, 6) + "_" +
                                StringUtils.subString(r.getCodeValue(), 6, 9),
                        (areaGroup.get(r.getParentCode()) != null ? areaGroup.get(r.getParentCode())
                                .get(0).getCodeName() : "") + "_" + r.getCodeName());
            });

            List<StdDistrict> collect4 = ecpJxtRegions.stream().filter(r -> "5".equals(r.getType()))
                    .collect(Collectors.toList());
            HashMap<String, Object> villageMap = new HashMap<>();
            collect4.forEach(r -> {
                villageMap.put(StringUtils.subString(r.getCodeValue(), 0, 9) + "_" + StringUtils
                                .subString(r.getCodeValue(), 9, 12),
                        (areaGroup.get(r.getParentCode()) != null ? areaGroup.get(r.getParentCode())
                                .get(0).getCodeName() : "") + "_" + r.getCodeName());
            });

            ArrayList<Object> objects = new ArrayList<>();
            objects.add(cityMap);
            objects.add(areaMap);
            //objects.add(streetMap);
            //objects.add(villageMap);
            log.info("输出地区" + objects);
            resultModel.set(0, "success", objects);
        } catch (Exception e) {
            log.error(e.getMessage());
            resultModel.set(1, "获取信息失败", e.getMessage());
        }
        return resultModel;
    }


    @ApiOperation(value = "获取一个地区")
    @RequestMapping("/getAreaByCode")
    public Object getAreaByCode(String area) {
        ResultModel resultModel = new ResultModel();
        try {

            if (StringUtils.isEmpty(area)) {
                resultModel.set(1, "地区编码不允许为空！", null);
                return resultModel;
            }

            QueryWrapper<StdDistrict> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("CODE_VALUE", area);

            List<StdDistrict> ecpJxtRegions = iStdDistrictService.getBaseMapper()
                    .selectList(queryWrapper);
            resultModel.set(0, "success", ecpJxtRegions);
        } catch (Exception e) {
            log.error("获取信息失败,异常信息{}", e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

    @ApiOperation(value = "通过父级获取父级以下地区")
    @RequestMapping("/getAreaListByParentCode")
    public Object getAreaListByParentCode(String area) {
        ResultModel resultModel = new ResultModel();
        try {

            if (StringUtils.isEmpty(area)) {
                resultModel.set(1, "地区编码不允许为空！", null);
                return resultModel;
            }

            QueryWrapper<StdDistrict> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("PARENT_CODE", area);

            List<StdDistrict> ecpJxtRegions = iStdDistrictService.getBaseMapper()
                    .selectList(queryWrapper);
            resultModel.set(0, "success", ecpJxtRegions);
        } catch (Exception e) {
            log.error("获取信息失败,异常信息{}", e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;

    }


}
