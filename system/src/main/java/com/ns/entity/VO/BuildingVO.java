package com.ns.entity.VO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ns.entity.Building;
import lombok.Data;

import java.util.List;

@Data
public class BuildingVO {
    private List<Building> tagList;
    private Page<Building> tagPage;
}
