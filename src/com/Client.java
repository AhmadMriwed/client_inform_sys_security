package com;
import model.ClientModel;
import request_response.Msg;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Client {


    public static void main(String[] args) throws IOException {

//        while (true){
//            Socket s = new Socket("localhost",4999);
//            PrintWriter pr = new PrintWriter(s.getOutputStream());
//            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
//            System.out.print("Enter a string: ");
//            String text= sc.nextLine();              //reads string
//            pr.println(text);
//            pr.flush();
//            InputStreamReader in = new InputStreamReader(s.getInputStream());
//            BufferedReader bf =  new BufferedReader(in);
//
//            String str = bf.readLine();
//            System.out.println("server :"+ str );
//
//        }

        ///    Socket s = new Socket("localhost", 4999);

//            PrintWriter pr = new PrintWriter(s.getOutputStream());
//            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
//            System.out.print("Enter a string: ");
//            String text= sc.nextLine();              //reads string
//            pr.println(text);
//            pr.flush();

      ///      InputStreamReader in = new InputStreamReader(s.getInputStream());
       ///     BufferedReader bf =  new BufferedReader(in);

        ///    String str = bf.readLine();
          ///  System.out.println("client :"+ str );



        try {
            // Create the socket
            Socket clientSocket = new Socket("localhost",5000);
            // Create the input & output streams to the server
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            // Read modify
            // TODO here
            Msg msg= new Msg();
            msg.status=true;
            msg.message="message";
            msg.body=new ClientModel(999333999,"123456","lhma");
            System.out.println(msg.toMap());
            /* Send the Message Object to the server */
            outToServer.writeObject(msg.toMap());

            /* Retrive the Message Object from server */
//            LinkedList<Message> inFromServerList = new LinkedList<>();
//            Message msgFrmServer = null;
//            inFromServerList = (LinkedList<Message>)inFromServer.readObject();
//            msgFrmServer = inFromServerList.pop();

            /* Print out the recived Message */
//            System.out.println("Message: " + msgFrmServer.getMessage());
//            System.out.println("Index: " + msgFrmServer.getIndex());
//            System.out.println("Average: " + msgFrmServer.getAverage());


            clientSocket.close();

        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            System.err.println("Localized: " + e.getLocalizedMessage());
            System.err.println("Stack Trace: " + e.getStackTrace());
        }
    }
}
