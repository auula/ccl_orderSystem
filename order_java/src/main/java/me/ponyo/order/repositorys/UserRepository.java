package me.ponyo.order.repositorys;


import me.ponyo.order.models.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
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
@Mapper
public interface UserRepository {

    //int insert(@Param("username")String username,@Param("password")String password);

    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("INSERT INTO user_info(user_account,user_password,user_stats,updated) VALUES (#{userAccount},#{userPassword},#{userStats},#{updated})")
    int addUser(@Param("userAccount") String userAccount,@Param("userPassword") String userPassword,@Param("userStats") byte userStats,@Param("updated") Timestamp updated);

    UserInfo findUserInfoByAccountAndPassword(UserInfo user);

    List<UserInfo> findAllUserInfo();

}
