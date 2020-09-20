package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdDistrict;
import com.yixiang.data.entity.YntTotal;
import com.yixiang.data.entity.YntTotalNew;
import com.yixiang.data.entity.YqEcpGpfPoint;
import com.yixiang.data.mapper.YntTotalNewMapper;
import com.yixiang.data.service.IStdDistrictService;
import com.yixiang.data.service.IYntTotalNewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.rose.common.utils.ExcelUtils;
import com.yixiang.rose.common.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-08-19
 */
@Service
public class YntTotalNewServiceImpl extends ServiceImpl<YntTotalNewMapper, YntTotalNew> implements IYntTotalNewService {

    @Autowired
    IStdDistrictService iStdDistrictService;



    @Override
    public List<YntTotalNew> getYnt(String areaCode) {

        QueryWrapper<YntTotalNew> yntTotalNewQueryWrapper = new QueryWrapper<>();
        yntTotalNewQueryWrapper.eq("p_code",areaCode);
        List<YntTotalNew> list = this.list(yntTotalNewQueryWrapper);
        return list;
    }


    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> objectList : listByExcel) {
                YntTotalNew yntTotalNew = new YntTotalNew();
                map.put("hang",listByExcel.indexOf(objectList));
                String areaName= String.valueOf(objectList.get(0));
                String areaCode= String.valueOf(objectList.get(1));
                String dyydkh= String.valueOf(objectList.get(2));
                String dyydye= String.valueOf(objectList.get(3));
                String nhkdkh= String.valueOf(objectList.get(4));
                String nhkdye= String.valueOf(objectList.get(5));
                String xwkdkh= String.valueOf(objectList.get(6));
                String xwkdye= String.valueOf(objectList.get(7));
                String jtjjh= String.valueOf(objectList.get(8));
                String jtjjxz= String.valueOf(objectList.get(9));
                String pCode= String.valueOf(objectList.get(10));
                yntTotalNew.setAreaName(areaName);
                yntTotalNew.setAreaCode(areaCode);
                yntTotalNew.setDyydkh(dyydkh);
                yntTotalNew.setDyydye(dyydye);
                yntTotalNew.setNhkdkh(nhkdkh);
                yntTotalNew.setNhkdye(nhkdye);
                yntTotalNew.setXwkdkh(xwkdkh);
                yntTotalNew.setXwkdye(xwkdye);
                yntTotalNew.setJtjjh(jtjjh);
                yntTotalNew.setJtjjxz(jtjjxz);
                yntTotalNew.setBacthCode(batchCode);
                yntTotalNew.setPCode(pCode);
                boolean save = this.saveOrUpdate(yntTotalNew);
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

    @Override
    public Map saveExcelCity(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> objectList : listByExcel) {
                YntTotalNew yntTotalNew = new YntTotalNew();
                map.put("hang",listByExcel.indexOf(objectList));
                String areaCode= String.valueOf(objectList.get(0));
                String areaName= String.valueOf(objectList.get(1));
                String dyydkh= String.valueOf(objectList.get(2));
                String dyydye= String.valueOf(objectList.get(3));
                String nhkdkh= String.valueOf(objectList.get(4));
                String nhkdye= String.valueOf(objectList.get(5));
                String xwkdkh= String.valueOf(objectList.get(6));
                String xwkdye= String.valueOf(objectList.get(7));
                String jtjjh= String.valueOf(objectList.get(8));
                String jtjjxz= String.valueOf(objectList.get(9));
                //String pCode= String.valueOf(objectList.get(10));
                QueryWrapper<StdDistrict> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("CODE_VALUE", areaCode);
                StdDistrict districtServiceOne = iStdDistrictService.getOne(queryWrapper);
                yntTotalNew.setAreaName(areaName);
                yntTotalNew.setAreaCode(areaCode);
                yntTotalNew.setDyydkh(dyydkh);
                yntTotalNew.setDyydye(dyydye);
                yntTotalNew.setNhkdkh(nhkdkh);
                yntTotalNew.setNhkdye(nhkdye);
                yntTotalNew.setXwkdkh(xwkdkh);
                yntTotalNew.setXwkdye(xwkdye);
                yntTotalNew.setJtjjh(jtjjh);
                yntTotalNew.setJtjjxz(jtjjxz);
                yntTotalNew.setBacthCode(batchCode);
                yntTotalNew.setPCode(districtServiceOne.getParentCode());
                boolean save = this.saveOrUpdate(yntTotalNew);
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
