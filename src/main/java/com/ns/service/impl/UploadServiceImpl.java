package com.ns.service.impl;

import com.ns.service.UploadService;
import com.ns.util.UploadUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UploadServiceImpl implements UploadService {
    @Resource
    private UploadUtils uploadUtil;
    @Override
    public Map<String, Object> upload() {
        String filePath="E:\\test\\oss上传测试.txt";
        String fileName="oss上传测试.txt";
        return uploadUtil.upload(filePath,fileName);
    }
}
