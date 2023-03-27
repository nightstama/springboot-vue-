package com.ns.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ns.entity.Course;

public interface CourseService{
    IPage<Course> search(Course course);

}
