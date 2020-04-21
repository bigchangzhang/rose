package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yixiang.data.entity.StdCompositeViewCity;
import com.yixiang.data.entity.StdTop;
import com.yixiang.data.mapper.StdTopMapper;
import com.yixiang.data.service.IStdTopService;
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
 * Top50表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-24
 */
@Service
public class StdTopServiceImpl extends ServiceImpl<StdTopMapper, StdTop> implements IStdTopService {

    @Override
    public Map saveExcel(MultipartFile file, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            map = saveSheng(file, batchCode);
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
            for (int j = 53; j <= sheet.getLastRowNum()-1; j++) {
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
                map.put("hang",list.indexOf(objectList));
                String svcpnt_id = String.valueOf(objectList.get(0));
                String svcpnt_nm = String.valueOf(objectList.get(1));
                String qyk_num = String.valueOf(objectList.get(2));
                String sdck_money = String.valueOf(objectList.get(3));
                String trs_num = String.valueOf(objectList.get(4));
                String rankid = String.valueOf(objectList.get(5));
                String area_code = String.valueOf(objectList.get(7));
                String area_name = String.valueOf(objectList.get(6));
                StdTop stdTop = new StdTop();
                stdTop.setSvcpntId(svcpnt_id);
                stdTop.setSvcpntNm(svcpnt_nm);
                stdTop.setQykNum(qyk_num);
                stdTop.setSdckMoney(sdck_money);
                stdTop.setTrsNum(trs_num);
                stdTop.setRankid(Long.parseLong(rankid));
                stdTop.setAreaCode(area_code);
                stdTop.setAreaName(area_name);
                stdTop.setBacthCode(batchCode);
                back = this.save(stdTop);

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

    private Map saveSheng(MultipartFile file, String batchCode) {
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
            for (int j = 2; j <= 51; j++) {
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
                map.put("hang",list.indexOf(objectList));
                String area_code ="130000";
                String svcpnt_id = String.valueOf(objectList.get(0));
                String svcpnt_nm = String.valueOf(objectList.get(1));
                String qyk_num = String.valueOf(objectList.get(2));
                String sdck_money = String.valueOf(objectList.get(3));
                String trs_num = String.valueOf(objectList.get(4));
                String rankid = String.valueOf(objectList.get(5));
                StdTop stdTop = new StdTop();
                stdTop.setSvcpntId(svcpnt_id);
                stdTop.setSvcpntNm(svcpnt_nm);
                stdTop.setQykNum(qyk_num);
                stdTop.setSdckMoney(sdck_money);
                stdTop.setTrsNum(trs_num);
                stdTop.setRankid(Long.parseLong(rankid));
                stdTop.setAreaCode(area_code);
                stdTop.setAreaName("河北省");
                stdTop.setBacthCode(batchCode);
                back = this.save(stdTop);

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
}
