package me.ponyo.order.utils;

import me.ponyo.order.models.BaseResult;
import me.ponyo.order.models.UserInfo;
import org.apache.logging.log4j.util.Strings;

import java.util.regex.Pattern;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 规则验证  验证表单规则
 * <p>
 * Created with IDEA. Date：2019/11/22 5:51 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
public class RuleUtil {
    //8位数字
    private  static final String ACCOUNT_NUMBER_RULE = "^\\d{8,}$";
    //密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
    private  static final String PASSWORD_RULE = "^[a-zA-Z]\\w{5,17}$";
    //邮箱
    private  static final String EMAIl_RULE = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //手机号码
    private  static final String PHONE_NUMBER_RULE = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    //强度密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间)
    private  static final String PASSWORD_STRENGTH_RULE = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$";
    //IP地址
    private  static final String IP_ADDRESS_RULE = "((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))";
    //匹配域名
    private  static final String DOMAIN_RULE= "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";
    //匹配文件后缀是否为图片格式
    private  static final String IS_IMAGE_RULE = "/^\\[(.+?)\\.(png|jpg|gif)\\]$/";
    //包含中文
    private  static final String IS_CHAIN_RULE = "[\\u4e00-\\u9fa5]";

    //是否为8位数数字账户
    public static Boolean isNumberAccount(String str){
        return Pattern.matches(RuleUtil.ACCOUNT_NUMBER_RULE,str);
    }
    //是否为邮箱
    public static Boolean isEmail(String mail){
        return Pattern.matches(RuleUtil.EMAIl_RULE,mail);
    }
    //是否为密码
    public static Boolean isPassword(String pass){
        return Pattern.matches(RuleUtil.PASSWORD_RULE,pass);
    }
    //是否手机号
    public static Boolean isPhoneNumber(String n){
        return Pattern.matches(RuleUtil.PHONE_NUMBER_RULE, n);
    }
    //密码强度
    public static Boolean asPassStrength(String pass){
        return Pattern.matches(RuleUtil.PASSWORD_STRENGTH_RULE, pass);
    }
    //是否为ip地址
    public static Boolean isIpAddress(String ip){
        return Pattern.matches(RuleUtil.IP_ADDRESS_RULE, ip);
    }
    //是否为域名
    public static Boolean isDomain(String domain){
        return Pattern.matches(RuleUtil.DOMAIN_RULE, domain);
    }
    //是否是图片
    public static Boolean isImage(String imageName){
        return Pattern.matches(RuleUtil.IS_IMAGE_RULE, imageName);
    }
    //是否为中文
    public static Boolean isChain(String str){
        return Pattern.matches(RuleUtil.IS_CHAIN_RULE, str);
    }


//    public static BaseResult asRuleUserForm(UserInfo user) {
//        if (Strings.isBlank(user.getUserPassword())) {
//            return new BaseResult().build(500, "密码不能空!");
//        }
//        if (Strings.isBlank(user.getUserAccount())) {
//            return new BaseResult().build(500, "账号不能空!");
//        }
//        if (!RuleUtil.isNumberAccount(user.getUserAccount())) {
//            return new BaseResult().build(500, "账号不符合规范!长度8位数字!");
//        }
//        if (!RuleUtil.isPassword(user.getUserPassword())) {
//            return new BaseResult().build(500, "密码不符合规范!以字母开头，长度在6~18之间，只能包含字母、数字和下划线!");
//        }
//
//    }
}
