package com.yixiang;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.BeanProperty;
import com.yixiang.data.entity.*;
import com.yixiang.data.mapper.PartenSrveiwMapper;
import com.yixiang.data.service.*;
import com.yixiang.rose.common.utils.ExcelUtils;
import com.yixiang.rose.common.utils.ResultModel;
import com.yixiang.rose.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
import java.util.*;

@RestController
@RequestMapping("/upload")
@Log4j2
@CrossOrigin("*")
public class UploadController {

    @Autowired
    IYntDistrictPointNumService iYntDistrictPointNumService;

    @Autowired
    IUploadLogService iUploadLogService;

    @Autowired
    IStdRiskWarnService iStdRiskWarnService;

    @Autowired
    IStdCompositeViewCityService iStdCompositeViewCityService;

    @Autowired
    IYntTotalService iYntTotalService;

    @Autowired
    IYntTradeService iYntTradeService;

    @Autowired
    IYqEcpGpfPointService iYqEcpGpfPointService;

    @Autowired
    IYntDetailService iYntDetailService;

    @Autowired
    IStdCompositeViewProvinceService iStdCompositeViewProvinceService;

    @Autowired
    IStdTopService iStdTopService;

    @Autowired
    IYntTermCheckService iYntTermCheckService;

    @Autowired
    IPartenSrveiwService iPartenSrveiwService;

    @Autowired
    PartenSrveiwMapper partenSrveiwMapper;

    @Autowired
    IStdImmBtService iStdImmBtService;

    @Autowired
    IYntTotalNewService iYntTotalNewService;



