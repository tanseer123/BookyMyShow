package com.example.bookmyshow.repositories;


import com.example.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //select * from user where id=10
    // @Query for custom query
    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findAllByEmail(String email);

    @Override
    User save(User user);
}
