package com.yixiang.data.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 流水表
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YntDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户编号
     */
    private String ordrTxnTpcd;

    /**
     * 交易码
     */
    private String svrName;

    /**
     * 交易方式
     */
    private String aspdNm;

    /**
     * 交易日期
     */
    private String txnamt;

    /**
     * 交易时间
     */
    private String cntprtacc;

    /**
     * 交易金额
     */
    private String hdlInsid;

    /**
     * 机构编号
     */
    private String cityName;

    /**
     * 机构名称
     */
    private String stmExecTm;

    /**
     * 批次号
     */
    private String bacthCode;


}
