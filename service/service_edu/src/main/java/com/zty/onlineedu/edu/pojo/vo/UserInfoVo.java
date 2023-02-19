package com.zty.onlineedu.edu.pojo.vo;

/**
 * @Author zty
 * @Date 2023/1/4 21:37
 */

public class UserInfoVo {
    private String username;
    private String password;

    public UserInfoVo() {
    }

    public UserInfoVo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
