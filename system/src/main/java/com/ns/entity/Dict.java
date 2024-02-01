package com.ns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (TabDict)实体类
 *
 * @author makejava
 * @since 2024-01-16 11:34:02
 */
@Data
public class Dict implements Serializable {
    private static final long serialVersionUID = 435965817419923594L;

    @TableId
    private String code;

    private String dictName;

    private String parentCode;

    @TableField("value_1")
    private String value1;

    @TableField("value_2")
    private String value2;

    @TableField("value_3")
    private String value3;

    @TableField("value_4")
    private String value4;

    @TableField("value_5")
    private String value5;

    private Long level;

    private Long leaf;

    private Long sortNum;

    private String note;

    private Date createTime;

    private Date updateTime;

    private Long dictState;

}

