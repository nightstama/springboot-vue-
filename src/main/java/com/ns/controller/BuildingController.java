package com.ns.controller;

import com.ns.common.ResultData;
import com.ns.entity.Building;
import com.ns.entity.VO.BuildingVO;
import com.ns.service.BuildingService;
import com.ns.util.TreeUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/building")
@Api(tags = "楼栋管理")
public class BuildingController {
    @Resource
    private BuildingService buildingService;


    @GetMapping("/search")
    private ResultData<BuildingVO> search(Integer id,String value, int pageNum, int pageSize){
        BuildingVO buildingVO=new BuildingVO();
        if (id==null){
            List<Building> buildingList = buildingService.queryAll();
            buildingVO.setTagList(TreeUtil.getTree(buildingList));
        }else {
            buildingVO.setTagPage(buildingService.search(id,value,pageNum,pageSize));
        }
        return new ResultData<>(buildingVO);
    }
}
