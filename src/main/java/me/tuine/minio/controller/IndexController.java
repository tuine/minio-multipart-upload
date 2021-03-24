package me.tuine.minio.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONObject;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import me.tuine.minio.service.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author tuine
 * @date 2021/3/23
 */
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final UploadService uploadService;

    /**
     * 分片初始化
     *
     * @param requestParam 请求参数-此处简单处理
     * @return /
     */
    @PostMapping("/multipart/init")
    public ResponseEntity<Object> initMultiPartUpload(@RequestBody JSONObject requestParam) {
        // 路径
        String path = requestParam.getStr("path", "test");
        // 文件名
        String filename = requestParam.getStr("filename", "test.obj");
        // content-type
        String contentType = requestParam.getStr("contentType", "application/octet-stream");
        // md5-可进行秒传判断
        String md5 = requestParam.getStr("md5", "");
        // 分片数量
        Integer partCount = requestParam.getInt("partCount", 1);

        //TODO::业务判断+秒传判断

        Map<String, Object> result = uploadService.initMultiPartUpload(path, filename, partCount, contentType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 完成上传
     *
     * @param requestParam 用户参数
     * @return /
     */
    @PutMapping("/multipart/complete")
    public ResponseEntity<Object> completeMultiPartUpload(
            @RequestBody JSONObject requestParam
    ) {
        // 文件名完整路径
        String objectName = requestParam.getStr("objectName");
        String uploadId = requestParam.getStr("uploadId");
        Assert.notNull(objectName, "objectName must not be null");
        Assert.notNull(uploadId, "uploadId must not be null");
        boolean result = uploadService.mergeMultipartUpload(objectName, uploadId);

        return new ResponseEntity<>(ImmutableMap.of("success", result), HttpStatus.OK);
    }
}
