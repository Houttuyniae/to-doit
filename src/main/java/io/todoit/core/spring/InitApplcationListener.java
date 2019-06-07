package io.todoit.core.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 * @className:InitApplcationListener
 * @author:zhangd
 * @date:2019/3/11
 * @description:
 * 当ioc容器加载处理完相应的bean之后，也给我们提供了一个机会（先有InitializingBean，
 * 后有ApplicationListener<ContextRefreshedEvent>），可以去做一些自己想做的事。
 * 其实这也就是spring ioc容器给提供的一个扩展的地方。我们可以这样使用这个扩展机制。
 *
 * 。
 */
@Component
public class InitApplcationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //在这里添加定时任务，与其他需要项目启动时加载的任务
    }
}
