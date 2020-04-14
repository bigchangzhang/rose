package com.yixiang.data.service.impl;

import com.yixiang.data.entity.UploadLog;
import com.yixiang.data.mapper.UploadLogMapper;
import com.yixiang.data.service.IUploadLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 导入日志表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-04-13
 */
@Service
public class UploadLogServiceImpl extends ServiceImpl<UploadLogMapper, UploadLog> implements IUploadLogService {

}
