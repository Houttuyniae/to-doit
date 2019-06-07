package io.todoit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @className:UploadConfig
 * @author:zhangd
 * @date:2019/3/16
 * @description:TODO
 */
@Component
@ConfigurationProperties(prefix = "servlet.common.multipart")
public class UploadConfig {

    private Long maxUploadSize;
    private Integer maxInMemorySize;
    private Long maxUploadSizePerFile;
    private String defaultEncoding;

    @Bean
    public CommonsMultipartResolver getCommonsMultipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(maxUploadSize);
        multipartResolver.setMaxInMemorySize(maxInMemorySize);
        multipartResolver.setMaxUploadSizePerFile(maxUploadSizePerFile);
        multipartResolver.setDefaultEncoding(defaultEncoding);
        return multipartResolver;
    }

    public Long getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(Long maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public Integer getMaxInMemorySize() {
        return maxInMemorySize;
    }

    public void setMaxInMemorySize(Integer maxInMemorySize) {
        this.maxInMemorySize = maxInMemorySize;
    }

    public Long getMaxUploadSizePerFile() {
        return maxUploadSizePerFile;
    }

    public void setMaxUploadSizePerFile(Long maxUploadSizePerFile) {
        this.maxUploadSizePerFile = maxUploadSizePerFile;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }
}
