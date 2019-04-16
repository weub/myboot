package com.weub.myboot.repository;

import com.weub.myboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByFirstNameOrLastName(String firstName, String lastName);
}
