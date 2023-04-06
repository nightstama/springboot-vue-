package com.ns.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ns.entity.Building;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author fankx
* @description 针对表【building】的数据库操作Service
* @createDate 2023-03-30 16:50:14
*/
public interface BuildingService extends IService<Building> {
    List<Building> queryAll();

    Page<Building> search(Integer id,String value, int pageNum, int pageSize);

}
