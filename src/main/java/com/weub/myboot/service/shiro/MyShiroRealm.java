package com.weub.myboot.service.shiro;

import com.weub.myboot.model.shiro.SysPermission;
import com.weub.myboot.model.shiro.SysRole;
import com.weub.myboot.model.shiro.UserInfo;
import com.weub.myboot.service.user.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        if (StringUtils.clean(userName) == null) {
            return null;
        }
        // userName非空判断?
        // ......

        UserInfo userInfo = userInfoService.findByUserName(userName);
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userInfo,   // 存放一个userInfo
                userInfo.getPassword(), // 这里是已经经过加密的
                ByteSource.Util.bytes(userInfo.getSalt()),
                userInfo.getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        for (SysRole role : userInfo.getRoleList()) {
            simpleAuthorizationInfo.addRole(role.getRole());
            for (SysPermission permission : role.getPermissionList()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }

        return simpleAuthorizationInfo;
    }
}
