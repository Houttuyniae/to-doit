package io.todoit.config.shiro.app;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author:zhangd
 * @date:2019/4/6 21:10
 */

/**
 * app token
 * app鉴权认证
 */
public class AppToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
