package com.mengya.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.mengya.oss.config.AliyunOssProperties;
import com.mengya.oss.service.IAliyunOssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AliyunOssServiceImpl implements IAliyunOssService {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Override
    public String generateVisitUrl(String objectName) {
        return generateVisitUrl(aliyunOssProperties.getBucketName(), aliyunOssProperties.getEndPoint(),objectName);
    }

    /**
     * 生成文件的访问地址
     *
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param endPoint   endpoint是访问OSS的域名
     * @param objectName 待保存对象的键
     * @return 生成文件的访问地址
     */
    @Override
    public String generateVisitUrl(String bucketName, String endPoint, String objectName) {
        String suffixEndpoint = endPoint.substring(endPoint.lastIndexOf("/")+1);
        return String.format("https://%1s.%2s/%3s",bucketName,suffixEndpoint,objectName);
    }

    /**
     * 保存文件到服务器
     *
     * @param objectName  待保存对象的键
     * @param file 待保存对象的内容(文件)
     */
    @Override
    public String upload(String objectName, File file) {
        return upload(aliyunOssProperties.getBucketName(),objectName,file);
    }

    /**
     * 保存文件到服务器
     *
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     * @param objectName        待保存对象的键
     * @param file       待保存对象的内容(文件)
     */
    @Override
    public String upload(String bucketName, String objectName, File file) {
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);
        // 上传文件。
        ossClient.putObject(putObjectRequest);
        return generateVisitUrl(bucketName,aliyunOssProperties.getEndPoint(),objectName);
    }

    /**
     * 保存文件到服务器
     *
     * @param objectName  待保存对象的键
     * @param file 待保存对象的内容(文件)
     */
    @Override
    public void upload(String objectName, MultipartFile file) {
        upload(aliyunOssProperties.getBucketName(),objectName,file);
    }

    /**
     * 保存文件到服务器
     *
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     * @param file       待保存对象的内容(文件)
     */
    @Override
    public void upload(String bucketName, String objectName, MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();// 上传文件流。
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (Exception e) {
            log.error("upload exception",e);
        }
    }

    /**
     * 保存字符串到服务器
     *
     * @param objectName 待保存对象的键
     * @param content    待保存对象的内容(文件)
     */
    @Override
    public void upload(String objectName, String content) {
        upload(aliyunOssProperties.getBucketName(),objectName,content);
    }

    /**
     * 保存字符串到服务器
     *
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     * @param content    待保存对象的内容(文件)
     */
    @Override
    public void upload(String bucketName, String objectName, String content) {
        // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
        InputStream is = new ByteArrayInputStream(content.getBytes());
        ossClient.putObject(bucketName, objectName, is);
    }

    /**
     * 流式下载
     * 如果要下载的文件太大，或者一次性下载耗时太长，您可以通过流式下载，一次处理部分内容，直到完成文件的下载。
     * ossObject对象使用完毕后必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作
     *
     * @param objectName 待保存对象的键
     */
    @Override
    public List<String> downloadStream(String objectName) {
        return downloadStream(aliyunOssProperties.getBucketName(),objectName);
    }

    /**
     * 流式下载
     * 如果要下载的文件太大，或者一次性下载耗时太长，您可以通过流式下载，一次处理部分内容，直到完成文件的下载。
     * ossObject对象使用完毕后必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作
     *
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     */
    @Override
    public List<String> downloadStream(String bucketName, String objectName) {
        List<String> list = new ArrayList<>();
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            // 读取文件内容。
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                list.add(line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            reader.close();
        }catch (Exception e){
            log.error("download exception",e);
        }
        return list;
    }

    /**
     * 下载文件到本地
     *
     * @param objectName    待保存对象的键
     * @param localFilePath 本地保存路径
     */
    @Override
    public void downloadFile(String objectName, String localFilePath) {
        downloadFile(aliyunOssProperties.getBucketName(),objectName,localFilePath);
    }

    /**
     * 下载文件到本地
     *
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     * @param localFilePath     本地保存路径
     */
    @Override
    public File downloadFile(String bucketName, String objectName,String localFilePath) {
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        File localFile = new File(localFilePath);
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), localFile);
        return localFile;
    }

    /**
     * 断点续传下载文件到本地
     *
     * @param objectName    待保存对象的键
     * @param localFilePath 本地保存路径
     */
    @Override
    public void downloadBreakPointFile(String objectName, String localFilePath) {
        downloadBreakPointFile(aliyunOssProperties.getBucketName(),objectName,localFilePath);
    }

    /**
     * 断点续传下载文件到本地
     *
     * @param bucketName    Bucket用来管理所存储Object的存储空间
     * @param objectName    待保存对象的键
     * @param localFilePath 本地保存路径
     */
    @Override
    public void downloadBreakPointFile(String bucketName, String objectName, String localFilePath) {
        try {
            String suffix = objectName.substring(objectName.lastIndexOf(".") + 1);//文件后缀名
            File tempFile = File.createTempFile("temp_", "." + suffix);
            // 下载请求，10个任务并发下载，启动断点续传。
            DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, objectName);
            downloadFileRequest.setDownloadFile(localFilePath);
            downloadFileRequest.setPartSize(1 * 1024 * 1024);
            downloadFileRequest.setTaskNum(10);
            downloadFileRequest.setEnableCheckpoint(true);
            downloadFileRequest.setCheckpointFile("<yourCheckpointFile>");

            // 下载文件。
            DownloadFileResult downloadRes = null;
            try {
                downloadRes = ossClient.downloadFile(downloadFileRequest);
                // 下载成功时，会返回文件元信息。
                downloadRes.getObjectMetadata();
            } catch (Throwable throwable) {
                log.error("breakpoint resume download throwable",throwable);
            }
        }catch (Exception e){
            log.error("breakpoint resume download exception",e);
        }
    }

    /**
     * 删除对象
     *
     * @param objectName 待保存对象的键
     */
    @Override
    public void delete(String objectName) {
        delete(aliyunOssProperties.getBucketName(),objectName);
    }

    /**
     * 删除对象
     *
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName 待保存对象的键
     */
    @Override
    public void delete(String bucketName, String objectName) {
        ossClient.deleteObject(bucketName, objectName);
    }
}
