package com.example.cs4750;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass( UserFollowingPK.class )
@Table(name = "UserFollowing")
@Data
public class UserFollowing {
    @Id
    @Column(name = "username")
    public String username;

    @Id
    @Column(name = "following_username")
    public String followingUsername;


    public UserFollowing(){}


    public UserFollowing(String username, String followingUsername){
        this.username = username;
        this.followingUsername = followingUsername;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowingUsername() {
        return followingUsername;
    }

    public void setFollowingUsername(String followingUsername) {
        this.followingUsername = followingUsername;
    }
}
