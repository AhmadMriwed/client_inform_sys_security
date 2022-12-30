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
    public static int portChat=5001;
    public static String host="localhost";


    public static Msg sendRequest(Msg msg) {
       return  sendRequest(msg,true);
    }
    public static Msg sendRequest(Msg msg,boolean status) {
        msg.header.setRec_id(Profile.rec_id);
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
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.writeObject(map);
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            var temp=inFromServer.readObject();
            //TODO here 2
            map= (Map<String, Object>) temp;
            result.fromMap(map);
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
