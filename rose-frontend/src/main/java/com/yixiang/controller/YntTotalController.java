package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdDistrict;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.entity.YntTotal;
import com.yixiang.data.entity.YntTotalNew;
import com.yixiang.data.service.IStdDistrictService;
import com.yixiang.data.service.IYntDistrictPointNumService;
import com.yixiang.data.service.IYntTotalNewService;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

    @Autowired
    IYntDistrictPointNumService iYntDistrictPointNumService;

    @Autowired
    IYntTotalNewService iYntTotalNewService;


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
                area="130000";
                queryWrapper.eq("area_code", "130000");
            }
            //查询属于几级机构
            QueryWrapper<StdDistrict> queryWrappers = new QueryWrapper<>();
            queryWrappers.eq("code_value", area);
            StdDistrict one = iStdDistrictService.getOne(queryWrappers);
            YntDistrictPointNum list = new YntDistrictPointNum();

            QueryWrapper<YntDistrictPointNum> yntDistrictPointNumQueryWrapper = new QueryWrapper<>();
            if ("2".equals(one.getType())){
                list = iYntTotalService.selectCity(one.getCodeValue());
            }
            if ("3".equals(one.getType())){
                list = iYntTotalService.selectArea(one.getCodeValue());
            }
            if ("4".equals(one.getType())){
                list = iYntTotalService.selectVillage(one.getCodeValue());
            }
            if ("1".equals(one.getType())){
                list = iYntTotalService.selectProvaince();
            }
            double hyPoint = Double.parseDouble(list.getHyPointNum());
            double pointNum = Double.parseDouble(list.getPointNum());
            DecimalFormat df = new DecimalFormat("0.00");
            String hyPointProportion = df.format(hyPoint/pointNum);


            List<YntTotal> listToal = iYntTotalService.list(queryWrapper);
            for (YntTotal yntTotal : listToal) {
                String s = String.valueOf(new BigDecimal(Double.parseDouble(hyPointProportion) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                yntTotal.setHyPointProportion(s);
                String round1 = String.valueOf(new BigDecimal(Double.parseDouble(yntTotal.getDoubleFiftyProportion())*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                yntTotal.setDoubleFiftyProportion(round1);
                String round2 = String.valueOf(new BigDecimal(Double.parseDouble(yntTotal.getDoubleTenProportion())*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                yntTotal.setDoubleTenProportion(round2);
                String round3 = String.valueOf(new BigDecimal(Double.parseDouble(yntTotal.getDoubleHundredProportion())*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                yntTotal.setDoubleHundredProportion(round3);


            }
            resultModel.set(0, "success", listToal);
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
            queryWrapperstd.eq("PARENT_CODE","130000");
            List<StdDistrict> stdDistricts = iStdDistrictService.list(queryWrapperstd);
            if(stdDistricts.isEmpty()){
                resultModel.set(1, "获取父级信息为空，当前地区{}",null);
                return resultModel;
            }
            List<String> collect = stdDistricts.stream().map(s -> s.getCodeValue()).collect(Collectors.toList());



            QueryWrapper<YntTotal> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("area_code",collect);
            //1助农贷款、2现金汇款、3定活互转、4转账汇款、5大额存单、6贷款、7生活缴费、8社保缴费
            //TODO
            if("1".equals(pointIde)){
                queryWrapper.orderByDesc("znqk_year_num");
            }
            if("2".equals(pointIde)){
                queryWrapper.orderByDesc("xjhk_year_num");
            }
            if("3".equals(pointIde)){
                queryWrapper.orderByDesc("dhhz_year_num");
            }
            if("4".equals(pointIde)){
                queryWrapper.orderByDesc("zzhk_year_num");
            }
            if("5".equals(pointIde)){
                queryWrapper.orderByDesc("decd_num");
            }
            if("6".equals(pointIde)){
                queryWrapper.orderByDesc("dk_num");
            }
            if("7".equals(pointIde)){
                queryWrapper.orderByDesc("shjf_num");
            }
            if("8".equals(pointIde)){
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

    /**
     * 根据地区代码获取左屏业务信息，area不传查询等于省370000，
     * workType业务类型（//1助农贷款、2现金汇款、3定活互转、4转账汇款、5大额存单、6贷款、7生活缴费、8社保缴费）
     * size查询数量
     */
    @ApiOperation(value = "point")
    @RequestMapping(value = "/getGroupLeftInfoByType",method = RequestMethod.GET)
    public Object getGroupLeftInfoByType(String workType) {
        ResultModel resultModel = new ResultModel();
        try {
            String area = "130000";
            if (StringUtils.isEmpty(workType)) {
                resultModel.set(1, "数据类型不允许为空！", null);
                return resultModel;
            }
            QueryWrapper<StdDistrict> areaQuery = new QueryWrapper<>();
            areaQuery.eq("PARENT_CODE",area);
            List<StdDistrict> areaList = iStdDistrictService.list(areaQuery);
            if(areaList.isEmpty()){
                resultModel.set(1, "获取父级信息为空，当前地区{}", area);
                return resultModel;
            }
            List<String> areaCodes = areaList.stream().map(r -> r.getCodeValue())
                    .collect(Collectors.toList());
            if("6".equals(workType)){
                QueryWrapper<YntTotalNew> queryWrapper = new QueryWrapper<>();
                queryWrapper.in("area_code",areaCodes);
                queryWrapper.orderByDesc("xwkdye");
                List<YntTotalNew> list = iYntTotalNewService.list(queryWrapper);
                List<List<YntTotalNew>> yntTotalNews = new ArrayList<>();
                int toIndex = 4;
                for (int i = 0; i < list.size(); i+=4) {
                    if (i+4>list.size()) {
                        toIndex = list.size() - i;
                    }
                    List<YntTotalNew> newList = list.subList(i, i+toIndex);
                    yntTotalNews.add(newList);
                }
                resultModel.set(0, "success", yntTotalNews);
            }else if("18".equals(workType)){
                QueryWrapper<YntTotalNew> queryWrapper = new QueryWrapper<>();
                queryWrapper.in("area_code",areaCodes);
                queryWrapper.orderByDesc("jtjjh");
                List<YntTotalNew> list = iYntTotalNewService.list(queryWrapper);
                List<List<YntTotalNew>> yntTotalNews = new ArrayList<>();
                int toIndex = 4;
                for (int i = 0; i < list.size(); i+=4) {
                    if (i+4>list.size()) {
                        toIndex = list.size() - i;
                    }
                    List<YntTotalNew> newList = list.subList(i, i+toIndex);
                    yntTotalNews.add(newList);
                }
                resultModel.set(0, "success", yntTotalNews);
            } else{
                QueryWrapper<YntTotal> queryWrapper = new QueryWrapper<>();
                queryWrapper.in("area_code",areaCodes);
                //1助农贷款、2现金汇款、3转账汇款、4定活互转、5裕农快贷、6社保卡、7etc、8药品追溯
                if("1".equals(workType)){
                    queryWrapper.orderByDesc("znqk_year_num");
                }
                if("2".equals(workType)){
                    queryWrapper.orderByDesc("xjhk_year_num");
                }
                if("3".equals(workType)){
                    queryWrapper.orderByDesc("dhhz_year_num");
                }
                if("4".equals(workType)){
                    queryWrapper.orderByDesc("zzhk_year_num");
                }
                if("5".equals(workType)){
                    queryWrapper.orderByDesc("decd_num");
                }
                if("6".equals(workType)){
                    queryWrapper.orderByDesc("dk_num");
                }
                if("7".equals(workType)){
                    queryWrapper.orderByDesc("shjf_num");
                }
                if("8".equals(workType)){
                    queryWrapper.orderByDesc("sbjf_num");
                }
                //queryWrapper.last("limit " + size);
                List<YntTotal> list = iYntTotalService.list(queryWrapper);
                List<List<YntTotal>> yntTotals = new ArrayList<>();
                int toIndex = 4;
                for (int i = 0; i < list.size(); i+=4) {
                    if (i+4>list.size()) {
                        toIndex = list.size() - i;
                    }
                    List<YntTotal> newList = list.subList(i, i+toIndex);
                    yntTotals.add(newList);
                }
                resultModel.set(0, "success", yntTotals);
            }

        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

}
