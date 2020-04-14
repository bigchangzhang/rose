package com.yixiang;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixiang.data.entity.UploadLog;
import com.yixiang.data.entity.Userlogin;
import com.yixiang.data.entity.YntDistrictPointNum;
import com.yixiang.data.service.IUploadLogService;
import com.yixiang.data.service.IYntDistrictPointNumService;
import com.yixiang.rose.common.utils.ExcelUtils;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/upload")
@Log4j2
@CrossOrigin("*")
public class UploadController {

    @Autowired
    IYntDistrictPointNumService iYntDistrictPointNumService;

    @Autowired
    IUploadLogService iUploadLogService;




    @RequestMapping(value="/excelExport")
    public Object excel2007Export(String fileId ,HttpServletResponse response, HttpServletRequest request) {
        //1、风险预警2、合作视图3、巡检4、服务点信息5、流水6、服务点数据7、按钮8、省视图9、市视图10、按钮汇总11、服务点排名
        try {
            if("1".equals(fileId)){

            }
            if("2".equals(fileId)){

            }
            if("3".equals(fileId)){

            }
            if("4".equals(fileId)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"YqEcpGpfPoint.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通业务服务点明细数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("5".equals(fileId)){

            }
            if("6".equals(fileId)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntDistrictPointNum.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通乡镇服务点统计数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("7".equals(fileId)){

            }
            if("8".equals(fileId)){

            }
            if("9".equals(fileId)){

            }
            if("10".equals(fileId)){

            }
            if("11".equals(fileId)){

            }

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

    @PostMapping("/salary/upload")
    public String uploadSalary(String fileId,MultipartFile file,HttpServletRequest request)throws Exception {
        String nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String crDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Userlogin one = (Userlogin) request.getSession().getAttribute("user");

        UploadLog uploadLog = new UploadLog();
        uploadLog.setBacthCode(nowDate);
        uploadLog.setCrTime(crDate);
        uploadLog.setCrUser(one.getUsername());


        QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
        ResultModel resultModel = new ResultModel();
        try {
            fileId="6";
            List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0);
            if("1".equals(fileId)){
                queryWrapper.eq("upload_moudel","");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){

                }else {

                }

            }
            if("2".equals(fileId)){

            }
            if("3".equals(fileId)){

            }
            if("4".equals(fileId)){

            }
            if("5".equals(fileId)){

            }
            if("6".equals(fileId)){
                Boolean aBoolean = iYntDistrictPointNumService.saveExcel(listByExcel);

            }
            if("7".equals(fileId)){

            }
            if("8".equals(fileId)){

            }
            if("9".equals(fileId)){

            }
            if("10".equals(fileId)){

            }
            if("11".equals(fileId)){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "done";
    }

}
