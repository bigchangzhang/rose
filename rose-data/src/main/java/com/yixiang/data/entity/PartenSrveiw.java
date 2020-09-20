package com.yixiang.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收入表
 * </p>
 *
 * @author zc
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PartenSrveiw implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 部门名称
     */
    private String partenNm;

    /**
     * 收入时间
     */
    private String srtime;

    /**
     * 部门账号
     */
    private String partemZh;

    /**
     * 部门收入
     */
    private String partenSr;

    /**
     * 部门账号标识
     */
    private String partenZhBs;

    /**
     * 预留字段一
     */
    @TableField("COLUMN1")
    private String column1;

    /**
     * 预留字段二
     */
    @TableField("COLUMN2")
    private String column2;

    /**
     * 预留字段三
     */
    @TableField("COLUMN3")
    private String column3;

    /**
     * 预留字段四
     */
    @TableField("COLUMN4")
    private String column4;


}
