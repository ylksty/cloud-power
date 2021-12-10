package com.bjpowernode;

import org.jasypt.encryption.StringEncryptor;

//@SpringBootTest
public class ApplicationTests {

    /**
     * 注入spring ioc容器中的bean对象
     */
    //@Autowired
    private StringEncryptor stringEncryptor;

    //@Test
    public void encryptPwd() {
        //加密
        String username = stringEncryptor.encrypt("root");
        System.out.println("加密username: " + username);

        String decUsername = stringEncryptor.decrypt(username);
        System.out.println("解密username: " + decUsername);

        //加密
        String password = stringEncryptor.encrypt("123456");
        System.out.println("password: " + password);
        String decPassword = stringEncryptor.decrypt(password);
        System.out.println("解密password: " + decPassword);
    }

    //@Test
    void contextLoads() {
    }
}