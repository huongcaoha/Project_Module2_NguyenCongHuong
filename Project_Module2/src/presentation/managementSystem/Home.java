package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Customer;
import business.entity.Role;
import business.feature.Login;
import business.util.GetColor;

import java.util.List;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Customer> checkLogin = IMethod.checkLogin();
       if(checkLogin.getFirst() != null){
               IMethod.logout();
       }
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|     "+ GetColor.GREEN+"WELCOME TO SHOP : IPHONE STORE"+GetColor.RESET+"                                                                                         |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━ HOME PAGE ━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                      ┃                        ┃                           ┃                        ┃                       |");
            System.out.println("┃    1. REGISTER       ┃       2. LOGIN         ┃        3. Shopping        ┃   4. FORGET PASSWORD   ┃        5 . EXIT       |");
            System.out.println("┃                      ┃                        ┃                           ┃                        ┃                       |" );
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    Register.main(args);
                    break;
                }
                case 2 : {
                    Login.login(args, scanner, IMethod.fileCheckLogin);
                    break;
                }
                case 3 : {
                    Shop.main(args);
                    break;
                }
                case 4 : {
                    ForgetPassword.forgetPassword();
                    break;
                }
                case 5 : {
                    System.out.println("Goodbye !!!");
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 5 !");
                }
            }
        }
    }
}
