package com.ns.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ns.common.ResultData;
import com.ns.entity.Course;
import com.ns.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
@Api(tags = "课程管理")
public class CourseController {
    @Resource
    private CourseService courseService;
    @GetMapping("/search")
    public ResultData<IPage<Course>> search(Course course){
        return new ResultData<>(courseService.search(course));
    }
}
