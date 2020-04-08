package com.yixiang;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.YqEcpGpfPoint;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/stdlogin")
@Api("登录页面")
@Log4j2
public class LoginController {


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object getPointList(String userName, String passWd, HttpServletRequest request) {
        ResultModel resultModel = new ResultModel();
        try {
            Enumeration<String> parameterNames = request.getParameterNames();
            Map map=request.getParameterMap();
            Set keSet=map.entrySet();
            for(Iterator itr = keSet.iterator(); itr.hasNext();){
                Map.Entry me=(Map.Entry)itr.next();
                Object ok=me.getKey();
                Object ov=me.getValue();
                String[] value=new String[1];
                if(ov instanceof String[]){
                    value=(String[])ov;
                }else{
                    value[0]=ov.toString();
                }

                for(int k=0;k<value.length;k++){
                    System.out.println(ok+"="+value[k]);
                }
            }
            System.out.println(userName+"==="+passWd);
        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "获取信息失败", null);
        }
        return resultModel;
    }

}
