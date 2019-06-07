package io.todoit.core.support.fileupload;

/**
 * @author:zhangd
 * @date:2019/3/18 22:26
 */
public enum FileValidateOption {

    /*
     *  通过各个不同的文件类型的魔术来
     *  判断文件上传类型
     */
    MAGIC_NUMBER,
    /**
     * 文件上传头文件的content-type
     * 属性
     */
    CONTENT_TYPE,

    /**
     * 后缀
     */
    SUFFIX,

    /**
     * 不校验
     */
    NO_VALIDATE;
}
