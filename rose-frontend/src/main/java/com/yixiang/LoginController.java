package com.yixiang;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.Userlogin;
import com.yixiang.data.entity.YqEcpGpfPoint;
import com.yixiang.data.service.IUserloginService;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/stdlogin")
@Api("登录页面")
@Log4j2
public class LoginController {

    @Autowired
    IUserloginService iUserloginService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object getPointList(@RequestParam HashMap<String, String> map, HttpServletRequest request) {
        ResultModel resultModel = new ResultModel();
        try {
            if ("0".equals(map.get("isRemanber"))){
                List<Userlogin> list = iUserloginService.list();
                Userlogin userlogin = list.get(0);
                resultModel.set(0, "登录成功", userlogin);
            }else {
                QueryWrapper<Userlogin> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("username",map.get("username"));
                Userlogin one = iUserloginService.getOne(queryWrapper);
                if (null == one){
                    resultModel.set(1, "登录失败用户名不存在", null);
                }else if (!one.getPasswd().equals(map.get("passwd"))){
                    resultModel.set(1, "登录失败密码错误", null);
                }else {
                    request.getSession().setAttribute("user",one);
                    resultModel.set(0, "登录成功", null);
                }
            }


        } catch (Exception e) {
            log.error("获取信息失败:{}",e);
            resultModel.set(1, "登录失败系统错误", null);
        }
        return resultModel;
    }

}
