package com.yixiang.data.service.impl;

import com.yixiang.data.entity.YntDetail;
import com.yixiang.data.entity.YqEcpGpfPoint;
import com.yixiang.data.mapper.YntDetailMapper;
import com.yixiang.data.service.IYntDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 流水表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class YntDetailServiceImpl extends ServiceImpl<YntDetailMapper, YntDetail> implements IYntDetailService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> objectList : listByExcel) {
                YntDetail yntDetail = new YntDetail();
                map.put("hang",listByExcel.indexOf(objectList));
                String ordr_txn_tpcd= String.valueOf(objectList.get(0));
                String svr_name= String.valueOf(objectList.get(1));

                String aspd_nm= String.valueOf(objectList.get(2));
                String txnamt= String.valueOf(objectList.get(3));
                String cntprtacc= String.valueOf(objectList.get(4));
                String hdl_insid= String.valueOf(objectList.get(5));
                String city_name= String.valueOf(objectList.get(6));
                String stm_exec_tm= String.valueOf(objectList.get(7));
                yntDetail.setOrdrTxnTpcd(ordr_txn_tpcd);
                yntDetail.setSvrName(svr_name);
                yntDetail.setAspdNm(aspd_nm);
                yntDetail.setTxnamt(txnamt);
                yntDetail.setCntprtacc(cntprtacc);
                yntDetail.setHdlInsid(hdl_insid);
                yntDetail.setCityName(city_name);
                yntDetail.setStmExecTm(stm_exec_tm);
                yntDetail.setBacthCode(batchCode);
                boolean save = this.save(yntDetail);
                if (save){
                    back = true;
                    map.put("back",back);
                }else {
                    back = false;
                    map.put("back",back);
                    break;
                }
            }

        } catch (Exception e){
            if (map.size() ==0){
                back = false;
                map.put("back",back);
            }
        }
        return map;
    }
}
