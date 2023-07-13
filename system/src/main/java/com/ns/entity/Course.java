package com.ns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName(value = "course",resultMap = "BaseResultMap")
public class Course extends Model<Course> {
    //id
    private String id;
    //用户名
    private String name;
    //分数
    private Integer score;
    //上课时间
    private String times;
    //开课状态(未开课:0 已开课:1)
    private Integer state;
    //教师id
    private String teacherId;
    //页数
    @TableField(exist = false)
    private Integer pageSize;
    //页码
    @TableField(exist = false)
    private Integer pageNum;
}