    @RequestMapping(value="/excelExport/{fileid}",method = RequestMethod.GET)
    public Object excel2007Export(@PathVariable(name = "fileid")String fileid ,HttpServletResponse response, HttpServletRequest request) {
        //1、风险预警2、合作视图3、巡检4、服务点信息5、流水6、服务点数据7、按钮8、省视图9、市视图10、按钮汇总11、服务点排名
        try {
            if("1".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"stdRiskWarn.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通风险预警明细数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("2".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntTrade.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通服务点发展情况行业汇总数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("3".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntTermCheck.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通巡检明细数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();

            }
            if("4".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"YqEcpGpfPoint.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通业务服务点明细数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("5".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntDetail.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通交易流水明细数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("6".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntDistrictPointNum.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通乡镇服务点统计数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("7".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"stdImmBt.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通蓝色按钮数据表.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();

            }
            if("8".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"stdCompositeViewProvince.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通综合汇总数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("9".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"stdCompositeViewCity.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通综合汇总数据模板（按市汇总）.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("10".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntTotal.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通交易情况汇总数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("11".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"stdTop.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行裕农通服务点排名明细数据模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("12".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntTotalNew.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行涉农贷款及集体经济组织开户汇总数据市级模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }
            if("13".equals(fileid)){
                ClassPathResource cpr = new ClassPathResource("/templates/"+"yntTotalNewCity.xlsx");
                InputStream is = cpr.getInputStream();
                Workbook workbook = new XSSFWorkbook(is);
                String fileName = "河北分行涉农贷款及集体经济组织开户汇总数据区县模板.xlsx";
                downLoadExcel(fileName, response, workbook);
                is.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";
    }

    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/salary/upload")
    public Object uploadSalary(String fileId,MultipartFile file,HttpServletRequest request)throws Exception {
        //1、风险预警2、合作视图3、巡检4、服务点信息5、流水6、服务点数据7、按钮8、省视图9、市视图10、按钮汇总11、服务点排名
        String nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String crDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Userlogin one = (Userlogin) request.getSession().getAttribute("user");
        String moudel = "";

        ResultModel resultModel = new ResultModel();
        try {
            if("1".equals(fileId)){
                moudel="stdRiskWarn";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 4, 0,true);
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","stdRiskWarn");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("stdRiskWarn");
                    iUploadLogService.save(uploadLog);
                    Boolean aBoolean = iStdRiskWarnService.saveExcel(listByExcel,nowDate);
                    if (aBoolean){
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel","stdRiskWarn");
                        queryWrappers.eq("bacth_code",nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        System.out.println(b);
                    }

                }

            }
            if("2".equals(fileId)){
                moudel="yntTrade";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 4, 0,false,false,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","yntTrade");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("yntTrade");
                    iUploadLogService.save(uploadLog);
                    Map map = iYntTradeService.saveExcel(listByExcel,nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole){
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel","yntTrade");
                        queryWrappers.eq("bacth_code",nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第"+hang+"行数据异常", null);
                    }else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel","yntTrade");
                        queryWrappers.eq("bacth_code",nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                        List<Map> maps = iYntTradeService.selectProInfo();
                        for (Map map1 : maps) {
                            YntTrade yntTrade = new YntTrade();
                            yntTrade.setTradeName(String.valueOf(map1.get("trade_name")));
                            yntTrade.setTradeType(String.valueOf(map1.get("trade_type")));
                            yntTrade.setArea("130000");
                            yntTrade.setCount(String.valueOf(map1.get("count")));
                            yntTrade.setBacthCode(nowDate);
                            iYntTradeService.save(yntTrade);
                        }
                    }

                }
            }
            if("3".equals(fileId)){
                moudel="yntTermCheck";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 3, 0,true,true,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","yntTermCheck");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("yntTermCheck");
                    iUploadLogService.save(uploadLog);
                    Map map = iYntTermCheckService.saveExcel(listByExcel, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntTermCheck");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntTermCheck");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }
            }
            if("4".equals(fileId)){
                moudel="yqEcpGpfPoint";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","yqEcpGpfPoint");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("yqEcpGpfPoint");
                    iUploadLogService.save(uploadLog);
                    Map map = iYqEcpGpfPointService.saveExcel(listByExcel, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yqEcpGpfPoint");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yqEcpGpfPoint");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }
                }
            if("5".equals(fileId)){
                moudel="yntDetail";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 3, 0,false,false,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","yntDetail");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("yntDetail");
                    iUploadLogService.save(uploadLog);
                    Map map = iYntDetailService.saveExcel(listByExcel, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntDetail");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntDetail");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);
                    }
                }

            }
            if("6".equals(fileId)){
                moudel="yntDistrictPointNum";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","yntDistrictPointNum");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("yntDistrictPointNum");
                    iUploadLogService.save(uploadLog);
                    Map map = iYntDistrictPointNumService.saveExcel(listByExcel, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntDistrictPointNum");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);

                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntDistrictPointNum");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);
                    }
                }

            }
            if("7".equals(fileId)){
                moudel="stdImmBt";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 3, 0,true,false,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","stdImmBt");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("stdImmBt");
                    iUploadLogService.save(uploadLog);
                    Map map = iStdImmBtService.saveExcel(listByExcel, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdImmBt");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdImmBt");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);
                    }
                }

            }
            if("8".equals(fileId)){
                moudel="stdCompositeViewProvince";
               // List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"1");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","stdCompositeViewProvince");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("stdCompositeViewProvince");
                    iUploadLogService.save(uploadLog);
                    Map map = iStdCompositeViewProvinceService.saveExcel(file, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdCompositeViewProvince");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdCompositeViewProvince");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }

            }
            if("9".equals(fileId)){
                moudel="stdCompositeViewCity";
                // List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"1");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","stdCompositeViewCity");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("stdCompositeViewCity");
                    iUploadLogService.save(uploadLog);
                    Map map = iStdCompositeViewCityService.saveExcel(file, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdCompositeViewCity");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdCompositeViewCity");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }
            }
            if("10".equals(fileId)){
                moudel="yntTotal";
                //List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"1");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","yntTotal");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("yntTotal");
                    iUploadLogService.save(uploadLog);
                    Map map = iYntTotalService.saveExcel(file, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntTotal");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "yntTotal");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }


            }
            if("11".equals(fileId)){
                moudel="stdTop";
                //List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"1");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel","stdTop");
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel("stdTop");
                    iUploadLogService.save(uploadLog);
                    Map map = iStdTopService.saveExcel(file, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdTop");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", "stdTop");
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }

            }
            if("12".equals(fileId)){
                moudel="yntTotalNew";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel",moudel);
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel(moudel);
                    iUploadLogService.save(uploadLog);
                    Map map = iYntTotalNewService.saveExcel(listByExcel, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", moudel);
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", moudel);
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }

            }
            if("13".equals(fileId)){
                moudel="yntTotalNewCity";
                List<List<Object>> listByExcel = ExcelUtils.getBankListByExcel(file.getInputStream(), file.getOriginalFilename(), 2, 0,false,false,"0");
                QueryWrapper<UploadLog> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("upload_moudel",moudel);
                queryWrapper.eq("upload_status","I");
                UploadLog logServiceOne = iUploadLogService.getOne(queryWrapper);
                if (null != logServiceOne){
                    resultModel.set(1, "导入失败，该模块有正在处理数据，请稍后重试", null);
                }else {
                    UploadLog uploadLog = new UploadLog();
                    uploadLog.setBacthCode(nowDate);
                    uploadLog.setCrTime(crDate);
                    uploadLog.setCrUser("ccbhb");
                    uploadLog.setUploadStatus("I");
                    uploadLog.setUploadMoudel(moudel);
                    iUploadLogService.save(uploadLog);
                    Map map = iYntTotalNewService.saveExcelCity(listByExcel, nowDate);
                    String hang = String.valueOf(map.get("hang"));
                    Boolean boole = (Boolean) map.get("back");
                    if (null ==boole || false==boole) {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", moudel);
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("E");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(1, "导入失败，数据第" + hang + "行数据异常", null);
                    } else {
                        QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
                        queryWrappers.eq("upload_moudel", moudel);
                        queryWrappers.eq("bacth_code", nowDate);
                        UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
                        logServiceOnes.setUploadStatus("S");
                        boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
                        resultModel.set(0, "导入成功", null);

                    }
                }

            }
        } catch (Exception e) {
            resultModel.set(1, "导入失败，系统异常", null);
            QueryWrapper<UploadLog> queryWrappers = new QueryWrapper<>();
            queryWrappers.eq("upload_moudel", moudel);
            queryWrappers.eq("bacth_code", nowDate);
            UploadLog logServiceOnes = iUploadLogService.getOne(queryWrappers);
            logServiceOnes.setUploadStatus("E");
            boolean b = iUploadLogService.saveOrUpdate(logServiceOnes);
        }
        return resultModel;
    }

    @PostMapping(value = "/salary/upload/srr")
    public Object uploadSrr(HttpServletResponse response)throws Exception {
        List<Map> list = new ArrayList();
        ClassPathResource cpr = new ClassPathResource("/templates/"+"stdRiskWarn.xlsx");
        InputStream is = cpr.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet bumenshouru = workbook.createSheet("bumenshouru");
        int row = 0;

        List<PartenSrveiw> partenSrveiws = partenSrveiwMapper.selectNm();
        for (PartenSrveiw partenSrveiw : partenSrveiws) {
            List<Map> maph = partenSrveiwMapper.selectHeng(partenSrveiw.getPartenNm());
            for (Map maph1 : maph) {
                maph1.put("bumen",partenSrveiw.getPartenNm());
            }
            List<Map> mapz = partenSrveiwMapper.selectZong(partenSrveiw.getPartenNm());
            for (Map mapz1 : mapz) {
                mapz1.put("bumen",partenSrveiw.getPartenNm());
            }
            List<Map> mapg = partenSrveiwMapper.selectGao(partenSrveiw.getPartenNm());
            for (Map mapg1 : mapg) {
                mapg1.put("bumen",partenSrveiw.getPartenNm());
            }
            List<Map> maps = partenSrveiwMapper.selectSuo(partenSrveiw.getPartenNm());
            for (Map maps1 : maps) {
                maps1.put("bumen",partenSrveiw.getPartenNm());
            }
            row = maph.size()+mapz.size()+mapg.size()+maps.size();
            list.addAll(maph);
            list.addAll(mapz);
            list.addAll(mapg);
            list.addAll(maps);
        }
        HSSFRow rows;

        HSSFCell cell;

        for(int i = 0; i < list.size(); i++) {
            Row row1 = bumenshouru.createRow(i);
            for(int j = 0; j < list.get(i).size(); j++) {
                Cell cell1 = row1.createCell(j);
                Map map = list.get(i);
                String bumen = (String) map.get("bumen");
                String zhanghao = (String) map.get("zhanghao");
                String years = (String) map.get("years");
                String parten_sr = String.valueOf(map.get("parten_sr"));
                if (j==0){
                    cell1.setCellValue(bumen);
                }
                if (j==1){
                    cell1.setCellValue(zhanghao);
                }
                if (j==2){
                    cell1.setCellValue(years);
                }
                if (j==3){
                    cell1.setCellValue(parten_sr);
                }
            }
        }
        String fileName = "bumenshouru.xlsx";
        downLoadExcel(fileName, response, workbook);
        is.close();
        ResultModel resultModel = new ResultModel();
        return null;
    }
}
