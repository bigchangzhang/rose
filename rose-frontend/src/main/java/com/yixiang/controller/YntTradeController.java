package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.YntTrade;
import com.yixiang.data.service.IYntTradeService;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 合作视图信息 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/yntTrade")
@Api("合作视图页面")
@Log4j2
public class YntTradeController {

    @Autowired
    IYntTradeService iYntTradeService;


    @ApiOperation(value = "获取一个地区的合作视图统计信息")
    @RequestMapping("/getAreaByCode")
    public Object getAreaByCode(String area) {
        ResultModel resultModel = new ResultModel();
        try {

            if (StringUtils.isEmpty(area)) {
                area = "370000";
            }

            QueryWrapper<YntTrade> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("area", area);
            queryWrapper.orderByDesc("trade_type");

            List<YntTrade> yntTrades = iYntTradeService.getBaseMapper()
                    .selectList(queryWrapper);

            resultModel.set(0, "success", yntTrades);
        } catch (Exception e) {
            log.error("获取信息失败,异常信息{}", e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }



}
