package com.yixiang.controller;



import com.yixiang.data.entity.StdImmBt;
import com.yixiang.data.service.IStdImmBtService;
import com.yixiang.rose.common.utils.ResultModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 蓝色按钮表 前端控制器
 * </p>
 *
 * @author zc
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/stdImmBt")
@Log4j2
public class StdImmBtController {

    @Autowired
    IStdImmBtService iStdImmBtService;

    @RequestMapping(value = "/getBtList",method = RequestMethod.GET)
    public Object getBtList() {
        ResultModel resultModel = new ResultModel();
        try {
            List<StdImmBt> btList = iStdImmBtService.list();
            Map<String, List<StdImmBt>> listMap = btList.stream().collect(Collectors.groupingBy(b -> b.getBtType()));
/*

            StdImmBt jRstdImmBt = new StdImmBt();
            StdImmBt bMstdImmBt = new StdImmBt();
            StdImmBt zWstdImmBt = new StdImmBt();
            StdImmBt jZstdImmBt = new StdImmBt();
            List<StdImmBt> objects = new ArrayList<>();
            objects.add(jRstdImmBt);
            objects.add(bMstdImmBt);
            objects.add(zWstdImmBt);

            objects.add(jZstdImmBt);


            for (StdImmBt stdImmBt : btList) {
                if ("金融服务".equals(stdImmBt.getBtType())){
                    putSomeValue(jRstdImmBt, stdImmBt);
                    jRstdImmBt.setBtType("金融服务");
                }
                if ("政务服务".equals(stdImmBt.getBtType())){
                    putSomeValue(zWstdImmBt, stdImmBt);
                    zWstdImmBt.setBtType("政务服务");
                }
                if ("便民服务".equals(stdImmBt.getBtType())){
                    putSomeValue(bMstdImmBt, stdImmBt);
                    bMstdImmBt.setBtType("便民服务");
                }

                if ("精准扶贫".equals(stdImmBt.getBtType())){
                    putSomeValue(jZstdImmBt, stdImmBt);
                    jZstdImmBt.setBtType("精准扶贫");
                }

            }*/
            resultModel.set(0, "success", listMap);
        }catch (Exception e){
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

    private void putSomeValue(StdImmBt jZstdImmBt, StdImmBt stdImmBt) {
        Map<String, String> obtMap = new HashMap<>();
        obtMap.put("txt",stdImmBt.getBtNm());
        obtMap.put("dialogTxt",stdImmBt.getBtContent());
        obtMap.put("id",stdImmBt.getColumn2());
        obtMap.put("type",stdImmBt.getColumn1());
        jZstdImmBt.getBtmapList().add(obtMap);
    }
}
