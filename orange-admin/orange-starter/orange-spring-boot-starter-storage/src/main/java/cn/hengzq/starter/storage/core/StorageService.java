package cn.hengzq.starter.storage.core;


import cn.hengzq.starter.storage.dto.UploadResult;

/**
 * @Description:
 * @author 程序员橙子
 * @date: 2022/10/25
 */
public interface StorageService {


    /**
     * 文件上传
     *
     * @param content  文件字节数组
     * @param filename 文件名
     * @return 返回存储路径 相对路径
     */
    UploadResult upload(byte[] content, String filename);


    /**
     * 文件上传
     *
     * @param content  文件字节数组
     * @param path     相对路径
     * @param filename 文件名
     * @return 返回存储路径 相对路径
     */
    UploadResult upload(byte[] content, String path, String filename);


    /**
     * 获得文件的内容
     *
     * @param path 相对路径
     * @return 文件的内容
     */
    byte[] getContent(String path);

}
