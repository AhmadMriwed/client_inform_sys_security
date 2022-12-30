package security;

import controller.Profile;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Decryption {

    static SealedObject sealedObject;
    public Decryption(SealedObject sealedObject){
        this.sealedObject=sealedObject;
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
    private static SecretKeySpec getSecretKey(byte[] keyBytes, String algorithm) throws IOException {

        return new SecretKeySpec(keyBytes, algorithm);

    }

    private static Serializable decryptData(SealedObject sealedObject, Key secretKey, String cipherAlgorithm)
            throws IOException, GeneralSecurityException, ClassNotFoundException {
         Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        Serializable unsealObject = (Serializable) sealedObject.getObject(cipher);
        return unsealObject;
    }

    private static byte[] decryptKey(PrivateKey key,Key secretKey, String cipherAlgorithm)
            throws IOException, GeneralSecurityException{
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(secretKey.getEncoded());


    }

    public  void run()
            throws IOException, GeneralSecurityException, Exception{

       // Encryption startEnc = new Encryption();

        Symmetric.encryptedKey =getSecretKey(Symmetric.encryptedKeybyte,"AES");

        //TODO
        PrivateKey privateKey = getPrivate(Profile.privateKey,"RSA");
        Symmetric.secretKey=getSecretKey(decryptKey(privateKey,Symmetric.encryptedKey,"RSA"),"AES");
        Symmetric.serializable=decryptData(sealedObject,Symmetric.secretKey,"AES");
    }

}
