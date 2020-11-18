package com.yixiang.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zc
 * @since 2020-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YntTotalPt implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaName;

    @TableId(type = IdType.INPUT)
    private String areaCode;

    private String dpsbs;

    private String kjl;

    private String yhzcs;

    private String ktqbs;

    private String dpfwl;

    private String appfwl;

    private String ptjfje;

    private String ptjfbs;

    private String ptmmje;

    private String ptmmbs;

    private String ptchfbbs;

    private String yntbye;

    private String qtjjye;

    private String bacthCode;

    private String coo;

    private String ctt;

    private String cth;

    private String cfo;

    private String pCode;


}
