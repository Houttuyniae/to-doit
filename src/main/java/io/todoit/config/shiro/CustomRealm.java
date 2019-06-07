package io.todoit.config.shiro;

import io.todoit.modules.sys.entity.User;
import io.todoit.modules.sys.service.IShiroService;
import io.todoit.modules.sys.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *   配置shiro
 *   步骤一:创建自定义Realm类
 *      Realm能做的工作主要有以下几个方面：
 *
 *          身份验证（getAuthenticationInfo 方法）验证账户和密码，并返回相关信息
 *
 *          权限获取（getAuthorizationInfo 方法） 获取指定身份的权限，并返回相关信息
 *
 *          令牌支持（supports方法）判断该令牌（Token）是否被支持
 *
 *          令牌有很多种类型，例如：HostAuthenticationToken（主机验证令牌），UsernamePasswordToken（账户密码验证令牌）

 * @author:zhangd
 * @date:2019/4/4 14:46
 */

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    IShiroService shiroService;
    /**
     * 授权信息，主要针对用户权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Set<String> menus = shiroService.selectMenuByUserId(user.getUserId());
        Set<String> roles = shiroService.selectRoleByUserId(user.getUserId());
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(menus);
        return info;
    }

    /**
     * 认证信息，主要针对用户登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*默认有两种token,登陆验证一般都才有 UsernamePasswordToken*/
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = shiroService.getOneByUserName(token.getUsername());
        if(user == null){
            throw new AccountException("帐号密码错误，请重新输入");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),user.getUsername());
    }




    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除指定用户权限缓存
     */
    public void clearAuthorCached(Long userId) {
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(userId, realmName);
        subject.runAs(principals);
        this.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();
    }
}
