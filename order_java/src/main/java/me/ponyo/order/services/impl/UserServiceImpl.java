package me.ponyo.order.services.impl;

import me.ponyo.order.models.UserInfo;
import me.ponyo.order.repositorys.UserRepository;
import me.ponyo.order.services.UserService;
import me.ponyo.order.utils.DateUtil;
import me.ponyo.order.utils.EncryptionUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public int saveUser(UserInfo user) {
        //数据初始化
        if (user.getUserStats() == null ) {
            user.setUserStats(Byte.parseByte("1"));
        }
        if (user.getUpdated() == null ) {
            user.setUpdated(DateUtil.asDateToTimestamp());
        }
        return userRepository.addUser(user.getUserAccount(), EncryptionUtil.encrypted(user.getUserPassword()), user.getUserStats(), user.getUpdated());
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
