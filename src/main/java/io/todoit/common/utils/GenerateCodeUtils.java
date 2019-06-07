package io.todoit.common.utils;

import freemarker.template.Template;
import io.todoit.modules.sys.entity.User;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author:zhangd
 * @date:2019/4/10 23:08
 */
public class GenerateCodeUtils {

    public static void genBuilderCode(){

    }

    public static void main(String[] args) {
//        Path path = Paths.get("./src/main/resources/template/builder.ftl");
//        Template template = FreemarkerConfigUtils.getTemplate(path);
//        if(template == null){
//            return;
//        }
//        try( BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("./q.txt"))){
//            HashMap<Object, Object> map = new HashMap<>();
//            map.put("name", "雷炜婧");
//            template.process(map,bufferedWriter);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println(template.getName());


        Class<User> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            System.out.println(field.getName());
        }

    }
}
