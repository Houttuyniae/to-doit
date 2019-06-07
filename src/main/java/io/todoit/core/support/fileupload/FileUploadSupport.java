package io.todoit.core.support.fileupload;

import io.todoit.common.expection.FileNotCreateException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * 提供对文件上传数据的解析保存
 * @author:zhangd
 * @date:2019/3/17
 */
public class FileUploadSupport {

    public void save(Path target, MultipartFile uploadFile) throws IOException {
        File file = target.toFile();
        if(!file.exists() && file.createNewFile()){
            throw new FileNotCreateException();
        }
        String contentType = uploadFile.getContentType();
    }

    public void save(Path target, MultipartFile uploadFile, io.todoit.core.support.fileupload.FileValidateOption option) throws IOException {

        switch (option){
            case MAGIC_NUMBER:
                break;
            case CONTENT_TYPE:
                break;
            case SUFFIX:
                break;
            case NO_VALIDATE:
                break;
            default:
        }
    }
}
