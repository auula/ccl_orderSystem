package me.ponyo.order.services;

import me.ponyo.order.models.UserInfo;

import java.util.List;

public interface UserService  {

    int register(UserInfo user);

    UserInfo login(UserInfo user);

    UserInfo checkAccount(String account);

    List<UserInfo> findAllUser();
}
