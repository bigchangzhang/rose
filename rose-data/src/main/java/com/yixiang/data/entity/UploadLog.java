package com.yixiang.data.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 导入日志表
 * </p>
 *
 * @author zc
 * @since 2020-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UploadLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 导入模块
     */
    private String uploadMoudel;

    /**
     * 导入状态
     */
    private String uploadStatus;

    /**
     * 创建时间
     */
    private String crTime;

    /**
     * 创建时间
     */
    private String crUser;

    /**
     * 批次号
     */
    @TableId(type = IdType.INPUT)
    private String bacthCode;


}
