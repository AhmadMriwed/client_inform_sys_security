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

public class Encryption {

   static Serializable serializable;
    public Encryption(Serializable serializable){
        this.serializable=serializable;
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

    public  void run()
            throws IOException, GeneralSecurityException, Exception{

       // Encryption startEnc = new Encryption();

        Key secretKey =getSecretKey(Symmetric.secretKey,"AES");
        //TODO
        PublicKey publicKey = getPublic(Profile.publicKey,"RSA");
        Symmetric.encryptedKeybyte=encryptKey(publicKey,secretKey,"RSA");
        Symmetric.sealedObject=encryptData(serializable,secretKey,"AES");

    }

}
