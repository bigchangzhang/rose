package com.yixiang.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdCompositeViewCity;
import com.yixiang.data.entity.StdCompositeViewProvince;
import com.yixiang.data.entity.YntDetail;
import com.yixiang.data.entity.YntTotal;
import com.yixiang.data.service.IStdCompositeViewCityService;
import com.yixiang.data.service.IStdCompositeViewProvinceService;
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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    @Autowired
    IYntTotalService iYntTotalService;

    @ApiOperation(value = "获取地区")
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public ResultModel query(String reportPlace, String provinceCode){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(3);
        nf.setRoundingMode(RoundingMode.UP);
        nf.setGroupingUsed(false);
        ResultModel resultModel = new ResultModel();
        if (StringUtils.isBlank(reportPlace)) {
            return new ResultModel();
        }
        QueryWrapper qr = new QueryWrapper();
        qr.eq("report_place", reportPlace);
        List<StdCompositeViewProvince> stdCompositeViewProvinceList = new ArrayList<>();
        if (StringUtils.isBlank(provinceCode) || "130000".equals(provinceCode)){
            if (reportPlace.equals("211")){

                QueryWrapper<YntTotal> yntTotalQueryWrapper = new QueryWrapper<>();
                yntTotalQueryWrapper.eq("area_code",provinceCode);
                List<YntTotal> list = iYntTotalService.list(yntTotalQueryWrapper);
                for (YntTotal yntTotal : list) {
                    Double zzhkYearNum = yntTotal.getZzhkYearNum();
                    Double znqkYearNum = yntTotal.getZnqkYearNum();
                    Double dhhzYearNum = yntTotal.getDhhzYearNum();
                    Double xjhkYearNum = yntTotal.getXjhkYearNum();
                    Double decdNum = Double.parseDouble(yntTotal.getDecdNum());
                    Double dkNum = Double.parseDouble(yntTotal.getDkNum());
                    Double sbjfNum = Double.parseDouble(yntTotal.getSbjfNum());
                    Double shjfNum = Double.parseDouble(yntTotal.getShjfNum());
                    Double sumTotle = znqkYearNum+zzhkYearNum+dhhzYearNum+xjhkYearNum+decdNum+dkNum+sbjfNum+shjfNum;
                    BigDecimal b=new BigDecimal(znqkYearNum / sumTotle);
                    String hyPointProportion = String.valueOf(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
                    StdCompositeViewProvince stdCompositeViewProvince = setStdCompositeViewProvincemethod("助农取款", String.valueOf(znqkYearNum), hyPointProportion);
                    String hyPointProportion1 = nf.format(xjhkYearNum/sumTotle);
                    StdCompositeViewProvince stdCompositeViewProvince1 = setStdCompositeViewProvincemethod("现金汇款", String.valueOf(xjhkYearNum), hyPointProportion1);
                    String hyPointProportion2 =  nf.format(dhhzYearNum/sumTotle);
                    StdCompositeViewProvince stdCompositeViewProvince2 = setStdCompositeViewProvincemethod("定活互转", String.valueOf(dhhzYearNum), hyPointProportion2);
                    String hyPointProportion3 =  nf.format(decdNum/sumTotle);
                    StdCompositeViewProvince stdCompositeViewProvince3 = setStdCompositeViewProvincemethod("大额存单", String.valueOf(decdNum), hyPointProportion3);
                    String hyPointProportion4 = nf.format(zzhkYearNum/sumTotle);
                    StdCompositeViewProvince stdCompositeViewProvince4 = setStdCompositeViewProvincemethod("转账汇款", String.valueOf(zzhkYearNum), hyPointProportion4);
                    String hyPointProportion5 = nf.format(dkNum/sumTotle);
                    StdCompositeViewProvince stdCompositeViewProvince5 = setStdCompositeViewProvincemethod("贷款", String.valueOf(dkNum), hyPointProportion5);
                    String hyPointProportion6 = nf.format(shjfNum/sumTotle);
                    StdCompositeViewProvince stdCompositeViewProvince6 = setStdCompositeViewProvincemethod("生活缴费", String.valueOf(shjfNum), hyPointProportion6);
                    String hyPointProportion7 = nf.format(sbjfNum/sumTotle);
                    StdCompositeViewProvince stdCompositeViewProvince7 = setStdCompositeViewProvincemethod("社保缴费", String.valueOf(sbjfNum), hyPointProportion7);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince1);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince2);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince3);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince4);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince5);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince6);
                    stdCompositeViewProvinceList.add(stdCompositeViewProvince7);
                }

            }else {
               stdCompositeViewProvinceList = iStdCompositeViewProvinceService.list(qr);
            }

            Map<String, List<StdCompositeViewProvince>> collect = stdCompositeViewProvinceList.stream().collect(Collectors.groupingBy(s -> s.getReportPlace()));
            resultModel.set(0, "success", collect);
        }else {
            QueryWrapper qee = new QueryWrapper();
            qee.eq("report_place",reportPlace);
            qee.eq("city_code",provinceCode);
            List<StdCompositeViewCity> stdCompositeViewCityList = new ArrayList<>();
            if (reportPlace.equals("211")){
                QueryWrapper<YntTotal> yntTotalQueryWrapper = new QueryWrapper<>();
                yntTotalQueryWrapper.eq("area_code",provinceCode);
                List<YntTotal> list = iYntTotalService.list(yntTotalQueryWrapper);
                for (YntTotal yntTotal : list) {
                    Double zzhkYearNum = yntTotal.getZzhkYearNum();
                    Double znqkYearNum = yntTotal.getZnqkYearNum();
                    Double dhhzYearNum = yntTotal.getDhhzYearNum();
                    Double xjhkYearNum = yntTotal.getXjhkYearNum();
                    Double decdNum = Double.parseDouble(yntTotal.getDecdNum());
                    Double dkNum = Double.parseDouble(yntTotal.getDkNum());
                    Double sbjfNum = Double.parseDouble(yntTotal.getSbjfNum());
                    Double shjfNum = Double.parseDouble(yntTotal.getShjfNum());
                    Double sumTotle = znqkYearNum+zzhkYearNum+dhhzYearNum+xjhkYearNum+decdNum+dkNum+sbjfNum+shjfNum;
                    String hyPointProportion = nf.format(znqkYearNum / sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince = setStdCompositeViewCityMethd("助农取款", String.valueOf(znqkYearNum), hyPointProportion,provinceCode);
                    String hyPointProportion1 = nf.format(xjhkYearNum/sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince1 = setStdCompositeViewCityMethd("现金汇款", String.valueOf(xjhkYearNum), hyPointProportion1,provinceCode);
                    String hyPointProportion2 =  nf.format(dhhzYearNum/sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince2 = setStdCompositeViewCityMethd("定活互转", String.valueOf(dhhzYearNum), hyPointProportion2,provinceCode);
                    String hyPointProportion3 = nf.format(decdNum/sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince3 = setStdCompositeViewCityMethd("大额存单", String.valueOf(decdNum), hyPointProportion3,provinceCode);
                    String hyPointProportion4 = nf.format(zzhkYearNum/sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince4 = setStdCompositeViewCityMethd("转账汇款", String.valueOf(zzhkYearNum), hyPointProportion4,provinceCode);
                    String hyPointProportion5 = nf.format(dkNum/sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince5 = setStdCompositeViewCityMethd("贷款", String.valueOf(dkNum), hyPointProportion5,provinceCode);
                    String hyPointProportion6 = nf.format(shjfNum/sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince6 = setStdCompositeViewCityMethd("生活缴费", String.valueOf(shjfNum), hyPointProportion6,provinceCode);
                    String hyPointProportion7 = nf.format(sbjfNum/sumTotle);
                    StdCompositeViewCity stdCompositeViewProvince7 = setStdCompositeViewCityMethd("社保缴费", String.valueOf(sbjfNum), hyPointProportion7,provinceCode);
                    stdCompositeViewCityList.add(stdCompositeViewProvince);
                    stdCompositeViewCityList.add(stdCompositeViewProvince1);
                    stdCompositeViewCityList.add(stdCompositeViewProvince2);
                    stdCompositeViewCityList.add(stdCompositeViewProvince3);
                    stdCompositeViewCityList.add(stdCompositeViewProvince4);
                    stdCompositeViewCityList.add(stdCompositeViewProvince5);
                    stdCompositeViewCityList.add(stdCompositeViewProvince6);
                    stdCompositeViewCityList.add(stdCompositeViewProvince7);
                }

            }else {
                stdCompositeViewCityList = iStdCompositeViewCityService.list(qee);
            }

            Map<String, List<StdCompositeViewCity>> collect = stdCompositeViewCityList.stream().collect(Collectors.groupingBy(s -> s.getReportPlace()));
            resultModel.set(0, "success", collect);
        }
        return resultModel;
    }


    private StdCompositeViewProvince setStdCompositeViewProvincemethod(String abscissaAxis,String verticalAxisA,String verticalAxisB){
        StdCompositeViewProvince stdCompositeViewProvince = new StdCompositeViewProvince();
        stdCompositeViewProvince.setReportPlace("211");
        stdCompositeViewProvince.setAbscissaAxis(abscissaAxis);
        stdCompositeViewProvince.setVerticalAxisA(verticalAxisA);
        stdCompositeViewProvince.setVerticalAxisB(verticalAxisB);
        return stdCompositeViewProvince;
    }

    private StdCompositeViewCity setStdCompositeViewCityMethd(String abscissaAxis,String verticalAxisA,String verticalAxisB,String areaCode){
        StdCompositeViewCity stdCompositeViewCity = new StdCompositeViewCity();
        stdCompositeViewCity.setReportPlace("211");
        stdCompositeViewCity.setAbscissaAxis(abscissaAxis);
        stdCompositeViewCity.setVerticalAxisA(verticalAxisA);
        stdCompositeViewCity.setVerticalAxisB(verticalAxisB);
        stdCompositeViewCity.setCityCode(areaCode);
        return stdCompositeViewCity;
    }


}
