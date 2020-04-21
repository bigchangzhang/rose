package com.yixiang.rose.common.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static List<List<Object>> getBankListByExcel(InputStream in, String fileName,int firstRowNum,int firstCellNum,Boolean isdate) throws Exception {
        List<List<Object>> list = new ArrayList();
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
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
                    String cellValueByCell = getCellValueByCell(cell,isdate);
                    li.add(cellValueByCell);
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
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
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
        File file = new File("C:\\Users\\zc\\Desktop\\河北驾驶舱\\20200415数据\\数据4.53\\数据4.5\\河北分行裕农通综合汇总数据模板按市汇总.xlsx");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ExcelUtils excelUtils = new ExcelUtils();
           // List<List<Object>> bankListByExcel = excelUtils.getBankListByExcel(fileInputStream, file.getName(),8,0);
            List<List<Object>> list = new ArrayList();
            //创建Excel工作薄
            Workbook work = getWorkbook(fileInputStream, file.getName());
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

            System.out.println(list);

        }
        catch (Exception e){

        }


    }

    public static String getCellValueByCell(Cell cell,Boolean isdate) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType=cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                short format = cell.getCellStyle().getDataFormat();
                if (false) {
                   /* SimpleDateFormat sdf = null;
                    //System.out.println("cell.getCellStyle().getDataFormat()="+cell.getCellStyle().getDataFormat());
                    if (format == 20 || format == 32) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = org.apache.poi.ss.usermodel.DateUtil
                                .getJavaDate(value);
                        cellValue = sdf.format(date);
                    }else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
                    }
                    try {
                        cellValue = sdf.format(cell.getDateCellValue());// 日期
                    } catch (Exception e) {
                        try {
                            throw new Exception("exception on get date data !".concat(e.toString()));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }finally{
                        sdf = null;
                    }*/
                }else if (format == 164 && isdate){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    cellValue = sdf.format(date);

                } else{
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    cellValue = bd.toPlainString();// 数值 这种用BigDecimal包装再获取plainString，可以防止获取到科学计数值
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue()+"";;
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "ERROR VALUE";
                break;
            default:
                cellValue = "UNKNOW VALUE";
                break;
        }
        return cellValue;
    }

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
    public static List<List<Object>> getBankListByExcel(InputStream in, String fileName,int firstRowNum,int firstCellNum,Boolean islastRow,Boolean isdate,String sheetid) throws Exception {
        List<List<Object>> list = new ArrayList();
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

            sheet = work.getSheetAt(Integer.parseInt(sheetid));
            if (sheet == null) {
                return null;
            }

            //for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            if(islastRow){
                for (int j = firstRowNum; j <= sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row == null || row.getFirstCellNum() == j) {
                        continue;
                    }

                    List<Object> li = new ArrayList<>();
                    //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    for (int y = firstCellNum; y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        String cellValueByCell = getCellValueByCell(cell,isdate);
                        li.add(cellValueByCell);
                    }
                    list.add(li);
                }
            }else {
                for (int j = firstRowNum; j <= sheet.getLastRowNum()-1; j++) {
                    row = sheet.getRow(j);
                    if (row == null || row.getFirstCellNum() == j) {
                        continue;
                    }

                    List<Object> li = new ArrayList<>();
                    //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    for (int y = firstCellNum; y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        String cellValueByCell = getCellValueByCell(cell,isdate);
                        li.add(cellValueByCell);
                    }
                    list.add(li);
                }
            }


        work.close();
        return list;
    }

}
