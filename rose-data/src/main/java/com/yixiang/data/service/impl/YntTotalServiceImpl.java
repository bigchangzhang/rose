package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.data.entity.StdCompositeViewCity;
import com.yixiang.data.entity.StdCompositeViewProvince;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.entity.YntTotal;
import com.yixiang.data.mapper.YntTotalMapper;
import com.yixiang.data.service.IStdCompositeViewCityService;
import com.yixiang.data.service.IStdCompositeViewProvinceService;
import com.yixiang.data.service.IYntTotalService;
import com.yixiang.rose.common.utils.ExcelUtils;
import com.yixiang.rose.common.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务点统计表-左屏 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-04-14
 */
@Service
public class YntTotalServiceImpl extends ServiceImpl<YntTotalMapper, YntTotal> implements IYntTotalService {

    @Autowired
    IStdCompositeViewProvinceService iStdCompositeViewProvinceService;

    @Autowired
    IStdCompositeViewCityService iStdCompositeViewCityService;

    @Override
    public YntDistrictPointNum   selectCity(String citycode) {
        return this.baseMapper.selectCity(citycode);
    }

    @Override
    public YntDistrictPointNum selectArea(String areacode) {
        return this.baseMapper.selectArea(areacode);
    }

    @Override
    public YntDistrictPointNum selectVillage(String villagecode) {
        return this.baseMapper.selectVillage(villagecode);
    }

    @Override
    public YntDistrictPointNum selectProvaince() {
        return this.baseMapper.selectProvaince();
    }

    @Override
    public Map saveExcel(MultipartFile file, String batchCode) throws Exception {
            Boolean back = true;
            Map map = new HashMap<>();
            try {
                map = save141(file, batchCode);

            } catch (Exception e){
                if (map.size() ==0){
                    back = false;
                    map.put("back",back);
                }
            }
            return map;
        }

