package org.example;

import lombok.Builder;

import javax.crypto.Cipher;
import java.security.*;
public class CipRSA {
    String name;
//私钥：
    PrivateKey sk;
//    公钥
    PublicKey pk;

    public CipRSA(String name) throws GeneralSecurityException{
        this.name = name;
        //生成私钥/公钥对
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair kp = kpGen.generateKeyPair();
        this.sk = kp.getPrivate();
        this.pk = kp.getPublic();
    }
//     私钥导出为字节
    public byte[] getPrivateKey(){
        return this.sk.getEncoded();
    }
    public byte[] getPublicKey(){
        return this.pk.getEncoded();
    }
//    用公钥加密
    public byte[] encrypt(byte[] message)throws GeneralSecurityException{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,this.pk);
        return cipher.doFinal(message);
    }
//    用私钥解密
    public byte[] decrypt(byte[] input) throws GeneralSecurityException{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,this.sk);
        return cipher.doFinal(input);
    }
}
