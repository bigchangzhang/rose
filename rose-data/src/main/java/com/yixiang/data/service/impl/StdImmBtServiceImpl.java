package com.yixiang.data.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixiang.data.entity.StdImmBt;
import com.yixiang.data.entity.YntTermCheck;
import com.yixiang.data.mapper.StdImmBtMapper;
import com.yixiang.data.service.IStdImmBtService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 蓝色按钮表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-23
 */
@Service
public class StdImmBtServiceImpl extends ServiceImpl<StdImmBtMapper, StdImmBt> implements IStdImmBtService {

    @Override
    public Map saveExcel(List<List<Object>> listByExcel, String batchCode) throws Exception {
        Boolean back = true;
        Map map = new HashMap<>();
        try {
            for (List<Object> list : listByExcel) {
                StdImmBt stdImmBt = new StdImmBt();
                map.put("hang",listByExcel.indexOf(list));
                String btName = String.valueOf(list.get(0));
                String btValue = String.valueOf(list.get(1));
                QueryWrapper<StdImmBt> queryWrapper = new QueryWrapper();
                queryWrapper.eq("bt_nm",btName);
                stdImmBt = this.getOne(queryWrapper);
                stdImmBt.setBtContent(btValue);
                stdImmBt.setBatchCode(batchCode);
                boolean b =this.saveOrUpdate(stdImmBt);
                if (b){
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