    private Map save141(MultipartFile file, String batchCode) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            List<List<Object>> list = new ArrayList();
            List<List<Object>> list1 = new ArrayList();
            //创建Excel工作薄
            Workbook work = ExcelUtils.getWorkbook(file.getInputStream(), file.getOriginalFilename());
            if (null == work) {
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;

            sheet = work.getSheetAt(Integer.parseInt("0"));
            if (sheet == null) {
                return null;
            }

            //for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            for (int j = 3; j <= 3; j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                for (int y = 0; y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String cellValueByCell = ExcelUtils.getCellValueByCell(cell, false);
                    li.add(cellValueByCell);
                }
                list.add(li);
            }
            for (int j = 5; j <= sheet.getLastRowNum() - 1; j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li1 = new ArrayList<>();
                //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                for (int y = 0; y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String cellValueByCell = ExcelUtils.getCellValueByCell(cell, false);
                    li1.add(cellValueByCell);
                }
                list1.add(li1);
            }
            work.close();

            for (List<Object> objects : list) {
                map.put("hang",list.indexOf(objects));
                String onestart = String.valueOf(objects.get(0));
                if(!StringUtils.isEmpty(onestart)){
                    back =saveStdCompositeViewProvincereporlis(onestart,"141","一星",batchCode);
                }
                String twostart = String.valueOf(objects.get(1));
                if(!StringUtils.isEmpty(twostart)){
                    back =saveStdCompositeViewProvincereporlis(twostart,"141","二星",batchCode);
                }
                String thrstart = String.valueOf(objects.get(2));
                if(!StringUtils.isEmpty(thrstart)){
                    back =saveStdCompositeViewProvincereporlis(thrstart,"141","三星",batchCode);
                }
            }
            for (List<Object> objectList : list1) {
                List<Object> objects = list.get(0);
                String areaCodes = String.valueOf(objectList.get(1));
                Double znqk_year_num = Double.parseDouble(String.valueOf(objectList.get(2)));
                Double znqk_year_money = Double.parseDouble(decimalFormat.format(String.valueOf(objectList.get(3))));
                Double xjhk_year_num = Double.parseDouble(String.valueOf(objectList.get(4)));
                Double xjhk_year_money = Double.parseDouble(decimalFormat.format(String.valueOf(objectList.get(5))));
                Double dhhz_year_num = Double.parseDouble(String.valueOf(objectList.get(6)));
                Double dhhz_year_money = Double.parseDouble(decimalFormat.format(String.valueOf(objectList.get(7))));
                Double zzhk_year_num = Double.parseDouble(String.valueOf(objectList.get(8)));
                Double zzhk_year_money = Double.parseDouble(decimalFormat.format(String.valueOf(objectList.get(9))));
                String decd_num = String.valueOf(objectList.get(10));
                String decd_year_money = String.valueOf(objectList.get(11));
                decd_year_money = decimalFormat.format(Double.parseDouble(decd_year_money));
                String shjf_num = String.valueOf(objectList.get(12));
                String shjf_year_money = String.valueOf(objectList.get(13));
                shjf_year_money = decimalFormat.format(Double.parseDouble(shjf_year_money));
                String sbjf_num = String.valueOf(objectList.get(14));
                String sbjf_year_money = String.valueOf(objectList.get(15));
                sbjf_year_money = decimalFormat.format(Double.parseDouble(sbjf_year_money));
                String hy_point_num = String.valueOf(objectList.get(16));
                String double_ten_proportion = String.valueOf(objectList.get(17));
                double_ten_proportion = decimalFormat.format(Double.parseDouble(double_ten_proportion));
                String double_fifty_proportion = String.valueOf(objectList.get(18));
                double_fifty_proportion = decimalFormat.format(Double.parseDouble(double_fifty_proportion));
                String double_hundred_proportion = String.valueOf(objectList.get(19));
                double_hundred_proportion = decimalFormat.format(Double.parseDouble(double_hundred_proportion));
                if (!StringUtils.isEmpty(areaCodes)) {
                    QueryWrapper<YntTotal> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("area_code", areaCodes);
                    YntTotal yntTotal = this.getOne(queryWrapper);
                    yntTotal.setDecdNum(decd_num);
                    yntTotal.setDecdYearMoney(decd_year_money);
                    yntTotal.setDhhzYearMoney(dhhz_year_money);
                    yntTotal.setDhhzYearNum(dhhz_year_num);
                    yntTotal.setZnqkYearNum(znqk_year_num);
                    yntTotal.setZnqkYearMoney(znqk_year_money);
                    yntTotal.setXjhkYearNum(xjhk_year_num);
                    yntTotal.setXjhkYearMoney(xjhk_year_money);
                    yntTotal.setZzhkYearNum(zzhk_year_num);
                    yntTotal.setZzhkYearMoney(zzhk_year_money);
                    yntTotal.setShjfNum(shjf_num);
                    yntTotal.setShjfYearMoney(shjf_year_money);
                    yntTotal.setSbjfNum(sbjf_num);
                    yntTotal.setSbjfYearMoney(sbjf_year_money);
                    yntTotal.setHyPointNum(hy_point_num);
                    yntTotal.setDoubleTenProportion(double_ten_proportion);
                    yntTotal.setDoubleFiftyProportion(double_fifty_proportion);
                    yntTotal.setDoubleHundredProportion(double_hundred_proportion);
                    this.updateById(yntTotal);
                }
                Double onestart = Double.parseDouble(String.valueOf(objects.get(0)))* Double.parseDouble(double_ten_proportion);
                back =save141total(String.valueOf(Math.round(onestart)),"141",areaCodes,"一星",batchCode);
                Double twostart = Double.parseDouble(String.valueOf(objects.get(1)))* Double.parseDouble(double_fifty_proportion);
                back =save141total(String.valueOf(Math.round(twostart)),"141",areaCodes,"二星",batchCode);
                Double thrstart = Double.parseDouble(String.valueOf(objects.get(2)))* Double.parseDouble(double_hundred_proportion);
                back =save141total(String.valueOf(Math.round(thrstart)),"141",areaCodes,"三星",batchCode);
                if (back){
                    back = true;
                    map.put("back",back);
                }else {
                    back = false;
                    map.put("back",back);
                }
            }
        }catch (Exception e) {
            if (map.size() ==0){
                back = false;
                map.put("back",back);
            }
        }
        return map;
    }

    private Boolean save141total(String verticalAxisA,String s,String areaCodes, String abscissa_axis, String batchCode) {
        QueryWrapper<StdCompositeViewCity> queryWrapper7 = new QueryWrapper<>();
        queryWrapper7.eq("report_place",s);
        queryWrapper7.eq("abscissa_axis",abscissa_axis);
        queryWrapper7.eq("city_code",areaCodes);
        StdCompositeViewCity stdCompositeViewCity = iStdCompositeViewCityService.getOne(queryWrapper7);
        stdCompositeViewCity.setVerticalAxisA(verticalAxisA);
        stdCompositeViewCity.setBacthCode(batchCode);
        return iStdCompositeViewCityService.updateById(stdCompositeViewCity);
    }

    private Boolean saveStdCompositeViewProvincereporlis(String verticalAxisA,String s, String abscissa_axis, String batchCode) {
        QueryWrapper<StdCompositeViewProvince> queryWrapper8 = new QueryWrapper<>();
        queryWrapper8.eq("report_place",s);
        queryWrapper8.eq("abscissa_axis",abscissa_axis);
        StdCompositeViewProvince compositeViewProvince = iStdCompositeViewProvinceService.getOne(queryWrapper8);
        compositeViewProvince.setVerticalAxisA(verticalAxisA);
        compositeViewProvince.setBatchCode(batchCode);
        return iStdCompositeViewProvinceService.updateById(compositeViewProvince);
    }

}
