package com.yixiang.data.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zc
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YntTotalNew implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaName;

    @TableId(type = IdType.INPUT)
    private String areaCode;

    private String xwkdye;

    private String xwkdkh;

    private String dyydye;

    private String dyydkh;

    private String nhkdye;

    private String nhkdkh;

    private String jtjjh;

    private String jtjjxz;

    private String bacthCode;

    private String coo;

    private String ctt;

    private String cth;

    private String cfo;

    private String pCode;


}
