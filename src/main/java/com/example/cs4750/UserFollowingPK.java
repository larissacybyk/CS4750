package com.example.cs4750;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@NoArgsConstructor
@Getter
public class UserFollowingPK implements Serializable {

    @Column(name = "username")
    public String username;


    @Column(name = "following_username")
    public String followingUsername;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFollowingPK userPK)) return false;
        return username.equals(userPK.username) && followingUsername.equals(userPK.followingUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, followingUsername);
    }
}