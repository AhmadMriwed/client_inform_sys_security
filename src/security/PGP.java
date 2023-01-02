package security;

import controller.Profile;
import controller.Request;
import controller.Service;
import model.ClientModel;
import page.Page;
import request_response.Header;
import request_response.Msg;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PGP {
    public PGP() throws NoSuchProviderException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException {
        GenerateKeys generateKeys = new GenerateKeys();
        generateKeys.createKeys();
        String uniqueID = UUID.randomUUID().toString();
        if(!Json.containsKey("uniqueID",Profile.pathUniqueID)){
            Profile.uniqueID=uniqueID;
            Json.put("uniqueID",uniqueID,Profile.pathUniqueID);
        }else {
            Profile.uniqueID= (String) Json.get("uniqueID",Profile.pathUniqueID);
        }
        Profile.privateKey = generateKeys.getPrivateKey();
        Profile.publicKey = generateKeys.getPublicKey();
        Profile.mapPublicKey.put(Profile.uniqueID,Profile.publicKey);
        handshaking();
    }
    static public Msg handshaking(){
        while (true){
            System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("||                Init PGP                   ||");
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
            Msg msg=new Msg();
            msg.header.setService(Service.getPublicKey.name());
            msg.header.setPublicKey(Profile.publicKey);

            msg= Request.sendRequest(msg);
            if (msg.status){
                System.out.println("able to connect :)");
                Profile.mapPublicKey.put(msg.header.getUniqueID(),msg.header.getPublicKey());
                Profile.uniqueIDServer=msg.header.getUniqueID();
                return msg;
            }else {
                System.out.println("Unable to connect, try again? [y]es or [n]o :");
                String choose = Page.sc.next();
                if(choose.toLowerCase().contains("no")||choose.toLowerCase().contains("n"))
                    return msg;
            }

        }

    }
    public static Map<String,Object> encrypt(Map<String,Object> map)  {

        try {
            SecretKey secretKey = Symmetric.generateSymmetricKey();
           map =new Encryption((Serializable) map.get("body"),Profile.mapPublicKey.get(Profile.uniqueIDServer),secretKey).run(map);

        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    public static Map<String,Object> decrypt(Map<String,Object> map)  {
        Header header=new Header().fromMap((Map<String, Object>) map.get("header"));
        try {
            map=new Decryption((SealedObject) map.get("body"),header.getSecretKey()).run(map);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
