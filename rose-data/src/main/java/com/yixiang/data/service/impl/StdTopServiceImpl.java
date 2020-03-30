package com.yixiang.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yixiang.data.entity.StdTop;
import com.yixiang.data.mapper.StdTopMapper;
import com.yixiang.data.service.IStdTopService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Top50表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-03-24
 */
@Service
public class StdTopServiceImpl extends ServiceImpl<StdTopMapper, StdTop> implements IStdTopService {

}
