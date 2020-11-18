package com.yixiang.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 不动服务点明细
 * </p>
 *
 * @author 肉鸡子
 * @since 2020-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StdNotmchInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 行政编码
     */
    private String orgCode;

    /**
     * 行政区名称
     */
    private String orgName;

    /**
     * 服务点编码
     */
    private String mchCode;

    /**
     * 服务点名称
     */
    private String mchName;

    /**
     * 长期不动时间
     */
    private String notmchStatus;

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
