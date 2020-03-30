package com.yixiang.rose.common.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zbc
 * @Date: 2019/10/26 17:48
 */
public class ExcelUtils {



    /**
     * 处理上传的文件
     *
     * @param in 输入流
     * @param fileName 文件名称
     * @param firstRowNum 从第几行读取数据
     * @param firstCellNum 从第几列读取数据
     * @return
     * @throws Exception
     * https://www.cnblogs.com/zhenghengbin/p/9490511.html
     */
    public List<List<Object>> getBankListByExcel(InputStream in, String fileName,int firstRowNum,int firstCellNum) throws Exception {
        List<List<Object>> list = new ArrayList();
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            //for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            for (int j = firstRowNum; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                for (int y = firstCellNum; y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }


    public static void main(String args[]){
        File file = new File("C:\\Users\\zbc\\Desktop\\山东\\2服务点交易统计月报201908.xlsx");
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            ExcelUtils excelUtils = new ExcelUtils();
            List<List<Object>> bankListByExcel = excelUtils.getBankListByExcel(fileInputStream, file.getName(),8,0);
            for (int i = 0; i < bankListByExcel.size(); i++) {
                List<Object> lo = bankListByExcel.get(i);
                //TODO 随意发挥
                System.out.println(lo);

            }
        }
        catch (Exception e){

        }


    }
}
