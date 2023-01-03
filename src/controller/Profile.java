package controller;

import com.Client;
import model.ClientModel;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Profile {
    public  static ClientModel clientModel=new ClientModel();
    public  static PublicKey publicKey;
    public  static int rec_id=0;
    public  static String securityType;
    public static String pathPvKey="S:/Users/hp/client/src/security/PvKey.dat";
    public static String pathPbKey="S:/Users/hp/client/src/security/PbKey.dat";
    public static String pathUniqueID="C:/Users/Leen/IdeaProjects/client_inform_sys_security/src/security/uniqueID.json";
    public  static Map<String, PublicKey> mapPublicKey=new HashMap<>();
    public  static PrivateKey privateKey;
    public  static String uniqueID;
    public  static String uniqueIDServer;

}
