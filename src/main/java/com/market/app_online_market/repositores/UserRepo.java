package com.market.app_online_market.repositores;

import com.market.app_online_market.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {
//    @Query(value = "select user_name from users where users.user_name = ?1 "
//            , nativeQuery = true)
    Optional<Users> findByUser_name(String userName);
}
