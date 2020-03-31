package com.yixiang.data.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合作视图信息
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YntTrade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地区编码
     */
    private String area;

    /**
     * 行业名称
     */
    private String tradeName;

    /**
     * 行业类型1生活超市、2诊所、3村委会、4农资店、5退役军人服务店、6手机通讯店、7点商平台、8家电超市
     */
    private String tradeType;

    /**
     * 行业个数
     */
    private String count;

    /**
     * 行业占比
     */
    private String mix;

    /**
     * 行业占比
     */
    private String bacthCode;


}
