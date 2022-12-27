package page;

import com.Client;
import controller.Profile;
import controller.Request;
import controller.Service;
import model.ClientModel;
import model.Model;
import request_response.Msg;

import java.util.ArrayList;
import java.util.List;

public class Home {
    List<Model> clientList=new ArrayList<>();
    List<Model> clientSendingList=new ArrayList<>();
    private void logOut(){

    }
    private void showNumbers(){
        clientList=new ArrayList<>();
        Msg msg=new Msg();
        msg.header.setService(Service.ShowNumbers.name());
        msg= Request.sendRequest(msg);
        System.out.println(msg.message);
        if (msg.status){
            clientList=  msg.bodyList;
        }
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        if(clientList.size()<1)
            System.out.println("Not Found");
        else
        for (Model model:
                clientList) {
            System.out.println("_. "+((ClientModel)model).getNumber()+"\t"+((ClientModel)model).getName());
        }
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -\n");
    }
    private void showSendingNumbers(){
        clientSendingList=new ArrayList<>();
        Msg msg=new Msg();
        msg.header.setService(Service.ShowSendingNumbers.name());
        msg= Request.sendRequest(msg);
        System.out.println(msg.message);
        if (msg.status){
            clientList=  msg.bodyList;
        }
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        if(clientSendingList.size()<1)
            System.out.println("Not Found");
        else
            for (Model model:
                    clientSendingList) {
                System.out.println("_. "+((ClientModel)model).getNumber()+"\t"+((ClientModel)model).getName()+"  ("+((ClientModel)model).getCountMessage()+" T)");
            }
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -\n");
    }
    private void chooseNumbers(){
        System.out.print("Enter number : ");
        int number = Page.sc.nextInt();

        Msg msg=new Msg();
        msg.header.setService(Service.ChooseNumber.name());
        ClientModel clientModel=new ClientModel();
        clientModel.setNumber(number);
        msg.body=clientModel;
        msg= Request.sendRequest(msg);
        System.out.println(msg.message);
        if (msg.status){
            new Chat(number);
        }

    }
    public Home(){
        while (true){
            System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("||********************************************||");
            System.out.println("||************        Home       *************||");
            System.out.println("||********************************************||");
            System.out.println("||* 1.Show numbers                           *||");
            System.out.println("||* 2.Show sending numbers                   *||");
            System.out.println("||* 3.choose number                          *||");
            System.out.println("||* 0.LogOut                                 *||");
            System.out.println("||********************************************||");
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.print("Choose from list : ");
            int choose = Page.sc.nextInt();

            switch (choose){
                case 1:
                    showNumbers();
                    break;
                case 2:
                    showSendingNumbers();
                    break;
                case 3:
                    chooseNumbers();
                    break;
                case 0:
                    return;

                default:
                    System.out.println("Choose not correct, try again");
                    break;

            }
        }
    }
}
