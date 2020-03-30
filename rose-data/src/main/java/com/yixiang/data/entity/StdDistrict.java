package com.yixiang.data.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 县区代码表
 * </p>
 *
 * @author zc
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StdDistrict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地区代码
     */
    @TableField("CODE_VALUE")
    private String codeValue;

    /**
     * 地区名称
     */
    @TableField("CODE_NAME")
    private String codeName;

    /**
     * 父级地区代码
     */
    @TableField("PARENT_CODE")
    private String parentCode;

    /**
     * 地区类型1省级、2市级、3区县、4乡镇、5村
     */
    @TableField("TYPE")
    private String type;


}
