package com.ns;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ns.dao.SysUserDao;
import com.ns.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ManageSystemApplicationTests {

	@Resource
	private SysUserDao userDao;

	@Test
	void contextLoads() {
	}

	@Test
	void test(){
		System.out.println();
	}

}
