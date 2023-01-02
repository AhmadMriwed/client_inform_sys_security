package com;
import controller.Profile;
import model.ClientModel;
import org.junit.Test;
import page.Splash;
import page.Welcome;
import request_response.Msg;
import security.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import static org.junit.Assert.assertThat;


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
                        //  Socket clientSocket = new Socket("localhost",5000);
            // Create the input & output streams to the server
                    //    ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                //     ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            new PGP();
            // Read modify
            // TODO here
            Msg msg= new Msg();
            msg.status=true;
            msg.message="message";
            msg.body=new ClientModel(999333999,"123456","lhma");
            System.out.println(msg.toMap());
            Map<String,Object> map=CBC.encrypt(msg.toMap());
            System.out.println(map);
            map=CBC.decrypt(map);
            System.out.println(map);


//            map=DigitalSignature.genSignature(map);
//            System.out.println(map.get("signature"));
//            System.out.println(DigitalSignature.VerifySignature(map));

//            map=PGP.encrypt(map);
//            System.out.println(map);
//            map=PGP.decrypt(map);
//            System.out.println(map);

            // new Encryption((Serializable) msg.toMap()).run();
           // new Decryption(Symmetric.sealedObject).run();
           //    System.out.println(Symmetric.encryptedKeybyte);
         //    msg.fromMap ((Map<String, Object>) Symmetric.serializable);
              // var s=  Symmetric.sealedObject;
             //  msg.fromMap((Map<String, Object>) Symmetric.sealedObject);

           //new PGP();
            new Splash();
          //  Symmetric.secretKey=Symmetric.generateSymmetricKey();

          // new Encryption((Serializable) msg.toMap()).run();
            //new Decryption(Symmetric.sealedObject).run();
         //   System.out.println(Symmetric.encryptedKeybyte);
           // msg.fromMap ((Map<String, Object>) Symmetric.serializable);
         //   String s=  Symmetric.sealedObject;
         //   msg.fromMap((Map<String, Object>) Symmetric.sealedObject);

            //test AES
            //givenObject_whenEncryptCBC_thenSuccess((Serializable) msg.toMap());
            //givenObject_whenEncryptGCM_thenSuccess((Serializable) msg.toMap());
            //new Welcome();
            /* Send the Message Object to the server */
                        //outToServer.writeObject(msg.toMap());
            /* Retrive the Message Object from server */
//            LinkedList<Message> inFromServerList = new LinkedList<>();
//            Message msgFrmServer = null;
//            inFromServerList = (LinkedList<Message>)inFromServer.readObject();
//            msgFrmServer = inFromServerList.pop();

            /* Print out the recived Message */
//            System.out.println("Message: " + msgFrmServer.getMessage());
//            System.out.println("Index: " + msgFrmServer.getIndex());
//            System.out.println("Average: " + msgFrmServer.getAverage());


                    // clientSocket.close();

        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            System.err.println("Localized: " + e.getLocalizedMessage());
            System.err.println("Stack Trace: " + e.getStackTrace());
        }
    }
//    @Test
//   public static void givenObject_whenEncryptCBC_thenSuccess(Serializable map) throws Exception {
//
//        byte[] salt=Symmetric.getSalt();
//        SecretKey key = Symmetric.getKeyFromPassword("5678",salt);
//        IvParameterSpec ivParameterSpec = Symmetric.generateIv();
//        String algorithm = "AES/CBC/PKCS5Padding";
//        SealedObject sealedObject = Symmetric.encryptCBC(
//                algorithm,  map, key, ivParameterSpec);
//        System.out.println(key);
//        Security.MAC(key,sealedObject);
//        key = Symmetric.getKeyFromPassword("5677",salt);
//        System.out.println(key);
//        Map<String,Object> object = (Map<String, Object>) Symmetric.decryptCBC(
//                algorithm, sealedObject, key, ivParameterSpec);
//        System.out.println(object);
//      //  assertThat(map).isEqualToComparingFieldByField(object);
//    }
//    @Test
//    public static void givenObject_whenEncryptGCM_thenSuccess(Serializable map) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
//            InvalidAlgorithmParameterException, NoSuchPaddingException, IOException,
//            BadPaddingException, ClassNotFoundException, InvalidKeyException {
//
//
//        SecretKey key = Symmetric.generateKey(128);
//        IvParameterSpec ivParameterSpec = Symmetric.generateIv();
//        String algorithm = "AES/GCM/PKCS5Padding";
//        SealedObject sealedObject = Symmetric.encryptGCM(
//                algorithm,  map, key, ivParameterSpec);
//
//        Map<String,Object> object = (Map<String, Object>) Symmetric.decryptGCM(
//                algorithm, sealedObject, key, ivParameterSpec);
//        System.out.println(object);
//        //  assertThat(map).isEqualToComparingFieldByField(object);
//    }
}
