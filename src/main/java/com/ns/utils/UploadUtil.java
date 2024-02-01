package com.ns.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.SimplifiedObjectMeta;
import com.ns.entity.Dict;
import com.ns.dao.DictDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;
import java.util.Date;

@Component
@Slf4j
public class UploadUtil {
    @Resource
    private DictDao dictMapper;
    @Value("{ali.ossKey}")
    private String OSSKEY;

    @Value("{ali.ossSecret}")
    private String OSSSECRET;

    @Value("{upload.path}")
    private String path;


    public String uploadExcel(String fileName) throws Exception {
        String filePath=path+fileName;
        File file = new File(filePath);
        Dict tabDict = dictMapper.selectById("oss_bucket_word");
        String bucket = tabDict.getValue1();
        String endpoint = tabDict.getValue2();

        OSS ossClient = new OSSClientBuilder().build(endpoint, OSSKEY, OSSSECRET);
        String fileUrl=null;
        if (file.isFile()){
            try{
                //发送
                /* 创建PutObjectRequest对象。
                 *    参数1: Bucket名称
                 *    参数2：Object完整路径
                 *    参数3: 需要上传文件的本地路径
                 * */
                ossClient.putObject(bucket,fileName,file);
                //设置url过期时间为一天
                Date expirationTime = new Date(System.currentTimeMillis() + 3600 * 24);
                // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
                URL url = ossClient.generatePresignedUrl(bucket, fileName, expirationTime);

                //获取获取文件元信息：上传信息所有数据
                SimplifiedObjectMeta meta = ossClient.getSimplifiedObjectMeta(bucket, fileName);
                fileUrl = url.toString();
            }catch (Exception e){
                throw new Exception("上传失败");
            }finally {
                ossClient.shutdown();
            }
        }
        return fileUrl;
    }
}
