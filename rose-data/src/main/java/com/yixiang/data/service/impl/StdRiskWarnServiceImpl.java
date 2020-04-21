package com.yixiang.data.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.yixiang.data.entity.StdRiskWarn;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.mapper.StdRiskWarnMapper;
import com.yixiang.data.service.IStdRiskWarnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.rose.common.utils.ExcelUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 风险预警表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class StdRiskWarnServiceImpl extends ServiceImpl<StdRiskWarnMapper, StdRiskWarn> implements IStdRiskWarnService {

    @Override
    public Boolean saveExcel(List<List<Object>> listByExcel,String batchCode) throws Exception {
        List<StdRiskWarn> stdRiskWarns = new ArrayList<>();
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> list : listByExcel) {
                StdRiskWarn stdRiskWarn = new StdRiskWarn();
                map.put("hang",listByExcel.indexOf(list));
                String svcpnt_id = String.valueOf(list.get(0));
                String svcpnt_nm = String.valueOf(list.get(1));
                String trs_date= String.valueOf(list.get(2));
                String trs_num = String.valueOf(list.get(3));
                String tisk_type = String.valueOf(list.get(4));
                if(StringUtils.isEmpty(svcpnt_nm)){
                    continue;
                }
                stdRiskWarn.setBatchCode(batchCode);
                stdRiskWarn.setSvcpntId(svcpnt_id);
                stdRiskWarn.setSvcpntNm(svcpnt_nm);
                stdRiskWarn.setTrsDate(trs_date);
                stdRiskWarn.setTrsNum(trs_num);
                stdRiskWarn.setTiskType(tisk_type);
                boolean b = this.save(stdRiskWarn);
                if (b){
                    back = true;
                }else {
                    back = false;
                    break;
                }
            }
        }catch (Exception e){
            if (map.size() ==0){
                back = false;
            }
        }
        return back;
    }
}
