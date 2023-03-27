package com.ns.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ns.mapper.CourseMapper;
import com.ns.entity.Course;
import com.ns.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseDao;

    @Override
    public IPage<Course> search(Course course) {
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank("name"),"name",course.getName());
        return courseDao.selectPage(new Page<>(course.getPageNum(), course.getPageSize()),queryWrapper);
    }
}
