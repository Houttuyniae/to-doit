package io.todoit.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.todoit.modules.sys.entity.User;
import io.todoit.modules.sys.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Year;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author:zhangd
 * @date:2019/4/3 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @Test
    public void getById() {
//        User admin = userService.getOneByUserName("admin");
//        assertNotNull(admin);
    }
}