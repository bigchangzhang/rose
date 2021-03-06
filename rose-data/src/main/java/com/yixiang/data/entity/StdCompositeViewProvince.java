package com.yixiang.data.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省视图表
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StdCompositeViewProvince implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 视图位置
     */
    private String reportPlace;

    /**
     * 横轴
     */
    private String abscissaAxis;

    /**
     * 纵轴一
     */
    private String verticalAxisA;

    /**
     * 纵轴二
     */
    private String verticalAxisB;

    /**
     * 预留字段一
     */
    private String column1;

    /**
     * 预留字段二
     */
    private String column2;

    /**
     * 预留字段三
     */
    private String column3;

    /**
     * 预留字段四
     */
    private String column4;

    /**
     * 批次号
     */
    private String batchCode;


}
