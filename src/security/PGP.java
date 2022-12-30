package security;

import controller.Profile;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class PGP {
    public PGP() throws NoSuchProviderException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException {
        GenerateKeys generateKeys = new GenerateKeys();
        generateKeys.createKeys();
        Profile.privateKey = generateKeys.getPrivateKey();
        Profile.publicKey = generateKeys.getPublicKey();
    }
}
