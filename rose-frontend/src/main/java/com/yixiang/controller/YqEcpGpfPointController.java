package com.yixiang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.YqEcpGpfPoint;
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

import java.util.List;

/**
 * <p>
 * 服务点表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/point")
@Api("服务点信息")
@Log4j2
public class YqEcpGpfPointController {

    @Autowired
    IYqEcpGpfPointService iYqEcpGpfPointService;


    /**
     * 根据区县代码获取服务点列表
     */
    @ApiOperation(value = "根据乡镇获取下面所有的服务店")
    @RequestMapping(value = "/getPointList",method = RequestMethod.GET)
    public Object getPointList(String area) {
        ResultModel resultModel = new ResultModel();
        try {

            if (StringUtils.isEmpty(area)) {
                resultModel.set(1, "地区不允许为空！", null);
                return resultModel;
            }
            QueryWrapper<YqEcpGpfPoint> yqEcpGpfPointQueryWrapper = new QueryWrapper<>();

            yqEcpGpfPointQueryWrapper.eq("jr_street", area);
            List<YqEcpGpfPoint> list = iYqEcpGpfPointService.list(yqEcpGpfPointQueryWrapper);
            resultModel.set(0, "success", list);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }


    /**
     * 根据服务点编号获取详细信息
     */
    @ApiOperation(value = "服务点详细信息")
    @RequestMapping(value = "/getPointAllInfoByCode",method = RequestMethod.GET)
    public Object getPointAllInfoByCode(String pointCode) {
        ResultModel resultModel = new ResultModel();
        try {

            /*if (StringUtils.isEmpty(areaType)) {
                resultModel.set(1, "数据类型不允许为空！", null);
                return resultModel;
            }*/

            if (StringUtils.isEmpty(pointCode)) {
                resultModel.set(1, "服务编号不允许为空！", null);
                return resultModel;
            }


            //查询服务点信息
            QueryWrapper<YqEcpGpfPoint> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("svcpnt_id",pointCode);
            YqEcpGpfPoint yqEcpGpfPoints = iYqEcpGpfPointService.getOne(queryWrapper);


            resultModel.set(0, "success", yqEcpGpfPoints);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

}
