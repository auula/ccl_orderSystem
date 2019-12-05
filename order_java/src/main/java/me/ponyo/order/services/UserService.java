package me.ponyo.order.services;

import me.ponyo.order.models.SysInfo;
import me.ponyo.order.models.UserInfo;

public interface UserService  {

    int register(UserInfo user);

    UserInfo login(UserInfo user);

    UserInfo checkAccount(String account);

    SysInfo  getSysInfo();
}
