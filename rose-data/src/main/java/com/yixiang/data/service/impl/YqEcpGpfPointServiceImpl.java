package com.yixiang.data.service.impl;

import com.yixiang.data.entity.YntTrade;
import com.yixiang.data.entity.YqEcpGpfPoint;
import com.yixiang.data.mapper.YqEcpGpfPointMapper;
import com.yixiang.data.service.IYqEcpGpfPointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务点表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class YqEcpGpfPointServiceImpl extends ServiceImpl<YqEcpGpfPointMapper, YqEcpGpfPoint> implements IYqEcpGpfPointService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> objectList : listByExcel) {
                YqEcpGpfPoint yqEcpGpfPoint = new YqEcpGpfPoint();
                map.put("hang",listByExcel.indexOf(objectList));
                String servId= String.valueOf(objectList.get(0));
                String svcpnt_nm= String.valueOf(objectList.get(1));

                String svcpnt_adr= String.valueOf(objectList.get(2));
                String user_name= String.valueOf(objectList.get(3));
                String phone= String.valueOf(objectList.get(4));
                String lat= String.valueOf(objectList.get(5));
                String lon= String.valueOf(objectList.get(6));
                String manage_user_name= String.valueOf(objectList.get(7));
                String ccbins_id= String.valueOf(objectList.get(8));
                String ccbins_chn_shrtnm= String.valueOf(objectList.get(9));
                String cardnum= String.valueOf(objectList.get(10));
                String aum= String.valueOf(objectList.get(11));
                String year_num= String.valueOf(objectList.get(12));
                String jr_city= String.valueOf(objectList.get(13));
                String jr_area= String.valueOf(objectList.get(14));
                String jr_street= String.valueOf(objectList.get(15));
                String jr_village= String.valueOf(objectList.get(16));
                String jr_city_name= String.valueOf(objectList.get(17));
                String jr_area_name= String.valueOf(objectList.get(18));
                String jr_street_name= String.valueOf(objectList.get(19));
                String jr_village_name= String.valueOf(objectList.get(20));
                yqEcpGpfPoint.setSvcpntId(servId);
                yqEcpGpfPoint.setSvcpntNm(svcpnt_nm);
                yqEcpGpfPoint.setSvcpntAdr(svcpnt_adr);
                yqEcpGpfPoint.setUserName(user_name);
                yqEcpGpfPoint.setPhone(phone);
                yqEcpGpfPoint.setLat(lat);
                yqEcpGpfPoint.setLon(lon);
                yqEcpGpfPoint.setManageUserName(manage_user_name);
                yqEcpGpfPoint.setCcbinsId(ccbins_id);
                yqEcpGpfPoint.setCcbinsChnShrtnm(ccbins_chn_shrtnm);
                yqEcpGpfPoint.setCardnum(Integer.parseInt(cardnum));
                yqEcpGpfPoint.setAum(aum);
                yqEcpGpfPoint.setYearNum(year_num);
                yqEcpGpfPoint.setJrCity(jr_city);
                yqEcpGpfPoint.setJrArea(jr_area);
                yqEcpGpfPoint.setJrStreet(jr_street);
                yqEcpGpfPoint.setJrVillage(jr_village);
                yqEcpGpfPoint.setJrCityName(jr_city_name);
                yqEcpGpfPoint.setJrAreaName(jr_area_name);
                yqEcpGpfPoint.setJrStreetName(jr_street_name);
                yqEcpGpfPoint.setJrVillageName(jr_village_name);
                yqEcpGpfPoint.setBacthCode(batchCode);
                boolean save = this.saveOrUpdate(yqEcpGpfPoint);
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
