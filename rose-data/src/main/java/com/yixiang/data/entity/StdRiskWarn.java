package com.yixiang.data.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 风险预警表
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StdRiskWarn implements Serializable {

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
     * 交易时间
     */
    private String trsDate;

    /**
     * 时点存款数额
     */
    private String trsMoney;

    /**
     * 交易金额
     */
    private String trsNum;

    /**
     * 预警类型
     */
    private String tiskType;

    /**
     * 批次号
     */
    private String batchCode;


}
