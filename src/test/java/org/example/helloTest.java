package org.example;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.*;

import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;

import javax.crypto.spec.*;


public class helloTest {
    private static final Logger log = LoggerFactory.getLogger(helloTest.class);

    @Test
    public void  test1() throws NoSuchAlgorithmException, InvalidKeyException {

        BigInteger n = new BigInteger("999999").pow(99);
        float f = n.floatValue();
//        System.out.println(f);
        BigDecimal d1 = new BigDecimal("123.456789");
        BigDecimal d2 = d1.setScale(4, RoundingMode.HALF_UP); // 四舍五入，123.4568
        BigDecimal d3 = d1.setScale(4, RoundingMode.DOWN); // 直接截断，123.4567
        log.info(d2.toString());
        log.info(d3.toString());

        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);

        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
        String b64encoded = Base64.getEncoder().encodeToString(input);
//        log.info(b64encoded);
        System.out.println(b64encoded);
        byte[] decode = Base64.getDecoder().decode(b64encoded);
        System.out.println(Arrays.toString(decode));

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("Hello".getBytes(StandardCharsets.UTF_8));
        md.update("World".getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        log.info(Arrays.toString(digest));
        System.out.println(Arrays.toString(digest));
        System.out.println(new BigInteger(1, digest).toString(16));


        //Hmac加密算法：：：编码
        KeyGenerator kg = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = kg.generateKey();
        //打印随机生成的key
        byte[] skey = key.getEncoded();
        System.out.println(Arrays.toString(skey));
        System.out.println("key:"+new BigInteger(1,skey).toString(16));
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update("Hello".getBytes(StandardCharsets.UTF_8));
        System.out.println("Hello转换的utf8字节："+ Arrays.toString("Hello".getBytes(StandardCharsets.UTF_8)));

        byte[] result = mac.doFinal();
        System.out.println("hmacmd5:"+new BigInteger(1,result).toString(16));

//      这里的hkey可以是另一个待验证的key字节，用于验证摘要算法的长度是否一致。
        SecretKey hkey = new SecretKeySpec(skey,"HmacMD5");//通过这个恢复secretKey
        Mac hmac = Mac.getInstance("HmacMD5");
        hmac.init(hkey);
        mac.update("Hello".getBytes(StandardCharsets.UTF_8));
        byte[] hresult = mac.doFinal();
        System.out.println(new BigInteger(1,hresult).toString(16));

    }
//
//    根据算法名称/工作模式/填充模式获取Cipher实例；
//    根据算法名称初始化一个SecretKey实例，密钥必须是指定长度；
//    使用SecretKey初始化Cipher实例，并设置加密或解密模式；
//    传入明文或密文，获得密文或明文。
    @Test
    public void testcrypo() throws GeneralSecurityException {
        //对称加密
//        AES 算法加密，使用ECB模式加密解密



//        原文
        String message = "Hello world";
        System.out.println("Message:"+ message);
//        128位密钥=16 bytes key
        byte[] key = "1234567890abcdef".getBytes(StandardCharsets.UTF_8);
//        加密：
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        byte[] encryped = encrypt(key,data);
        System.out.println("Encrypted"+Base64.getEncoder().encodeToString(encryped));
//        解密
        byte[] decrypted = decrypt(key,encryped);
        System.out.println("Decrypted:"+new String(decrypted,StandardCharsets.UTF_8));
    }
    public static byte[] encrypt(byte[] key,byte[] input) throws GeneralSecurityException{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key,"AES");
        cipher.init(Cipher.ENCRYPT_MODE,keySpec);
        return cipher.doFinal(input);
    }
    public static byte[] decrypt(byte[] key,byte[] input) throws GeneralSecurityException{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key,"AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec);
        return cipher.doFinal(input);
    }

    @Test
    public void testRSA() throws GeneralSecurityException {
        //非对称加密算法RSA
//        明文
        byte[] plain = "hello,encrypt use RSA".getBytes(StandardCharsets.UTF_8);
//        创建密钥对
        CipRSA cipRSA = new CipRSA("BOB");
//        用BoB公钥加密
        byte[] encrypt = cipRSA.encrypt(plain);

//        用BoB私钥解密
        byte[] decrypt = cipRSA.decrypt(encrypt);
        log.info("有没有用");
        System.out.println(new String(decrypt,StandardCharsets.UTF_8));


//        通过如下方式获取公钥或者私钥，以及从byte[]恢复公钥或者私钥
        byte[] privateKey = cipRSA.getPrivateKey();
        byte[] publicKey = cipRSA.getPublicKey();
        KeyFactory kf = KeyFactory.getInstance("RSA");
//        恢复公钥
        X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(publicKey);
        PublicKey pk = kf.generatePublic(pkSpec);

//        恢复私钥
        PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey sk = kf.generatePrivate(skSpec);
        System.out.println(sk.toString());
    }

    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }


}
