package com.yixiang.rose.common.utils;

import io.swagger.models.auth.In;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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
        File file = new File("D:\\某一年度到位经费.xlsx");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            List<List<Object>> list = new ArrayList();
            //创建Excel工作薄
            Workbook work = getWorkbook(fileInputStream, file.getName());
            if (null == work) {
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;

            sheet = work.getSheetAt(1);

            List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();

            //for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            //第一列循环
            List<Map> hebing = new ArrayList<>();
            for (int j = 1; j <= sheet.getLastRowNum(); j=j) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();

                //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                for (int y = 0; y < 1; y++) {
                    cell = row.getCell(y);
                    Map mergedRegion = new HashMap();
                    mergedRegion=   isMergedRegion(sheet, j, y);
                    if(mergedRegion.size() !=0){
                        j+=Integer.parseInt(String.valueOf(mergedRegion.get("mergeSizeh")));
                    }else {
                        j++;
                    }

                    li.add(cell);
                    mergedRegion.put("bumen",cell);
                    hebing.add(mergedRegion);

                }
                System.out.println(hebing);
                list.add(li);
            }
            //第二列循环
            List<Map> hebinger = new ArrayList<>();
            for (Map map : hebing) {
               Integer firstRow = Integer.parseInt(String.valueOf(map.get("firstRow"))) ;
               Integer lastRow = Integer.parseInt(String.valueOf(map.get("lastRow"))) ;
               String bumen = String.valueOf(map.get("bumen"));
                for (int j = firstRow; j <= lastRow; j=j) {
                    row = sheet.getRow(j);
                    if (row == null || row.getFirstCellNum() == j) {
                        continue;
                    }
                    //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    for (int y = 1; y < 2; y++) {
                        cell = row.getCell(y);
                        Map mergedRegionss = new HashMap();
                        mergedRegionss=   isMergedRegion(sheet, j, y);
                        if(mergedRegionss.size() !=0){
                            j+=Integer.parseInt(String.valueOf(mergedRegionss.get("mergeSizeh")));
                        }else {
                            j++;
                        }
                        mergedRegionss.put("riqi",cell);
                        mergedRegionss.put("bumen",bumen);
                        hebinger.add(mergedRegionss);

                    }
                    System.out.println(hebinger);
                }

            }
            //第三四列循环
            List<Map> hebingsan = new ArrayList<>();
            for (Map map : hebingsan) {
                Integer firstRow = Integer.parseInt(String.valueOf(map.get("firstRow"))) ;
                Integer lastRow = Integer.parseInt(String.valueOf(map.get("lastRow"))) ;
                String bumen = String.valueOf(map.get("bumen"));
                String riqi = String.valueOf(map.get("riqi"));
                for (int j = firstRow; j <= lastRow; j=j) {
                    row = sheet.getRow(j);
                    if (row == null || row.getFirstCellNum() == j) {
                        continue;
                    }
                    //for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    for (int y = 2; y < 4; y++) {
                        cell = row.getCell(y);
                        Map mergedRegionsss = new HashMap();
                        mergedRegionsss.put("bumen",bumen);
                        mergedRegionsss.put("riqi",riqi);
                        //mergedRegionsss=   isMergedRegion(sheet, j, y);
//                        if(mergedRegionsss.size() !=0){
//                            j+=Integer.parseInt(String.valueOf(mergedRegionsss.get("mergeSizeh")));
//                        }else {
//                            j++;
//                        }
                        if (y ==2){
                            if (null != cell.getStringCellValue() && "汇总".equals(cell.getStringCellValue())){
                                continue;
                            }else {
                                mergedRegionsss.put("zhanghao",cell);
                            }

                        }
                        if (y==3){
                            mergedRegionsss.put("ziji",cell);
                        }
                        hebingsan.add(mergedRegionsss);

                    }
                    System.out.println(hebingsan.size());
                }

            }








            work.close();
        }
        catch (Exception e){

        }


    }


    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static Map isMergedRegion(Sheet sheet , int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();
        Map map = new HashMap();
        int mergeSizel = 1;
        int mergeSizeh = 1;
        for(int i = 0 ; i < sheetMergeCount ; i++ ){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    //获取合并的行数
                    mergeSizel = ca.getLastColumn() - ca.getFirstColumn() + 1;
                    //获取合并的列数
                    mergeSizeh =	ca.getLastRow()-ca.getFirstRow()+1;
                    map.put("mergeSizel",mergeSizel);
                    map.put("mergeSizeh",mergeSizeh);
                    map.put("firstColumn",firstColumn);
                    map.put("lastColumn",lastColumn);
                    map.put("firstRow",firstRow);
                    map.put("lastRow",lastRow);

                }
            }
        }

        return map ;
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

                } else if (format == 176 && isdate){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    cellValue = sdf.format(date);

                }
                else{
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
