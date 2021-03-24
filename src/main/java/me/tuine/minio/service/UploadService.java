package me.tuine.minio.service;

import java.util.Map;

/**
 * @author tuine
 * @date 2021/3/23
 */
public interface UploadService {

    /**
     * 分片上传初始化
     *
     * @param path        路径
     * @param filename    文件名
     * @param partCount   分片数量
     * @param contentType /
     * @return /
     */
    Map<String, Object> initMultiPartUpload(String path, String filename, Integer partCount, String contentType);

    /**
     * 完成分片上传
     *
     * @param objectName 文件名
     * @param uploadId 标识
     * @return /
     */
     boolean mergeMultipartUpload(String objectName, String uploadId);
}
