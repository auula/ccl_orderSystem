package me.ponyo.order.services;

import me.ponyo.order.models.UserInfo;

import java.util.List;

public interface UserService  {

    int saveUser(UserInfo user);

    UserInfo findUserByAccountAndPassword(UserInfo user);

    List<UserInfo> findAllUser();
}
