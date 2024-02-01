package com.ns.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ns.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictDao extends BaseMapper<Dict> {
}
