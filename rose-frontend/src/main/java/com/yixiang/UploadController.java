package com.yixiang;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/upload")
public class UploadController {




    @RequestMapping(value="/excelExport")
    public Object excel2007Export(HttpServletResponse response, HttpServletRequest request) {
        try {
            ClassPathResource cpr = new ClassPathResource("/templates/"+"student.xlsx");
            InputStream is = cpr.getInputStream();
            Workbook workbook = new XSSFWorkbook(is);
            org.apache.poi.ss.usermodel.Sheet sheet0 =workbook.getSheetAt(0);
            //Row row = sheet0.getRow(2);
            Row row1 = sheet0.createRow(2);
            Cell cell0 = row1.createCell(0);
            Cell cell1 = row1.createCell(1);
            Cell cell2 = row1.createCell(2);
            Cell cell3 = row1.createCell(3);
            cell0.setCellValue("guo");
            cell1.setCellValue("bin");
            cell2.setCellValue("hui");
            System.out.println(cell0);
            //这里作为演示，造几个演示数据，模拟数据库里查数据
            String fileName = "moban.xlsx";
            downLoadExcel(fileName, response, workbook);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "done";
    }

    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
            try {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
