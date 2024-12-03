package com.example.cs4750;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, UserFollowingPK> {
    List<UserFollowing> findByUsername(String username);
}
