package page;

import controller.Profile;
import controller.Request;
import controller.Service;
import model.ClientModel;
import request_response.Header;
import request_response.Msg;
import security.Json;
import security.Security;

public class LogIn {
    ClientModel clientModel=new ClientModel();
    private boolean logIn(){
        try {
            System.out.print("Number : ");
            clientModel.setNumber(Page.sc.nextInt());
            System.out.print("Password : ");
            clientModel.setPassword(Page.sc.next());
            if(clientModel.getPassword().toCharArray().length<6)
                throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("password short");
            return false;
        }
        catch (Exception e){
            System.out.println("Entry not correct, try again");
            return false;
        }
        Msg msg=new Msg();
        msg.body=clientModel;
        msg.header.setService(Service.LogIn.name());
        Profile.rec_id=clientModel.getNumber();
        msg=Request.sendRequest(msg);
        System.out.println(msg.message);
        if (msg.status){
            Profile.clientModel= (ClientModel) msg.body;

            if(!Json.containsKey(Profile.rec_id))
                Security.getKeyClient(clientModel.getNumber(),clientModel.getPassword());
           // Profile.rec_id=((ClientModel) msg.body).getNumber();
                 return true;
        }
        return false;
    }
    public LogIn(){
        while (true){
            System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("||********************************************||");
            System.out.println("||************       LogIn       *************||");
            System.out.println("||********************************************||");
            System.out.println("||* 1.LogIn                                  *||");
            System.out.println("||* 0.Back                                   *||");
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.print("Choose from list : ");
            int choose = Page.sc.nextInt();

            switch (choose){
                case 1:
                    if (logIn()) {
                        new Home();
                        return;}
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
