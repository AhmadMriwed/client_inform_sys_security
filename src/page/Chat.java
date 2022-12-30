package page;

import controller.Profile;
import controller.Request;
import controller.Service;
import model.ClientModel;
import model.MessageModel;
import model.Model;
import model.NumberModel;
import request_response.Msg;

import java.util.ArrayList;
import java.util.List;

class RecMessage extends Thread{
    int rec_id;
    public RecMessage(int rec_id){
        this.rec_id=rec_id;
    }
    @Override
    public void run() {
        NumberModel numberModel=new NumberModel();
        numberModel.setNumber(rec_id);
        numberModel.setClient_number(Profile.rec_id);
        Msg msg=new Msg();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            msg.header.setService(Service.Chat.name());
            msg.body=numberModel;
            msg= Request.sendRequest(msg);
            if(msg.status&&msg.bodyList.size()>0){
                System.out.println("\n _ _ _ _ _ _ _ _ Message _ _ _ _ _ _ _ _ _ _");
                for (var messageModel:(List)msg.bodyList
                     ) {
                    Chat.showMessage((MessageModel) messageModel);
                }
                System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n ");
                viewPageChat();
            }
        }

    }
    void viewPageChat(){
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        System.out.println("||********************************************||");
        System.out.println("||************        Chat       *************||");
        System.out.println("||********************************************||");
        System.out.println("||* 1.SendMessage                            *||");
        System.out.println("||* 2.ShowAllMessageFromNumber               *||");
        System.out.println("||* 3.ShowAllMessageChat                     *||");
        System.out.println("||* 0.Back                                   *||");
        System.out.println("||********************************************||");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("Choose from list : ");
    }
}
public class Chat {
    int rec_id;
    List<Model> messageAllChatList=new ArrayList<>();
    MessageModel messageModel =new MessageModel();
    public Chat(int rec_id){
        this.rec_id=rec_id;
        RecMessage recMessage=new RecMessage(rec_id);
        recMessage.start();
        while (true){
            viewPageChat();
            int choose = Page.sc.nextInt();

            switch (choose){
                case 1:
                    sendMessage();
                    break;
                case 2:
                    showAllMessageFromNumber();
                    break;
                case 3:
                    showAllMessageChat();
                    break;
                case 0:
                    recMessage.stop();
                    return;

                default:
                    System.out.println("Choose not correct, try again");
                    break;

            }
        }
    }
    void viewPageChat(){
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        System.out.println("||********************************************||");
        System.out.println("||************        Chat       *************||");
        System.out.println("||********************************************||");
        System.out.println("||* 1.SendMessage                            *||");
        System.out.println("||* 2.ShowAllMessageFromNumber               *||");
        System.out.println("||* 3.ShowAllMessageChat                     *||");
        System.out.println("||* 0.Back                                   *||");
        System.out.println("||********************************************||");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("Choose from list : ");
    }
    private void sendMessage(){
        System.out.print("Enter message : ");
        messageModel.text=Page.sc.next();
        messageModel.rec_id=this.rec_id;
        messageModel.send_id= Profile.rec_id;

        Msg msg=new Msg();
        msg.header.setService(Service.SendMessage.name());
        msg.body=messageModel;
        msg= Request.sendRequest(msg);
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        if (msg.status){
            System.out.println("correct send :)");
        }
        else {
            System.out.println("fail send :(");
        }
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -\n");
    }
    private void showAllMessageChat(){
        messageAllChatList=new ArrayList<>();
        NumberModel numberModel=new NumberModel();
        numberModel.setNumber(rec_id);
        numberModel.setClient_number(Profile.rec_id);
        Msg msg=new Msg();
        msg.header.setService(Service.ShowAllMessageChat.name());
        msg.body=numberModel;
        msg= Request.sendRequest(msg);
        messageAllChatList.clear();
        messageAllChatList= msg.bodyList;
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        if (msg.status){
            for (Model model:
                    messageAllChatList ) {
                showMessage((MessageModel) model);
            }
        }else {
            System.out.println(msg.message);
        }
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -\n");

    }
    private void showAllMessageFromNumber(){
        messageAllChatList=new ArrayList<>();
        NumberModel numberModel=new NumberModel();
        numberModel.setNumber(rec_id);
        numberModel.setClient_number(Profile.rec_id);
        Msg msg=new Msg();
        msg.header.setService(Service.ShowAllMessageFromNumber.name());
        msg.body=numberModel;
        msg= Request.sendRequest(msg);
        messageAllChatList.clear();
        messageAllChatList= msg.bodyList;
                System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        if (msg.status){
            for (Model model:
                    messageAllChatList ) {
                showMessage((MessageModel) model);
            }
        }else {
            System.out.println(msg.message);
        }
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -\n");

    }
    public  static void showMessage(MessageModel messageModel){
        if(messageModel.send_id==Profile.rec_id)
        System.out.println("send : me"+"\t text : "+messageModel.text +"\t"+messageModel.date);
       else
        System.out.println("send : "+messageModel.send_id+"\t text : "+messageModel.text +"\t"+messageModel.date);
    }

    public Chat(){

    }
}
