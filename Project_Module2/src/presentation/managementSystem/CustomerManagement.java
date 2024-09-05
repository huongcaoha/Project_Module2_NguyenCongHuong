package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Customer;
import business.feature.CustomerFeature;
import presentation.run.Main;

import java.util.List;

public class CustomerManagement {
    public static void main(String[] args) {
        CustomerFeature customerFeature = new CustomerFeature();
        List<Customer> customers = IMethod.listCustomer();
        if(customers.getFirst() == null){
            System.err.println("Please login first. !");
            Login.main(args);
        }
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━ CUSTOMER MANAGEMENT ━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|      1. Display list customer          |      2. Update status customer     |           3. Delete customer           |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|      4. Search customer by name        |      5. Sort customer by name      |               6. Back                  |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

            int choice = IMethod.getNumber("Enter choice : ");
            switch (choice){
                case 1 : {
                    List<Customer> customers1 = IMethod.listCustomer();
                    customerFeature.displayList(customers1);
                    break;
                }
                case 2 : {
                    customerFeature.updateStatus();
                    break;
                }
                case 3 : {
                   customerFeature.delete();
                    break;
                }
                case 4 : {
                   customerFeature.searchCustomerByName();
                    break;
                }
                case 5 : {
                    customerFeature.sortCustomer();
                    break;
                }
                case 6 : {
                   return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }
}
