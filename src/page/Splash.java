package page;

import controller.Profile;
import controller.SecurityType;
import security.PGP;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Splash {
    private void exit(){
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        System.out.println("||********************************************||");
        System.out.println("||************        Exit       *************||");
        System.out.println("||********************************************||");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
    }
    public Splash() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException {
        while (true){
            System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("||********************************************||");
            System.out.println("||************    ISS Project    *************||");
            System.out.println("||********************************************||");
            System.out.println("||* 0.Zero                                   *||");
            System.out.println("||* 1.SymmetricCBC                           *||");
            System.out.println("||* 2.SymmetricGCM                           *||");
            System.out.println("||* 3.PGP                                    *||");
            System.out.println("||* 4.DigitSign                              *||");
            System.out.println("||* 5.PGP_DigitSign                          *||");
            System.out.println("||* 6.AllSecurity                            *||");
            System.out.println("||* 10.Exit                                  *||");
            System.out.println("||********************************************||");
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.print("Choose type Encryption from list : ");
            int choose = Page.sc.nextInt();
            Profile.securityType= SecurityType.Zero;
            switch (choose){
                case 0:
                    Profile.securityType= SecurityType.Zero;
                    break;
                case 1:
                    Profile.securityType= SecurityType.SymmetricCBC;
                    break;
                case 2:
                    Profile.securityType= SecurityType.SymmetricGCM;
                    break;
                case 3:
                    new PGP();
                    Profile.securityType= SecurityType.PGP;
                    break;
                case 4:
                    new PGP();
                    Profile.securityType= SecurityType.DigitalSignature;
                    break;
                case 5:
                    new PGP();
                    Profile.securityType= SecurityType.PGP_DigitSign;
                    break;
                case 10:
                    exit();
                    return;

                default:
                    System.out.println("Choose not correct, try again");
                    break;
            }
            if(choose>=0&&choose<10)
                new Welcome();
        }
    }
}
