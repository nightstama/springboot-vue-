package com.ns.util;

import com.ns.entity.Building;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TreeUtil {
    public static List<Building> getTree(List<Building> Buildings) {
        List<Building> result = new ArrayList<>();
        for (Building Building : Buildings) {
            if (Building.getPid() == 0) {
                result.add(Building);
            }
        }
        for (Building building : result) {
            List<Building> children = getBuildChild(building.getId(), Buildings);
            building.setChildren(children);
        }
        return result;
    }
    public static List<Building> getBuildChild(Integer parentId,List<Building> all){
        ArrayList<Building> childList = new ArrayList<>();
        for (Building building : all) {
            if (building.getPid()==parentId){
                childList.add(building);
            }
        }
        for (Building building : childList) {
            building.setChildren(getBuildChild(building.getId(), all));
        }
        if (childList.size()==0){
            return new ArrayList<>();
        }
        return childList;
    }
}
