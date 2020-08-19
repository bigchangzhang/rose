package com.yixiang.data.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.entity.YntTermCheck;
import com.yixiang.data.mapper.YntTermCheckMapper;
import com.yixiang.data.service.IYntTermCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 巡检表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class YntTermCheckServiceImpl extends ServiceImpl<YntTermCheckMapper, YntTermCheck> implements IYntTermCheckService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> list : listByExcel) {
               YntTermCheck yntTermCheck = new YntTermCheck();
                map.put("hang",listByExcel.indexOf(list));
                String cityName = String.valueOf(list.get(0));
                String mch_name = String.valueOf(list.get(1));
                String mch_id= String.valueOf(list.get(2));
                String jtm_change_date = String.valueOf(list.get(4));
                String check_type_name = String.valueOf(list.get(5));
                String check_result = String.valueOf(list.get(6));
                yntTermCheck.setBacthCode(batchCode);
                yntTermCheck.setCheckResult(check_result);
                yntTermCheck.setCheckTypeName(check_type_name);
                yntTermCheck.setCityName(cityName);
                yntTermCheck.setMchName(mch_name);
                yntTermCheck.setMchId(mch_id);
                yntTermCheck.setJtmChangeDate(jtm_change_date);
                boolean b = this.saveOrUpdate(yntTermCheck);
                if (b){
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
