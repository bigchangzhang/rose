package com.yixiang.data.service.impl;

import com.yixiang.data.entity.YntTrade;
import com.yixiang.data.mapper.YntTradeMapper;
import com.yixiang.data.service.IYntTradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合作视图信息 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Service
public class YntTradeServiceImpl extends ServiceImpl<YntTradeMapper, YntTrade> implements IYntTradeService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> objectList : listByExcel) {
                YntTrade trade = new YntTrade();
                map.put("hang",listByExcel.indexOf(objectList));
               String areaCode= String.valueOf(objectList.get(0));
               String trade_name= String.valueOf(objectList.get(1));
               String tradeType= String.valueOf(objectList.get(2));
               String servNm= String.valueOf(objectList.get(3));
               String zb= String.valueOf(objectList.get(4));
               trade.setArea(areaCode);
               trade.setBacthCode(batchCode);
               trade.setCount(servNm);
               trade.setTradeType(tradeType);
               trade.setMix(zb);
               trade.setTradeName(trade_name);
                boolean save = this.save(trade);
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

    @Override
    public List<Map> selectProInfo() {
        return this.baseMapper.selectProInfo();
    }

    @Override
    public List<YntTrade> orderBYcountList(String area) {
        return this.baseMapper.orderBYcountList(area);
    }
}
