package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    // add code for the "/hello" endpoint

    @GetMapping("/hello")
    public String sayHello() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
        String test = "hellonghia";
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//        SecureRandom random = SecureRandom.getInstanceStrong();
        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        String base64PublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        PrivateKey privateKey = keyPair.getPrivate();
        String base64PrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println("Public Key: " + base64PublicKey);
        System.out.println("Private Key: " + base64PrivateKey);

        byte[] data = test.getBytes("UTF-8");

        Signature rsaSignature = Signature.getInstance("SHA256withRSA");
        rsaSignature.initSign(privateKey);
        rsaSignature.update(data);
        byte[] digitalSignature = rsaSignature.sign();

        String value = Base64.getEncoder().encodeToString(digitalSignature);
        System.out.println("value: " + value);

        Signature rsaSignature1 = Signature.getInstance("SHA256withRSA");
        rsaSignature1.initVerify(publicKey);

        rsaSignature1.update(data); // 'data' là dữ liệu cần xác thực

        boolean verified = rsaSignature1.verify(digitalSignature);
        System.out.println(verified);

        return "Hello World!";
    }
}
