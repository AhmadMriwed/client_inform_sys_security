package security;

import controller.Profile;
import request_response.Header;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

public class Encryption {

   static Serializable serializable;
    static PublicKey publicKey;
    static SecretKey secretKey;
    public Encryption(Serializable serializable,PublicKey publicKey, SecretKey secretKey){
        this.serializable=serializable;
        this.publicKey=publicKey;
        this.secretKey=secretKey;
    }
    private static PrivateKey getPrivate(Key key, String algorithm) throws Exception {

        byte[] keyBytes =key.getEncoded();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePrivate(spec);
    }

    private static PublicKey getPublic(Key key, String algorithm) throws Exception {

        byte[] keyBytes = key.getEncoded();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        return kf.generatePublic(spec);

    }

    private static SecretKeySpec getSecretKey(Key key, String algorithm) throws IOException {

        byte[] keyBytes = key.getEncoded();
        return new SecretKeySpec(keyBytes, algorithm);

    }

    private static SealedObject encryptData(Serializable serializable, Key secretKey, String cipherAlgorithm)
            throws IOException, GeneralSecurityException{
         Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        SealedObject sealedObject = new SealedObject( serializable, cipher);
        return sealedObject;
    }

    private static byte[] encryptKey(PublicKey key,Key secretKey, String cipherAlgorithm)
            throws IOException, GeneralSecurityException{
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(secretKey.getEncoded());


    }
    private static byte[] decryptKey(PrivateKey key,Key secretKey, String cipherAlgorithm)
            throws IOException, GeneralSecurityException{
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(secretKey.getEncoded());


    }
    private static SecretKeySpec getSecretKey(byte[] keyBytes, String algorithm) throws IOException {

        return new SecretKeySpec(keyBytes, algorithm);

    }
    public  Map<String, Object> run(Map<String,Object> map)
            throws IOException, GeneralSecurityException, Exception{

       // Encryption startEnc = new Encryption();

        Key secretKey1 =getSecretKey(secretKey,"AES");

        //TODO
        PublicKey publicKey1 = getPublic(publicKey,"RSA");
        byte[] encryptedKeybyte=encryptKey(publicKey1,secretKey1,"RSA");
        SealedObject sealedObject=encryptData(serializable,secretKey1,"AES");
        Header header=new Header().fromMap((Map<String, Object>) map.get("header"));
        header.setSecretKey(encryptedKeybyte);
        map.put("body",sealedObject);
        map.put("header", header.toMap());
        return map;

    }

}
/*
package Encryption;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class DigitalSignature {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String HMAC_SHA512 = "HmacSHA512";
    private static final int RANDOM_BYTES_LENGTH = 16;
    private static final String MAC_ALGORITHM = "HMACSHA256";
    private static final String initVector = "wxz-yvWG1VhtlT_R";
    private static final String STRING_ENCODING = "ISO_8859_1";
    PGP pgp=new PGP();
    public  String SignDoc(PrivateKey pvtKey, String Data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        System.out.println(">>>>>>>> Start Add Signature  ");
        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withRSA");

        //Initialize the signature
        sign.initSign(pvtKey);

        byte[] bytes = Data.getBytes("UTF-8");

        //Adding data to the signature
        sign.update(bytes);

        //Calculating the signature
        return Base64.getEncoder().encodeToString(sign.sign());
    }


    public  boolean VerifySignature(PublicKey pubKey, String SignedData, String Data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        System.out.println(">>>>>>>> Start Verify Signature  ");
        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withRSA");

        //Initializing the signature
        sign.initVerify(pubKey);

        //Update the data to be verified
        sign.update(Data.getBytes("UTF-8"));

        //Verify the signature
        return sign.verify(Base64.getDecoder().decode(SignedData));
    }




    public  String  encryption( String data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException, SignatureException {
        System.out.println(">>>>>>>> start encryption with RSA");
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        return encodedMessage;
    }

    public  String  decryption(String EncryptionMyVector, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IOException, SignatureException {
        // System.out.println(">>>>>>>> start decryption  with RSA");
        byte[] encodedMessage = Base64.getDecoder().decode(EncryptionMyVector);
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encodedMessage);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        return decryptedMessage;
    }


//


}

*/