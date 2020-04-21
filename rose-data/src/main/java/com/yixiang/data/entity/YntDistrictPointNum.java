package com.yixiang.data.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务点数据
 * </p>
 *
 * @author zc
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YntDistrictPointNum implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地市
     */
    private String city;

    /**
     * 区县
     */
    private String area;

    /**
     * 乡镇
     */
    private String village;

    /**
     * 地市代码
     */
    private String citycode;

    /**
     * 区县代码
     */
    private String areacode;

    /**
     * 乡镇代码
     */
    @TableId(type = IdType.INPUT)
    private String villagecode;

    /**
     * 活跃点数
     */
    private String hyPointNum;

    /**
     * 服务点数
     */
    private String pointNum;

    /**
     * 批次号
     */
    private String bacthCode;


}
