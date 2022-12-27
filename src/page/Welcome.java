package page;

public class Welcome {
    private void exit(){
        System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        System.out.println("||********************************************||");
        System.out.println("||************        Exit       *************||");
        System.out.println("||********************************************||");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
    }
    public Welcome(){
        while (true){
            System.out.println("\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
            System.out.println("||********************************************||");
            System.out.println("||************      Welcome      *************||");
            System.out.println("||********************************************||");
            System.out.println("||* 1.LogIn                                  *||");
            System.out.println("||* 2.SignUp                                 *||");
            System.out.println("||* 3.Exit                                   *||");
            System.out.println("||********************************************||");
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.print("Choose from list : ");
            int choose = Page.sc.nextInt();

            switch (choose){
                case 1:
                    new LogIn();
                    break;
                case 2:
                    new SignUp();
                    break;
                case 3:
                    exit();
                    return;

                default:
                    System.out.println("Choose not correct, try again");
                    break;

            }
        }
    }
}
