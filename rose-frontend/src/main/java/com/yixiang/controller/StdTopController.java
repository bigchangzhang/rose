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
            queryWrapper.orderByAsc("rank");
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
        ResultModel resultModel = new ResultModel();
        try {
            QueryWrapper<StdTop> queryWrapper = new QueryWrapper();
            queryWrapper.orderByAsc("rank");
            queryWrapper.eq("area_code",code);
            List<StdTop> riskWarns = iStdTopService.list(queryWrapper);
            resultModel.set(0, "success", riskWarns);
        }catch (Exception e){
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }


}
