package com.yixiang.data.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务点表
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YqEcpGpfPoint implements Serializable {

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
     * 服务点地址
     */
    private String svcpntAdr;

    /**
     * 业主姓名
     */
    private String userName;

    /**
     * 商户电话号码
     */
    private String phone;

    /**
     * 经度
     */
    private String lat;

    /**
     * 纬度
     */
    private String lon;

    /**
     * 客户经理
     */
    private String manageUserName;

    /**
     * 所属网点编号
     */
    private String ccbinsId;

    /**
     * 所属网点名称
     */
    private String ccbinsChnShrtnm;

    /**
     * 签约卡
     */
    private Integer cardnum;

    /**
     * 沉淀存款
     */
    private String aum;

    /**
     * 本年累计交易笔数
     */
    private String yearNum;

    /**
     * 地市代码
     */
    private String jrCity;

    /**
     * 区县代码
     */
    private String jrArea;

    /**
     * 乡镇街道代码
     */
    private String jrStreet;

    /**
     * 村社区代码
     */
    private String jrVillage;

    /**
     * 地市名称
     */
    private String jrCityName;

    /**
     * 区县名称
     */
    private String jrAreaName;

    /**
     * 乡镇名称
     */
    private String jrStreetName;

    /**
     * 村社区名称
     */
    private String jrVillageName;

    /**
     * 批次号
     */
    private String bacthCode;


}
