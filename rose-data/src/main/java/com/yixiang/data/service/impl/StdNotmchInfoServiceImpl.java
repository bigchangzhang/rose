package com.yixiang.data.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yixiang.data.entity.StdDistrict;
import com.yixiang.data.entity.StdNotmchInfo;
import com.yixiang.data.entity.YntTotalNew;
import com.yixiang.data.mapper.StdNotmchInfoMapper;
import com.yixiang.data.service.IStdNotmchInfoService;
import com.yixiang.rose.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 不动服务点明细 服务实现类
 * </p>
 *
 * @author 肉鸡子
 * @since 2020-11-03
 */
@Service
public class StdNotmchInfoServiceImpl extends ServiceImpl<StdNotmchInfoMapper, StdNotmchInfo> implements IStdNotmchInfoService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> objectList : listByExcel) {
                StdNotmchInfo stdNotmchInfo = new StdNotmchInfo();
                map.put("hang",listByExcel.indexOf(objectList));
                String areaName= String.valueOf(objectList.get(0));
                String areaCode= String.valueOf(objectList.get(1));
                String mchCode= String.valueOf(objectList.get(2));
                if (StringUtils.isEmpty(mchCode)){
                    continue;
                }
                String mchName= String.valueOf(objectList.get(3));
                String notmch_status= String.valueOf(objectList.get(4));

                stdNotmchInfo.setOrgCode(areaCode);
                stdNotmchInfo.setOrgName(areaName);
                stdNotmchInfo.setMchCode(mchCode);
                stdNotmchInfo.setMchName(mchName);
                stdNotmchInfo.setNotmchStatus(notmch_status);

                boolean save = this.saveOrUpdate(stdNotmchInfo);
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
