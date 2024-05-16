package com.roommake.user.security;

public abstract class LoginUser {
    private int id;
    private String nickname;

    public LoginUser(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}