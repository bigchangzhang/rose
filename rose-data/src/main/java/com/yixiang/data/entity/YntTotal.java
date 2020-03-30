package com.yixiang.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 服务点统计表-左屏
 * </p>
 *
 * @author zc
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YntTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地区名称
     */
    private String areaName;

    /**
     * 地区编号
     */
    private String areaCode;

    /**
     * 机构名称
     */
    private String jgName;

    /**
     * 机构编号
     */
    private String jgCode;

    /**
     * 助农取款本年累计笔数
     */
    private Double znqkYearNum;

    /**
     * 助农取款本年累计金额
     */
    private Double znqkYearMoney;

    /**
     * 现金汇款本年累计笔数
     */
    private Double xjhkYearNum;

    /**
     * 现金汇款本年累计金额
     */
    private Double xjhkYearMoney;

    /**
     * 定活互转本年累计笔数
     */
    private Double dhhzYearNum;

    /**
     * 定活互转本年累计金额
     */
    private Double dhhzYearMoney;

    /**
     * 转账汇款本年累计笔数
     */
    private Double zzhkYearNum;

    /**
     * 转账汇款本年累计金额
     */
    private Double zzhkYearMoney;

    /**
     * 大额存单本年累计笔数
     */
    private String decdNum;

    /**
     * 大额存单本年累计金额
     */
    private String decdYearMoney;

    /**
     * 生活缴费本年累计笔数
     */
    private String shjfNum;

    /**
     * 生活缴费本年累计金额
     */
    private String shjfYearMoney;

    /**
     * 社保缴费本年累计笔数
     */
    private String sbjfNum;

    /**
     * 社保缴费本年累计金额
     */
    private String sbjfYearMoney;

    /**
     * 活跃点数
     */
    private String hyPointNum;

    /**
     * 双十占比
     */
    private String doubleTenProportion;

    /**
     * 双五十占比
     */
    private String doubleFiftyProportion;

    /**
     * 双一百占比
     */
    private String doubleHundredProportion;

    /**
     * 批次号
     */
    private Double bacthCode;


}
