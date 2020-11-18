package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yixiang.data.entity.StdNotmchAll;
import com.yixiang.data.entity.StdNotmchInfo;
import com.yixiang.data.mapper.StdNotmchAllMapper;
import com.yixiang.data.service.IStdNotmchAllService;
import com.yixiang.rose.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 不动服务点数量 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-11-03
 */
@Service
public class StdNotmchAllServiceImpl extends ServiceImpl<StdNotmchAllMapper, StdNotmchAll> implements IStdNotmchAllService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> objectList : listByExcel) {
                StdNotmchAll stdNotmchAll = new StdNotmchAll();
                map.put("hang",listByExcel.indexOf(objectList));
                String areaName= String.valueOf(objectList.get(0));
                String areaCode= String.valueOf(objectList.get(1));
                if (StringUtils.isEmpty(areaCode)){
                    continue;
                }
                String onemCount= String.valueOf(objectList.get(2));
                String threemCount= String.valueOf(objectList.get(3));
                String sixmCount= String.valueOf(objectList.get(4));
                String oneyearCount= String.valueOf(objectList.get(5));
                stdNotmchAll.setOrgCode(areaCode);
                stdNotmchAll.setOrgName(areaName);
                stdNotmchAll.setOnemCount(onemCount);
                stdNotmchAll.setThreemCount(threemCount);
                stdNotmchAll.setSixmCount(sixmCount);
                stdNotmchAll.setOneyearCount(oneyearCount);
                stdNotmchAll.setBatchCode(batchCode);

                boolean save = this.saveOrUpdate(stdNotmchAll);
                if (save){
                    back = true;
                    map.put("back",back);
                }else {
                    back = false;
                    map.put("back",back);
                    break;
                }
            }
        } catch (Exception e){
            if (map.size() ==0){
                back = false;
                map.put("back",back);
            }
        }
        return map;
    }
}
