package me.ponyo.order.utils;

import org.junit.jupiter.api.Test;

class RuleUtilTest {

    @Test
    void isNumberAccount() {
        System.out.println(RuleUtil.isNumberAccount("12345678"));
        System.out.println(RuleUtil.isPhoneNumber("13000000000"));
        System.out.println(RuleUtil.isChain("中"));
    }
}