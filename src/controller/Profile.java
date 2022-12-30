package controller;

import com.Client;
import model.ClientModel;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

public class Profile {
    public  static ClientModel clientModel=new ClientModel();
    public  static PublicKey publicKey;
    public  static int rec_id=0;
    public  static String securityType;
    public  static Map<String, PublicKey> MapPublicKey;
    public  static PrivateKey privateKey;

}
