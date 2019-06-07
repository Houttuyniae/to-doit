package io.todoit.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.todoit.modules.sys.entity.User;
import io.todoit.modules.sys.mapper.UserMapper;
import io.todoit.modules.sys.service.IShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author:zhangd
 * @date:2019/4/4 16:05
 */
@Service
public class ShiroServiceImpl implements IShiroService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getOneByUserName(String userName) {
        Map<String,Object> param = new HashMap<String,Object>(2);
        param.put("username", userName);
        return userMapper.selectOne(new QueryWrapper<User>().allEq(param));
    }

    @Override
    public Set<String> selectRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Set<String> selectMenuByUserId(Long userId) {
        return null;
    }
}
