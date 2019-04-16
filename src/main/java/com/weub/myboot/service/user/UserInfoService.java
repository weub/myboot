package com.weub.myboot.service.user;

import com.weub.myboot.model.shiro.UserInfo;

public interface UserInfoService {
    UserInfo findByUserName(String userName);
}
