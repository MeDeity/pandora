package com.mengya.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public interface IAliyunOssService {

    String generateVisitUrl(String objectName);
    /**
     * 生成文件的访问地址
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param endPoint   endpoint是访问OSS的域名
     * @param objectName 待保存对象的键
     * @return  生成文件的访问地址
     */
    String generateVisitUrl(String bucketName, String endPoint, String objectName);
    /**
     * 保存文件到服务器
     * @param key        待保存对象的键
     * @param file      待保存对象的内容(文件)
     */
    String upload(String key, File file);
    /**
     * 保存文件到服务器
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param key        待保存对象的键
     * @param file      待保存对象的内容(文件)
     */
    String upload(String bucketName, String key, File file);

    /**
     * 保存文件到服务器
     * @param key        待保存对象的键
     * @param file      待保存对象的内容(文件)
     */
    void upload(String key, MultipartFile file);
    /**
     * 保存文件到服务器
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param key        待保存对象的键
     * @param file      待保存对象的内容(文件)
     */
    void upload(String bucketName, String key, MultipartFile file);

    /**
     * 保存字符串到服务器
     * @param objectName        待保存对象的键
     * @param content      待保存对象的内容(文件)
     */
    void upload(String objectName, String content);
    /**
     * 保存字符串到服务器
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     * @param content      待保存对象的内容(文件)
     */
    void upload(String bucketName, String objectName, String content);


    /**
     * 流式下载
     * 如果要下载的文件太大，或者一次性下载耗时太长，您可以通过流式下载，一次处理部分内容，直到完成文件的下载。
     * ossObject对象使用完毕后必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作
     * @param key        待保存对象的键
     */
    List<String> downloadStream(String key);

    /**
     * 流式下载
     * 如果要下载的文件太大，或者一次性下载耗时太长，您可以通过流式下载，一次处理部分内容，直到完成文件的下载。
     * ossObject对象使用完毕后必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param key        待保存对象的键
     */
    List<String> downloadStream(String bucketName, String key);

    /**
     * 下载文件到本地
     * @param objectName        待保存对象的键
     * @param localFilePath     本地保存路径
     */
    void downloadFile(String objectName, String localFilePath);
    /**
     * 下载文件到本地
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     * @param localFilePath     本地保存路径
     */
    File downloadFile(String bucketName, String objectName, String localFilePath);

    /**
     * 断点续传下载文件到本地
     * @param objectName        待保存对象的键
     * @param localFilePath     本地保存路径
     */
    void downloadBreakPointFile(String objectName, String localFilePath);
    /**
     * 断点续传下载文件到本地
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     * @param localFilePath     本地保存路径
     */
    void downloadBreakPointFile(String bucketName, String objectName, String localFilePath);

    /**
     * 删除对象
     * @param objectName        待保存对象的键
     */
    void delete(String objectName);
    /**
     * 删除对象
     * @param bucketName Bucket用来管理所存储Object的存储空间
     * @param objectName        待保存对象的键
     */
    void delete(String bucketName, String objectName);


}
