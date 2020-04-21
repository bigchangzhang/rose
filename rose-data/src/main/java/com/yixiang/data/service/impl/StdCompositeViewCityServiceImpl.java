package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdCompositeViewCity;
import com.yixiang.data.entity.StdCompositeViewProvince;
import com.yixiang.data.mapper.StdCompositeViewCityMapper;
import com.yixiang.data.service.IStdCompositeViewCityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.rose.common.utils.ExcelUtils;
import com.yixiang.rose.common.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 市视图表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class StdCompositeViewCityServiceImpl extends ServiceImpl<StdCompositeViewCityMapper, StdCompositeViewCity> implements IStdCompositeViewCityService {

    @Override
    public Map saveExcel(MultipartFile file, String batchCode) throws Exception {

        Boolean back = true;
        Map map = new HashMap<>();
        try {
            map = save431(file, batchCode);
            map = saveOther(file, batchCode);
            map = saveOtherTwo(file, batchCode);


        } catch (Exception e){
            if (map.size() ==0){
                back = false;
                map.put("back",back);
            }
        }
        return map;
    }

    private Map saveOtherTwo(MultipartFile file, String batchCode) {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            List<List<Object>> list = new ArrayList();
            //创建Excel工作薄
            Workbook work = ExcelUtils.getWorkbook(file.getInputStream(), file.getOriginalFilename());
            if (null == work) {
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;

            sheet = work.getSheetAt(Integer.parseInt("1"));
            if (sheet == null) {
                return null;
            }

            //for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            for (int j = 4; j <= sheet.getLastRowNum()-1; j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                for (int y = 0; y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String cellValueByCell = ExcelUtils.getCellValueByCell(cell,false);
                    li.add(cellValueByCell);
                }
                list.add(li);
            }


            work.close();

            for (List<Object> objectList : list) {
                String[] s = String.valueOf(objectList.get(0)).split("_");
                String areaCodes = "13"+String.valueOf(objectList.get(0)).split("_")[1] + "00";
                String abscissa_axis = String.valueOf(objectList.get(1));
                String erticalAxisA = String.valueOf(objectList.get(2));
                String report_place = s[0];
                if (!StringUtils.isEmpty(abscissa_axis)) {
                    map.put("hang",list.indexOf(objectList));
                    QueryWrapper<StdCompositeViewCity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("report_place", report_place);
                    queryWrapper.eq("abscissa_axis", abscissa_axis);
                    queryWrapper.eq("city_code", areaCodes);
                    StdCompositeViewCity stdCompositeViewCity = this.getOne(queryWrapper);
                    stdCompositeViewCity.setVerticalAxisA(erticalAxisA);
                    stdCompositeViewCity.setBacthCode(batchCode);
                    back = this.updateById(stdCompositeViewCity);
                }

            }
            if (back){
                back = true;
                map.put("back",back);
            }else {
                back = false;
                map.put("back",back);
            }
        }
        catch (Exception e){
            if (map.size() ==0){
                back = false;
                map.put("back",back);
            }

        }
        return map;
    }

    private Map saveOther(MultipartFile file, String batchCode) {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            List<List<Object>> list = new ArrayList();
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
            for (int j = 16; j <= sheet.getLastRowNum()-1; j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                for (int y = 0; y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String cellValueByCell = ExcelUtils.getCellValueByCell(cell,false);
                    li.add(cellValueByCell);
                }
                list.add(li);
            }


            work.close();

            for (List<Object> objectList : list) {
                String[] s = String.valueOf(objectList.get(0)).split("_");
                String areaCodes = "13"+String.valueOf(objectList.get(0)).split("_")[1] + "00";
                String abscissa_axis = String.valueOf(objectList.get(1));
                String erticalAxisA = String.valueOf(objectList.get(2));
                String report_place = s[0];
                if (!StringUtils.isEmpty(abscissa_axis)) {
                    map.put("hang",list.indexOf(objectList));
                    QueryWrapper<StdCompositeViewCity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("report_place", report_place);
                    queryWrapper.eq("abscissa_axis", abscissa_axis);
                    queryWrapper.eq("city_code", areaCodes);
                    StdCompositeViewCity stdCompositeViewCity = this.getOne(queryWrapper);
                    stdCompositeViewCity.setVerticalAxisA(erticalAxisA);
                    stdCompositeViewCity.setBacthCode(batchCode);
                    back = this.updateById(stdCompositeViewCity);
                }

            }
            if (back){
                back = true;
                map.put("back",back);
            }else {
                back = false;
                map.put("back",back);
            }
        }
        catch (Exception e){
            if (map.size() ==0){
                back = false;
                map.put("back",back);
            }

        }
        return map;
    }

    private Map save431(MultipartFile file, String batchCode) {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            List<List<Object>> list = new ArrayList();
            //创建Excel工作薄
            Workbook work = ExcelUtils.getWorkbook(file.getInputStream(), file.getOriginalFilename());
            if (null == work) {
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;

            sheet = work.getSheetAt(0);

            //for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            for (int j = 3; j <= 14; j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                for (int y = 0; y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
            work.close();

            for (List<Object> objectList : list) {
                map.put("hang",list.indexOf(objectList));
                String areaCodes = String.valueOf(objectList.get(0))+"00";

                String phoneBank = String.valueOf(objectList.get(1));
                if(!StringUtils.isEmpty(phoneBank)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"手机银行",batchCode);
                }
                String lzf= String.valueOf(objectList.get(2));
                if(!StringUtils.isEmpty(lzf)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"龙支付",batchCode);

                }
                String xyk = String.valueOf(objectList.get(3));
                if(!StringUtils.isEmpty(xyk)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"信用卡",batchCode);
                }
                String dk = String.valueOf(objectList.get(4));
                if(!StringUtils.isEmpty(dk)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"贷款",batchCode);
                }
                String lc = String.valueOf(objectList.get(5));
                if(!StringUtils.isEmpty(lc)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"理财",batchCode);
                }
                String jj = String.valueOf(objectList.get(6));
                if(!StringUtils.isEmpty(jj)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"基金",batchCode);
                }
                String gjs = String.valueOf(objectList.get(7));
                if(!StringUtils.isEmpty(gjs)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"贵金属",batchCode);
                }
                String bx = String.valueOf(objectList.get(8));
                if(!StringUtils.isEmpty(bx)){
                    back =saveStdCompositeViewProvincereporlis(phoneBank,"431",areaCodes,"保险",batchCode);
                }

                String ywhjyqmkhgxsl = String.valueOf(objectList.get(9));
                if(!StringUtils.isEmpty(ywhjyqmkhgxsl)){
                    back =saveStdCompositeViewProvincereporlis(ywhjyqmkhgxsl,"611",areaCodes,"与我行具有全面客户关系数量",batchCode);
                }

                String ljkhsl = String.valueOf(objectList.get(10));
                if(!StringUtils.isEmpty(ljkhsl)){
                    back =saveStdCompositeViewProvincereporlis(ljkhsl,"611",areaCodes,"临界客户数量",batchCode);
                }

                String jcsjqyyh = String.valueOf(objectList.get(11));
                if(!StringUtils.isEmpty(jcsjqyyh)){
                    back =saveStdCompositeViewProvincereporlis(jcsjqyyh,"611",areaCodes,"仅差手机银行签约",batchCode);
                }

                String jcaum = String.valueOf(objectList.get(12));
                if(!StringUtils.isEmpty(jcaum)){
                    back =saveStdCompositeViewProvincereporlis(jcaum,"611",areaCodes,"仅差AUM",batchCode);
                }

                String ccxlc = String.valueOf(objectList.get(13));
                if(!StringUtils.isEmpty(ccxlc)){
                    back =saveStdCompositeViewProvincereporlis(ccxlc,"611",areaCodes,"差储蓄理财",batchCode);

                }
                String ccxdk = String.valueOf(objectList.get(14));
                if(!StringUtils.isEmpty(ccxdk)){
                    back =saveStdCompositeViewProvincereporlis(ccxdk,"611",areaCodes,"差储蓄贷款",batchCode);
                }

                String ccxxyk = String.valueOf(objectList.get(15));
                if(!StringUtils.isEmpty(ccxxyk)){
                    back =saveStdCompositeViewProvincereporlis(ccxxyk,"611",areaCodes,"差储蓄信用卡",batchCode);
                    ;
                }

                String clcdk = String.valueOf(objectList.get(16));
                if(!StringUtils.isEmpty(clcdk)){
                    back =saveStdCompositeViewProvincereporlis(clcdk,"611",areaCodes,"差理财贷款",batchCode);
                }

                String clcxyk = String.valueOf(objectList.get(17));
                if(!StringUtils.isEmpty(clcxyk)){
                    back =saveStdCompositeViewProvincereporlis(clcxyk,"611",areaCodes,"差理财信用卡",batchCode);
                }
                String cdkxyk = String.valueOf(objectList.get(18));
                if(!StringUtils.isEmpty(cdkxyk)){
                    back =saveStdCompositeViewProvincereporlis(cdkxyk,"611",areaCodes,"差贷款信用卡",batchCode);

                }
            }
            if (back){
                back = true;
                map.put("back",back);
            }else {
                back = false;
                map.put("back",back);
            }
        }
        catch (Exception e){
            if (map.size() ==0){
                back = false;
                map.put("back",back);
            }

        }
        return map;
    }

    private Boolean saveStdCompositeViewProvincereporlis(String verticalAxisA,String s,String areaCodes, String abscissa_axis, String batchCode) {
        QueryWrapper<StdCompositeViewCity> queryWrapper7 = new QueryWrapper<>();
        queryWrapper7.eq("report_place",s);
        queryWrapper7.eq("abscissa_axis",abscissa_axis);
        queryWrapper7.eq("city_code",areaCodes);
        StdCompositeViewCity stdCompositeViewCity = this.getOne(queryWrapper7);
        stdCompositeViewCity.setVerticalAxisA(verticalAxisA);
        stdCompositeViewCity.setBacthCode(batchCode);
        return this.updateById(stdCompositeViewCity);
    }
}
