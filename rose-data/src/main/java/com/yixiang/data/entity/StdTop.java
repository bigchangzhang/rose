package com.yixiang.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * Top50表
 * </p>
 *
 * @author zc
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StdTop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务点编号
     */
    private String svcpntId;

    /**
     * 服务点名称
     */
    private String svcpntNm;

    /**
     * 签约卡量
     */
    private String qykNum;

    /**
     * 沉淀存款
     */
    private String sdckMoney;

    /**
     * 交易笔数
     */
    private String trsNum;

    /**
     * 批次
     */
    private String bacthCode;

    /**
     * 排名
     */
    private Long rankid;

    /**
     * 地区名称
     */
    private String areaName;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 预留一
     */
    private String coloumo;

    /**
     * 预留二
     */
    private String coloumt;


}
