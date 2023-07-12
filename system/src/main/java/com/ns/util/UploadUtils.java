package com.ns.util;


import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UploadUtils {
    public static ConcurrentHashMap<String, OSS> ossClientMap = new ConcurrentHashMap<>();
    @Value("${ali.ossKey}")
    private String OSSKEY;

    @Value("${ali.ossSecret}")
    private String OSSSECRET;

    public Map<String,Object> upload(String filePath, String fileName){
        String endpoint="https://oss-cn-guangzhou.aliyuncs.com";
        OSS ossClient = new OSSClientBuilder().build(endpoint, OSSKEY, OSSSECRET);
        String bucketName = "r-99";
        File file = new File(filePath);
        HashMap<String, Object> msg = new HashMap<>();
        try {
            if (file.isFile()){
                ossClient.putObject(bucketName,fileName,file);
                String url = signUrl(OSSKEY, OSSSECRET, endpoint, bucketName, fileName);
                msg.put("url",url);
            }
        }finally {
            ossClient.shutdown();
        }
        return msg;
    }

    public static String signUrl(String key, String secret, String endpoint, String bucket, String object){
        // 设置URL过期时间为2小时
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 4);
        // 生成URL
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, object, HttpMethod.GET);
        request.setExpiration(expiration);

        URL signedUrl = getOssClient(key, secret, endpoint).generatePresignedUrl(request);
        return signedUrl.toString();
    }
    public static OSS getOssClient(String key, String secret, String endpoint) {
        OSS oc = ossClientMap.get(endpoint);
        if (oc == null) {
            oc = new OSSClientBuilder().build(endpoint, key, secret);
            ossClientMap.put(endpoint, oc);
            return oc;
        }
        return oc;
    }

}
