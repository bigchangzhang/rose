package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.data.entity.StdNotmchAll;
import com.yixiang.data.entity.YntTotalPt;
import com.yixiang.data.mapper.YntTotalPtMapper;
import com.yixiang.data.service.IYntTotalPtService;
import com.yixiang.rose.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-11-18
 */
@Service
public class YntTotalPtServiceImpl extends ServiceImpl<YntTotalPtMapper, YntTotalPt> implements IYntTotalPtService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        try {
            for (List<Object> objectList : listByExcel) {
                YntTotalPt yntTotalPt = new YntTotalPt();
                map.put("hang",listByExcel.indexOf(objectList));
                String areaName= String.valueOf(objectList.get(0));
                String areaCode= String.valueOf(objectList.get(1));
                if (StringUtils.isEmpty(areaCode)){
                    continue;
                }
                String dpsbs= String.valueOf(objectList.get(2));
                String kjl= String.valueOf(objectList.get(3));
                kjl = decimalFormat.format(Double.parseDouble(kjl));
                String yhzcs= String.valueOf(objectList.get(4));
                String ktqbs= String.valueOf(objectList.get(5));
                String dpfwl= String.valueOf(objectList.get(6));
                String appfwl= String.valueOf(objectList.get(7));
                String ptjfje= String.valueOf(objectList.get(8));
                ptjfje = decimalFormat.format(Double.parseDouble(ptjfje));
                String ptjfbs= String.valueOf(objectList.get(9));
                String ptmmje= String.valueOf(objectList.get(10));
                ptmmje = decimalFormat.format(Double.parseDouble(ptmmje));
                String ptmmbs= String.valueOf(objectList.get(11));
                String ptchfbbs= String.valueOf(objectList.get(12));
                String yntbye= String.valueOf(objectList.get(13));
                yntbye = decimalFormat.format(Double.parseDouble(yntbye));
                String qtjjye= String.valueOf(objectList.get(14));
                qtjjye = decimalFormat.format(Double.parseDouble(qtjjye));
                yntTotalPt.setAreaCode(areaCode);
                yntTotalPt.setAreaName(areaName);
                yntTotalPt.setDpsbs(dpsbs);
                yntTotalPt.setKjl(kjl);
                yntTotalPt.setYhzcs(yhzcs);
                yntTotalPt.setKtqbs(ktqbs);
                yntTotalPt.setDpfwl(dpfwl);
                yntTotalPt.setAppfwl(appfwl);
                yntTotalPt.setPtjfje(ptjfje);
                yntTotalPt.setPtjfbs(ptjfbs);
                yntTotalPt.setPtmmje(ptmmje);
                yntTotalPt.setPtmmbs(ptmmbs);
                yntTotalPt.setPtchfbbs(ptchfbbs);
                yntTotalPt.setYntbye(yntbye);
                yntTotalPt.setQtjjye(qtjjye);
                yntTotalPt.setBacthCode(batchCode);
                boolean save = this.saveOrUpdate(yntTotalPt);
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
