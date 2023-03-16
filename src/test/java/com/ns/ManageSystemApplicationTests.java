package com.ns;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ns.dao.SysUserDao;
import com.ns.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
class ManageSystemApplicationTests {

    @Resource
    private SysUserDao userDao;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        ExecutorBuilder executorBuilder = ExecutorBuilder.create();
        //初始5个线程
        //最大10个线程
        //有界等待队列，最大等待数是100
        executorBuilder.setCorePoolSize(5).setMaxPoolSize(10).setWorkQueue(new LinkedBlockingQueue<>(100));
        ThreadPoolExecutor pool = executorBuilder.build();
        for (int i = 1; i < 15; i++) {
            int n=i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(sum(1, 2));
                        System.out.println("开始执行" + n);
                        Thread.sleep(3000L);
                        System.out.println("执行结束" + n);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("任务提交成功：" + i);
        }
    }

    public static int sum (int a,int b){
        return a+b;
    }


}
