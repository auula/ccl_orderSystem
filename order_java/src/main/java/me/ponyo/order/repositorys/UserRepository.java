package me.ponyo.order.repositorys;


import me.ponyo.order.models.UserInfo;
import org.apache.ibatis.annotations.*;

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

    //添加用户
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("INSERT INTO user_info(user_account,user_password,user_stats,updated) VALUES (#{userAccount},#{userPassword},#{userStats},#{updated})")
    int addUser(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword, @Param("userStats") byte userStats, @Param("updated") Timestamp updated);

    //通过密码和账户查询用户
    @Select("SELECT * FROM user_info WHERE user_account = #{userAccount} AND user_password = #{userPassword}")
    UserInfo findUserInfoByAccountAndPassword(UserInfo userInfo);

    ////通过账户查询用户
    @Select("SELECT * FROM user_info WHERE user_account = #{userAccount}")
    UserInfo findUserInfoByAccount(@Param("userAccount") String userAccount);

    List<UserInfo> findAllUserInfo();


}
