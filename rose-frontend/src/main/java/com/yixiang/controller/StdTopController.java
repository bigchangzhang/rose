package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdTop;
import com.yixiang.data.service.IStdTopService;
import com.yixiang.rose.common.utils.ResultModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

/**
 * <p>
 * Top50表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/stdTop")
@Log4j2
public class StdTopController {

    @Autowired
    IStdTopService iStdTopService;

    @RequestMapping(value = "/getTopList",method = RequestMethod.GET)
    public Object getToplList() {
        ResultModel resultModel = new ResultModel();
        try {
            QueryWrapper<StdTop> queryWrapper = new QueryWrapper();
            queryWrapper.orderByAsc("rankid");
            List<StdTop> riskWarns = iStdTopService.list(queryWrapper);
            resultModel.set(0, "success", riskWarns);
        }catch (Exception e){
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

    @RequestMapping(value = "/getTopListByCode",method = RequestMethod.GET)
    public Object getTopListByCode(String code) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(3);
        nf.setRoundingMode(RoundingMode.UP);
        nf.setGroupingUsed(false);
        ResultModel resultModel = new ResultModel();
        try {
            QueryWrapper<StdTop> queryWrapper = new QueryWrapper();
            queryWrapper.orderByAsc("rankid");
            queryWrapper.eq("area_code",code);
            List<StdTop> stdTops = iStdTopService.list(queryWrapper);
            for (StdTop stdTop : stdTops) {
                String sdckMoney = stdTop.getSdckMoney();
                String sdckMoneys = nf.format(Double.parseDouble(sdckMoney)/1000000);
                stdTop.setSdckMoney(sdckMoneys);
            }
            List<StdTop> stdTops1 = stdTops.subList(0, 49);

            resultModel.set(0, "success", stdTops1);
        }catch (Exception e){
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

}
