package presentation.managementSystem;

import business.common.IMethod;
import business.feature.ForgetPassword;
import business.feature.Register;
import business.util.GetColor;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        business.feature.Login login = new business.feature.Login();
        String fileCheckLogin = "checkLogin.txt";
       while (true){
           Scanner scanner = new Scanner(System.in);
           System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
           System.out.println("|                                                         "+ GetColor.GREEN + " PAGE : LOGIN"+GetColor.RESET+"                                                     |");
           System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
           System.out.println("┃                              ┃                              ┃                              ┃                              ┃");
           System.out.println("┃         1. Login             ┃         2. Register          ┃      3. Forget password      ┃           4 . EXIT           |");
           System.out.println("┃                              ┃                              ┃                              ┃                              ┃");
           System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

           int choice = IMethod.getNumber("Enter your choice : ");
           switch (choice){
               case 1 : {
                   login.login(args, scanner, fileCheckLogin);
                   break;
               }
               case 2 : {
                   Register.main(args);
                   break;
               }
               case 3 : {
                   ForgetPassword forgetPassword = new ForgetPassword();
                   forgetPassword.forgetPassword();
                   break;
               }
               case 4 : {
                   return;
               }
               default: {
                   System.err.println("Enter choice from 1 to 5 !");
               }
           }
       }
    }

}
