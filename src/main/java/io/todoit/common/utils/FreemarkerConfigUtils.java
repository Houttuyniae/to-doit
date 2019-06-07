package io.todoit.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author:zhangd
 * @date:2019/4/10 22:35
 */
public class FreemarkerConfigUtils {

    private static Configuration configuration;

    public static Template getTemplate(Path path){
        try {
            if(configuration == null){
                configuration = new Configuration(Configuration.VERSION_2_3_28);
                configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            }
            configuration.setDirectoryForTemplateLoading(path.getParent().toFile());
            return configuration.getTemplate(path.toFile().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
