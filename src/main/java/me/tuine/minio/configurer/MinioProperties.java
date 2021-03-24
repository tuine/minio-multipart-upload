package me.tuine.minio.configurer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tuine
 * @date 2021/3/23
 */
@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class MinioProperties {

    private String endpoint;

    private String accesskey;

    private String secretkey;

    private String bucket;
}
