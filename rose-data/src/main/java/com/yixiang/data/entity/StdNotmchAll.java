package com.yixiang.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 不动服务点数量
 * </p>
 *
 * @author zc
 * @since 2020-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StdNotmchAll implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行政编码
     */
    @TableId(type = IdType.INPUT)
    private String orgCode;

    /**
     * 行政区名称
     */
    private String orgName;

    /**
     * 一个月不动户
     */
    private String onemCount;

    /**
     * 三个月不动户
     */
    private String threemCount;

    /**
     * 六个月不动户
     */
    private String sixmCount;

    /**
     * 一年不动户
     */
    private String oneyearCount;

    /**
     * 批次号
     */
    private String batchCode;

    /**
     * 备用一
     */
    private String upOne;

    /**
     * 备用二
     */
    private String upTwo;

    /**
     * 备用三
     */
    private String upThree;

    /**
     * 备用四
     */
    private String upFour;


}
