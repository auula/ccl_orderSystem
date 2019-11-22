package me.ponyo.order.services.impl;

import me.ponyo.order.models.UserInfo;
import me.ponyo.order.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: UserServiceImpl
 * <p>
 * Created with IDEA. Date：2019/11/21 6:25 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public int saveUser(UserInfo user) {
        return 0;
    }

    @Override
    public UserInfo findUserByAccountAndPassword(UserInfo user) {
        return null;
    }

    @Override
    public List<UserInfo> findAllUser() {
        return null;
    }
}
