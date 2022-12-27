package page;

import com.Client;
import controller.Profile;
import controller.Request;
import controller.Service;
import model.ClientModel;
import request_response.Msg;

public class SignUp {
    ClientModel clientModel=new ClientModel();
    private boolean signUp(){
        try {
            System.out.print("Number : ");
            clientModel.setNumber(Page.sc.nextInt());
            System.out.print("Password : ");
            clientModel.setPassword(Page.sc.next());
            System.out.print("Name : ");
            clientModel.setName(Page.sc.next());

            if(clientModel.getPassword().toCharArray().length<6)
                throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("password short, Enter password > 6");
            return false;
        }
        catch (Exception e){
            System.out.println("Entry not correct, try again");
            return false;
        }
        Msg msg=new Msg();
        msg.body=clientModel;
        msg.header.setService(Service.SignUp.name());
        msg= Request.sendRequest(msg);
        System.out.println(msg.message);
        if (msg.status){
            Profile.clientModel= (ClientModel) msg.body;
            Profile.rec_id=((ClientModel) msg.body).getNumber();
            return true;
        }
        return false;
    }
    public SignUp(){
        while (true){
            System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("||********************************************||");
            System.out.println("||************      SignUp      *************||");
            System.out.println("||********************************************||");
            System.out.println("||* 1.SignUp                                 *||");
            System.out.println("||* 0.Back                                   *||");
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.print("Choose from list : ");
            int choose = Page.sc.nextInt();

            switch (choose){
                case 1:
                    if (signUp()) {
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
