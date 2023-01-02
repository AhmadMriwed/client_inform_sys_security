package controller;

import model.MessageModel;
import page.Splash;
import request_response.Msg;
import security.PGP;
import security.Security;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

public class Request {
    public static int port=5000;
    public static int portChat=5001;
    public static String host="localhost";


    public static Msg sendRequest(Msg msg) {
       return  sendRequest(msg,true);
    }
    public static Msg sendRequest(Msg msg,boolean status) {
        msg.header.setRec_id(Profile.rec_id);
        msg.header.setUniqueID(Profile.uniqueID);
        msg.status=status;
        Map<String,Object> map=msg.toMap();
        Msg result = new Msg();
        try {
            Socket clientSocket;
            if(msg.header.getService().contains(Service.Chat.name()))
                clientSocket = new Socket(host,portChat);
            else
                 clientSocket = new Socket(host,port);
            //TODO here 1
            map= Security.Encryption(Profile.securityType,map);

            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.writeObject(map);

            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            var temp=inFromServer.readObject();

            map= (Map<String, Object>) temp;
            if((boolean)map.get("status")){
                //TODO here 2
                map= Security.Decryption((String) map.get("securityType"),map);

                result.fromMap(map);
            }
            else {
                map= errorHandler(map);
            }
            outToServer.close();
            inFromServer.close();
            clientSocket.close();
        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            System.err.println("Localized: " + e.getLocalizedMessage());
            System.err.println("Stack Trace: " + e.getStackTrace());
        }
        return result;
    }
    public static Map errorHandler(Map map) {
        switch ((String) map.get("message")){
            case "Disconnect":
                try {
                    System.out.println("Disconnect");
                    new Splash();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                }
            default:
                System.out.println("\n ------------ "+map.get("message")+" ------------ \n");

        }
        return map;
    }
}
