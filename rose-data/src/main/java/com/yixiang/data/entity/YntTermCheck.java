package com.yixiang.data.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 巡检表
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YntTermCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 地市名称
     */
    private String cityName;

    /**
     * 商户名称
     */
    private String mchName;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 创建时间
     */
    private String createTm;

    /**
     * 巡检日期
     */
    private String jtmChangeDate;

    /**
     * 巡检方式
     */
    private String checkTypeName;

    /**
     * 巡检结果
     */
    private String checkResult;

    /**
     * 批次号
     */
    private String bacthCode;


}
