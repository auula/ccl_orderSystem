package me.ponyo.order.services.impl;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public int register(UserInfo user) {
        //数据初始化
        if (user.getUserStats() == null ) {
            user.setUserStats(Byte.parseByte("1"));
        }
        if (user.getUpdated() == null ) {
            user.setUpdated(DateUtil.asDateToTimestamp());
        }
        log.info("registerDATA:"+user.toString());
        return userRepository.addUser(user.getUserAccount(), EncryptionUtil.encrypted(user.getUserPassword()), user.getUserStats(), user.getUpdated());
    }

    @Override
    public UserInfo login(UserInfo user) {
        // userInfo notNull
        if(user.getUserAccount() == null || user.getUserPassword() == null){
            return null;
        }
        log.info("loginDATA:"+user.toString());
        return userRepository.findUserInfoByAccountAndPassword(user);
    }

    @Override
    public UserInfo checkAccount(String account) {
        if (Strings.isBlank(account)) {
            return null;
        }
        return userRepository.findUserInfoByAccount(account);
    }

    @Override
    public List<UserInfo> findAllUser() {
        return null;
    }
}
