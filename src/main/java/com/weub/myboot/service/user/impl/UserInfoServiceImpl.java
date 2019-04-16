package com.weub.myboot.service.user.impl;

import com.weub.myboot.model.shiro.UserInfo;
import com.weub.myboot.repository.user.UserInfoRepository;
import com.weub.myboot.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUserName(String userName) {
        return userInfoRepository.findByUsername(userName);
    }
}
