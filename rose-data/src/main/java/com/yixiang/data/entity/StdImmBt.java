package com.yixiang.data.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 蓝色按钮表
 * </p>
 *
 * @author zc
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StdImmBt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private transient List<Map> btmapList = new ArrayList<>();

    /**
     * 按钮编号
     */
    private String btNum;

    /**
     * 按钮名称
     */
    private String btNm;

    /**
     * 按钮内容
     */
    private String btContent;

    /**
     * 按钮类型
     */
    private String btType;

    /**
     * 批次号
     */
    private String batchCode;

    /**
     * 预留字段
     */
    private String column1;

    /**
     * 预留字段
     */
    private String column2;

    /**
     * 预留字段
     */
    private String column3;


}
