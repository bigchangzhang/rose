package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdDistrict;
import com.yixiang.data.entity.StdNotmchAll;
import com.yixiang.data.service.IStdNotmchAllService;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 不动服务点数量 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-11-03
 */
@RestController
@RequestMapping("/stdNotmchAll")
@Log4j2
public class StdNotmchAllController {


    @Autowired
    IStdNotmchAllService iStdNotmchAllService;

    @ApiOperation(value = "获取对应机构下的不动户数量")
    @RequestMapping(value = "/getNotmchAllList",method = RequestMethod.GET)
    public Object getNotmchAllList(String orgCode) {
        ResultModel resultModel = new ResultModel();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (!StringUtils.isEmpty(orgCode)) {
                QueryWrapper<StdNotmchAll> queryWrapper = new QueryWrapper<>();
               queryWrapper.eq("org_code",orgCode);
               StdNotmchAll stdNotmchAll = iStdNotmchAllService.getOne(queryWrapper);
               Integer onecount = Integer.parseInt(stdNotmchAll.getOnemCount());
               Integer threecount = Integer.parseInt(stdNotmchAll.getThreemCount());
               Integer sixcount = Integer.parseInt(stdNotmchAll.getSixmCount());
               Integer oneycount = Integer.parseInt(stdNotmchAll.getOneyearCount());
               Integer count = onecount+threecount+sixcount+oneycount;
                DecimalFormat decimalFormat = new DecimalFormat("0.000");
                String ozb = decimalFormat.format((float) onecount / count);
                String tzb = decimalFormat.format((float) threecount / count);
                String szb = decimalFormat.format((float) sixcount / count);
                String oyzb = decimalFormat.format((float) oneycount / count);
                resultMap.put("count",count);
                resultMap.put("onecount",onecount);
                resultMap.put("ozb",ozb);
                resultMap.put("threecount",threecount);
                resultMap.put("tzb",tzb);
                resultMap.put("sixcount",sixcount);
                resultMap.put("szb",szb);
                resultMap.put("oneycount",oneycount);
                resultMap.put("oyzb",oyzb);
            }else {
                List<StdNotmchAll> list = iStdNotmchAllService.list();
                Integer count = 0;
                Integer onecount = 0;
                Integer threecount = 0;
                Integer sixcount = 0;
                Integer oneycount = 0;
                for (StdNotmchAll stdNotmchAll : list) {
                     onecount += Integer.parseInt(stdNotmchAll.getOnemCount());
                     threecount += Integer.parseInt(stdNotmchAll.getThreemCount());
                     sixcount += Integer.parseInt(stdNotmchAll.getSixmCount());
                     oneycount += Integer.parseInt(stdNotmchAll.getOneyearCount());
                }
                count += onecount+threecount+sixcount+oneycount;
                DecimalFormat decimalFormat = new DecimalFormat("0.000");
                String ozb = decimalFormat.format((float) onecount / count);
                String tzb = decimalFormat.format((float) threecount / count);
                String szb = decimalFormat.format((float) sixcount / count);
                String oyzb = decimalFormat.format((float) oneycount / count);
                resultMap.put("count",count);
                resultMap.put("onecount",onecount);
                resultMap.put("ozb",ozb);
                resultMap.put("threecount",threecount);
                resultMap.put("tzb",tzb);
                resultMap.put("sixcount",sixcount);
                resultMap.put("szb",szb);
                resultMap.put("oneycount",oneycount);
                resultMap.put("oyzb",oyzb);
            }
            resultModel.set(0, "success", resultMap);
        } catch (Exception e) {
            log.error("获取信息失败,异常信息{}", e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;

    }

}
