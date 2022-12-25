package com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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

            Socket s = new Socket("localhost", 4999);

//            PrintWriter pr = new PrintWriter(s.getOutputStream());
//            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
//            System.out.print("Enter a string: ");
//            String text= sc.nextLine();              //reads string
//            pr.println(text);
//            pr.flush();

            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf =  new BufferedReader(in);

            String str = bf.readLine();
            System.out.println("client :"+ str );




    }
}
