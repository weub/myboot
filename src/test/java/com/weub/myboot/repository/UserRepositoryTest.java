package com.weub.myboot.repository;

import com.weub.myboot.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        /*User user1 = new User();
        user1.setUserName("imweub001");
        user1.setPassword("xxxx1");
        user1.setNickName("weub1");
        user1.setFirstName("weub1");
        user1.setLastName("banana1");
        user1.setCreateDateTime(new Date());

        User user2 = new User();
        user2.setUserName("imweub002");
        user2.setPassword("xxxx2");
        user2.setNickName("weub2");
        user2.setFirstName("weub2");
        user2.setLastName("banana2");
        user2.setCreateDateTime(new Date());

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userRepository.saveAll(userList);

        Assert.assertEquals("banana1", userRepository.findByUserName("imweub001").getLastName());*/
    }
}