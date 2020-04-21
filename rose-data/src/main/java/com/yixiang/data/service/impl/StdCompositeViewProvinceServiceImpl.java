package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.StdCompositeViewCity;
import com.yixiang.data.entity.StdCompositeViewProvince;
import com.yixiang.data.entity.YntDetail;
import com.yixiang.data.mapper.StdCompositeViewProvinceMapper;
import com.yixiang.data.service.IStdCompositeViewProvinceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.rose.common.utils.ExcelUtils;
import com.yixiang.rose.common.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 省视图表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class StdCompositeViewProvinceServiceImpl extends ServiceImpl<StdCompositeViewProvinceMapper, StdCompositeViewProvince> implements IStdCompositeViewProvinceService {

    @Override
    public Map saveExcel(MultipartFile file, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
             map = save431(file, batchCode);
            map = saveOther(file, batchCode);

        } catch (Exception e){
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
                for (int j = 5; j <= sheet.getLastRowNum()-1; j++) {
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
                String report_place = String.valueOf(objectList.get(0));
                String abscissa_axis = String.valueOf(objectList.get(1));
                String erticalAxisA = String.valueOf(objectList.get(2));
                if (!StringUtils.isEmpty(abscissa_axis)) {
                    map.put("hang",list.indexOf(objectList));
                    QueryWrapper<StdCompositeViewProvince> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("report_place", report_place);
                    queryWrapper.eq("abscissa_axis", abscissa_axis);
                    StdCompositeViewProvince stdCompositeViewProvince = this.getOne(queryWrapper);
                    stdCompositeViewProvince.setVerticalAxisA(erticalAxisA);
                    stdCompositeViewProvince.setBatchCode(batchCode);
                    back = this.updateById(stdCompositeViewProvince);
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
            for (int j = 3; j <= 3; j++) {
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
                String phoneBank = String.valueOf(objectList.get(0));
                if(!StringUtils.isEmpty(phoneBank)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("report_place","431");
                    queryWrapper.eq("abscissa_axis","手机银行");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper);
                    compositeViewProvince.setVerticalAxisA(phoneBank);
                    compositeViewProvince.setBatchCode(batchCode);
                    back = this.updateById(compositeViewProvince);
                }
                String lzf= String.valueOf(objectList.get(1));
                if(!StringUtils.isEmpty(lzf)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("report_place","431");
                    queryWrapper1.eq("abscissa_axis","龙支付");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper1);
                    compositeViewProvince.setVerticalAxisA(lzf);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }
                String xyk = String.valueOf(objectList.get(2));
                if(!StringUtils.isEmpty(xyk)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper2 = new QueryWrapper<>();
                    queryWrapper2.eq("report_place","431");
                    queryWrapper2.eq("abscissa_axis","信用卡");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper2);
                    compositeViewProvince.setVerticalAxisA(xyk);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }
                String dk = String.valueOf(objectList.get(3));
                if(!StringUtils.isEmpty(dk)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper3 = new QueryWrapper<>();
                    queryWrapper3.eq("report_place","431");
                    queryWrapper3.eq("abscissa_axis","贷款");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper3);
                    compositeViewProvince.setVerticalAxisA(dk);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }
                String lc = String.valueOf(objectList.get(4));
                if(!StringUtils.isEmpty(lc)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper4 = new QueryWrapper<>();
                    queryWrapper4.eq("report_place","431");
                    queryWrapper4.eq("abscissa_axis","理财");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper4);
                    compositeViewProvince.setVerticalAxisA(lc);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }
                String jj = String.valueOf(objectList.get(5));
                if(!StringUtils.isEmpty(jj)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper5 = new QueryWrapper<>();
                    queryWrapper5.eq("report_place","431");
                    queryWrapper5.eq("abscissa_axis","基金");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper5);
                    compositeViewProvince.setVerticalAxisA(jj);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }
                String gjs = String.valueOf(objectList.get(6));
                if(!StringUtils.isEmpty(gjs)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper6 = new QueryWrapper<>();
                    queryWrapper6.eq("report_place","431");
                    queryWrapper6.eq("abscissa_axis","贵金属");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper6);
                    compositeViewProvince.setVerticalAxisA(gjs);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }
                String bx = String.valueOf(objectList.get(7));
                if(!StringUtils.isEmpty(bx)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper7 = new QueryWrapper<>();
                    queryWrapper7.eq("report_place","431");
                    queryWrapper7.eq("abscissa_axis","保险");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper7);
                    compositeViewProvince.setVerticalAxisA(bx);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }

                String ywhjyqm = String.valueOf(objectList.get(8));
                if(!StringUtils.isEmpty(ywhjyqm)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper8 = new QueryWrapper<>();
                    queryWrapper8.eq("report_place","611");
                    queryWrapper8.eq("abscissa_axis","与我行具有全面客户关系数量");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper8);
                    compositeViewProvince.setVerticalAxisA(ywhjyqm);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }

                String ljkhsl = String.valueOf(objectList.get(9));
                if(!StringUtils.isEmpty(ljkhsl)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper8 = new QueryWrapper<>();
                    queryWrapper8.eq("report_place","611");
                    queryWrapper8.eq("abscissa_axis","临界客户数量");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper8);
                    compositeViewProvince.setVerticalAxisA(ljkhsl);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }

                String jcsjqyyh = String.valueOf(objectList.get(10));
                if(!StringUtils.isEmpty(jcsjqyyh)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper9 = new QueryWrapper<>();
                    queryWrapper9.eq("report_place","611");
                    queryWrapper9.eq("abscissa_axis","仅差手机银行签约");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper9);
                    compositeViewProvince.setVerticalAxisA(jcsjqyyh);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }

                String jcaum = String.valueOf(objectList.get(11));
                if(!StringUtils.isEmpty(jcaum)){
                    QueryWrapper<StdCompositeViewProvince> queryWrapper10 = new QueryWrapper<>();
                    queryWrapper10.eq("report_place","611");
                    queryWrapper10.eq("abscissa_axis","仅差AUM");
                    StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper10);
                    compositeViewProvince.setVerticalAxisA(jcaum);
                    compositeViewProvince.setBatchCode(batchCode);
                    back =this.updateById(compositeViewProvince);
                }

                String ccxlc = String.valueOf(objectList.get(12));
                if(!StringUtils.isEmpty(ccxlc)){
                    back =saveStdCompositeViewProvincereporlis(ccxlc,"611","差储蓄理财",batchCode);

                }
                String ccxdk = String.valueOf(objectList.get(13));
                if(!StringUtils.isEmpty(ccxdk)){
                    back =saveStdCompositeViewProvincereporlis(ccxdk,"611","差储蓄贷款",batchCode);
                }

                String ccxxyk = String.valueOf(objectList.get(14));
                if(!StringUtils.isEmpty(ccxxyk)){
                    back =saveStdCompositeViewProvincereporlis(ccxxyk,"611","差储蓄信用卡",batchCode);
;
                }

                String clcdk = String.valueOf(objectList.get(7));
                if(!StringUtils.isEmpty(clcdk)){
                    back =saveStdCompositeViewProvincereporlis(clcdk,"611","差理财贷款",batchCode);
                }

                String clcxyk = String.valueOf(objectList.get(7));
                if(!StringUtils.isEmpty(clcxyk)){
                    back =saveStdCompositeViewProvincereporlis(clcxyk,"611","差理财信用卡",batchCode);
                }
                String cdkxyk = String.valueOf(objectList.get(7));
                if(!StringUtils.isEmpty(cdkxyk)){
                    back =saveStdCompositeViewProvincereporlis(cdkxyk,"611","差贷款信用卡",batchCode);

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

    private Boolean saveStdCompositeViewProvincereporlis(String verticalAxisA,String s, String abscissa_axis, String batchCode) {
        QueryWrapper<StdCompositeViewProvince> queryWrapper7 = new QueryWrapper<>();
        queryWrapper7.eq("report_place",s);
        queryWrapper7.eq("abscissa_axis",abscissa_axis);
        StdCompositeViewProvince compositeViewProvince = this.getOne(queryWrapper7);
        compositeViewProvince.setVerticalAxisA(verticalAxisA);
        compositeViewProvince.setBatchCode(batchCode);
        return this.updateById(compositeViewProvince);
    }


}
