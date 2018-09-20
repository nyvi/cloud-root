package com.github.cloud.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseDO 定义数据库公共字段
 *
 * @author : czk
 * @date 2018-07-02
 */
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改人
     */
    private Long modifyBy;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModify;
    /**
     * 是否有效 0-无效 1-有效
     */
    @TableLogic
    private Integer active;
    /**
     * 版本号
     */
    private Long versionNo;
}
