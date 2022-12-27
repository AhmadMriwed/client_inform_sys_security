package controller;

import model.MessageModel;
import request_response.Msg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class Request {
    public static int port=5000;
    public static String host="localhost";

    public static Msg sendRequest(Msg msg) {
       return  sendRequest(msg,true);
    }
    public static Msg sendRequest(Msg msg,boolean status) {
        msg.header.setRec_id(Profile.rec_id);
        msg.status=true;
        Map<String,Object> map=msg.toMap();
        Msg result = new Msg();
        try {
            Socket clientSocket = new Socket(host,port);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.writeObject(map);
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            result.fromMap((Map<String, Object>) inFromServer.readObject());

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
}
