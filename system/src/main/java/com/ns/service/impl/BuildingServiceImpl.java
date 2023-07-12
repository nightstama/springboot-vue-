package com.ns.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ns.entity.Building;
import com.ns.mapper.BuildingMapper;
import com.ns.service.BuildingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author fankx
* @description 针对表【building】的数据库操作Service实现
* @createDate 2023-03-30 16:50:14
*/
@Service("BuildingMapper")
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService{
    @Resource
    private BuildingMapper buildingMapper;
    @Override
    public List<Building> queryAll() {
        QueryWrapper<Building> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return buildingMapper.selectList(wrapper);
    }

    @Override
    public Page<Building> search(Integer id, String value, int pageNum, int pageSize) {
        QueryWrapper<Building> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank("value"),"value",value);
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }
}




