package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.UploadLog;
import com.yixiang.data.entity.YntTrade;
import com.yixiang.data.service.IUploadLogService;
import com.yixiang.data.service.IYntTradeService;
import com.yixiang.data.service.IYqEcpGpfPointService;
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
import java.text.NumberFormat;
import java.util.ArrayList;
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

    @Autowired
    IUploadLogService iUploadLogService;

    @Autowired
    IYqEcpGpfPointService iYqEcpGpfPointService;


    @ApiOperation(value = "获取一个地区的合作视图统计信息")
    @RequestMapping(value = "/getAreaByCode",method = RequestMethod.GET)
    public Object getAreaByCode(String area) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.UP);
        nf.setGroupingUsed(false);
        ResultModel resultModel = new ResultModel();
        try {

            if (StringUtils.isEmpty(area)) {
                area = "130000";
            }

            int count = iYqEcpGpfPointService.count();

            QueryWrapper<UploadLog> uploadLogQueryWrapper = new QueryWrapper<>();
                uploadLogQueryWrapper.eq("upload_moudel","yntTrade");
                uploadLogQueryWrapper.eq("upload_status","S");
                uploadLogQueryWrapper.orderByDesc("bacth_code");


                List<UploadLog> list = iUploadLogService.list(uploadLogQueryWrapper);
                UploadLog uploadLog = list.get(0);

                QueryWrapper<YntTrade> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("area", area);
                queryWrapper.orderByDesc("count");
                queryWrapper.eq("bacth_code", uploadLog.getBacthCode());

                List<YntTrade> yntTrades = new ArrayList<>();
                if ("130000".equals(area)){
                     yntTrades = iYntTradeService.orderBYcountList(area);
                    for (YntTrade yntTrade : yntTrades) {
                        Double count1 = Double.parseDouble(yntTrade.getCount());
                        String s = nf.format((count1 / count)*100);
                        yntTrade.setMix(s);
                    }
                }else {
                     yntTrades = iYntTradeService.orderBYcountList(area);
                    for (YntTrade yntTrade : yntTrades) {
                        String s =  nf.format(Double.parseDouble(yntTrade.getMix())*100);
                        yntTrade.setMix(s);

                    }
                }


                if (yntTrades.size() >8){
                    List<YntTrade> yntTrades1 = yntTrades.subList(0, 8);
                    resultModel.set(0, "success", yntTrades1);
                }else {
                    resultModel.set(0, "success", yntTrades);
                }


        } catch (Exception e) {
            log.error("获取信息失败,异常信息{}", e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }



}
