package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixiang.data.entity.StdDistrict;
import com.yixiang.data.entity.YntDetail;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.service.IStdDistrictService;
import com.yixiang.data.service.IYntDistrictPointNumService;
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
import java.util.stream.Collectors;

/**
 * <p>
 * 服务点数据 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/pointNum")
@Api("服务点数页面")
@Log4j2
public class YntDistrictPointNumController {

    @Autowired
    IYntDistrictPointNumService iYntDistrictPointNumService;

    @Autowired
    IStdDistrictService iStdDistrictService;



    /**
     * 根据区县代码获取服务点数
     */
    @ApiOperation(value = "根据行政区号获取下级服务点数")
    @RequestMapping(value = "/getPointlList",method = RequestMethod.GET)
    public Object getPointlList(String area) {
        ResultModel resultModel = new ResultModel();

        try {

            if (StringUtils.isEmpty(area)) {
                area = "130000";
            }
            //查询属于几级机构
            QueryWrapper<StdDistrict> queryWrappers = new QueryWrapper<>();
            queryWrappers.eq("code_value", area);
            StdDistrict one = iStdDistrictService.getOne(queryWrappers);


           /* QueryWrapper<StdDistrict> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_code", area);
            List<StdDistrict> ecpJxtRegions = iStdDistrictService.getBaseMapper().selectList(queryWrapper);
            List<String> collect = ecpJxtRegions.stream().map(r -> r.getCodeValue()).collect(Collectors.toList());*/

            List<YntDistrictPointNum> list = new ArrayList<>();
            QueryWrapper<YntDistrictPointNum> yntDistrictPointNumQueryWrapper = new QueryWrapper<>();
            if ("1".equals(one.getType())){
                list = iYntDistrictPointNumService.selectCity();
            }
            if ("2".equals(one.getType())){
                list = iYntDistrictPointNumService.selectArea(one.getCodeValue());
            }
            if ("3".equals(one.getType())){
                list = iYntDistrictPointNumService.selectVillage(one.getCodeValue());
            }
            //list = iYntDistrictPointNumService.list(yntDistrictPointNumQueryWrapper);
            resultModel.set(0, "success", list);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }


    /**
     * 根据区县代码获取流水列表
     */
    @ApiOperation(value = "根据行政区号获取本地区服务点数")
    @RequestMapping(value = "/getPointHightlList",method = RequestMethod.GET)
    public Object getPointHightlList(String area) {
        ResultModel resultModel = new ResultModel();

        try {

            if (StringUtils.isEmpty(area)) {
                area = "130000";
            }
            QueryWrapper<StdDistrict> queryWrappers = new QueryWrapper<>();
            queryWrappers.eq("code_value", area);
            StdDistrict one = iStdDistrictService.getOne(queryWrappers);
            QueryWrapper<YntDistrictPointNum> yntDistrictPointNumQueryWrapper = new QueryWrapper<>();

            if ("2".equals(one.getType())){
                yntDistrictPointNumQueryWrapper.eq("citycode",area);
            }
            if ("3".equals(one.getType())){
                yntDistrictPointNumQueryWrapper.eq("areacode",area);

            }
            if ("4".equals(one.getType())){
                yntDistrictPointNumQueryWrapper.eq("villagecode",area);
            }
            YntDistrictPointNum one1 = iYntDistrictPointNumService.getOne(yntDistrictPointNumQueryWrapper);

            resultModel.set(0, "success", one1);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }
}
