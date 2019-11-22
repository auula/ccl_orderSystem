package me.ponyo.order.repositorys;


import me.ponyo.order.models.UserInfo;

import java.util.List;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 用户数据访问接口
 * <p>
 * Created with IDEA. Date：2019/11/21 6:16 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
public interface UserRepository {

    int addUser(UserInfo user);

    UserInfo findUserInfoByAccountAndPassword(UserInfo user);

    List<UserInfo> findAllUserInfo();
}
