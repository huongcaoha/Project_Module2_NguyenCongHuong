package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Customer;
import business.entity.Product;
import business.feature.CartFeature;
import business.feature.InformationFeature;
import business.feature.ShopFeature;
import presentation.run.Main;

import java.util.List;

public class Shop {
    public static void main(String[] args) {
        while (true){
            String fileCheckLogin = "checkLogin.txt";
            List<Customer> checkLogin = IMethod.getListObject(fileCheckLogin);
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("|   Hello , %-28s |\n",checkLogin.getFirst() == null ? "Customer !" : checkLogin.getFirst().getCustomerName() + " !");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━ SHOPPING ━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|        1. Display list products        |    2. View personal information    |              3. View cart              |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|        4. Orders history               |          5. Favorites list         |               6. Logout                |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

            int choice = IMethod.getNumber("Enter choice : ");
            switch (choice){
                case 1 : {
                    List<Product> products = IMethod.listProduct();
                    ShopFeature.displayList(args,products);
                    break;
                }
                case 2 : {
                InformationFeature.viewInformation();
                    break;
                }
                case 3 : {
                CartFeature.viewCart(checkLogin.getFirst());
                    break;
                }
                case 4 : {

                    break;
                }
                case 5 : {

                    break;
                }
                case 6 : {
                    IMethod.logout();
                    Main.main(args);
                    break;
                }
                default: {
                    System.err.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }
}
