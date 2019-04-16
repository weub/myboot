package com.weub.myboot.repository.user;

import com.weub.myboot.model.shiro.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /**
     * 查找用户信息
     * @param userName 用户名
     * @return
     */
    UserInfo findByUsername(String userName);
}
