package com.wuxi;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 青铜
 * @create: 2018-08-14
 **/
public class RSAtest {

    public static void main(String[] args) throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);  //初始化RSA1024安全些

        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();      // 公钥

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  // 私钥

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("PUBLIC_KEY", publicKey);
        keyMap.put("PRIVATE_KEY", privateKey);
        System.out.println(publicKey);
        System.out.println("-----------");
        System.out.println(privateKey);
    }
}
