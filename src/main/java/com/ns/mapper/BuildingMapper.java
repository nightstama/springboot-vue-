package com.ns.mapper;

import com.ns.entity.Building;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author fankx
* @description 针对表【building】的数据库操作Mapper
* @createDate 2023-03-30 16:50:14
* @Entity com.ns.entity.Building
*/
@Mapper
public interface BuildingMapper extends BaseMapper<Building> {

}




