package business.feature;

import business.common.IMethod;
import business.entity.Customer;

import java.text.SimpleDateFormat;
import java.util.List;

public class InformationFeature {
    public static void viewInformation(){
        List<Customer> checkLogin = IMethod.checkLogin();
        Customer customer = checkLogin.getFirst();
        if(checkLogin.getFirst() == null){
            System.err.println("Please log in first !");
        }else {
            //length 80 tất cả
            while (true){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.printf("| %-76s |\n","              Customer Name : " +customer.getCustomerName());
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.printf("| -Gender : %-10s                                                         |\n",customer.getGender() ? "male" : "female");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.printf("| -Birthday : %-15s                                                  |\n",simpleDateFormat.format(customer.getBirthday()));
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.printf("| -Phone number : %-15s                                              |\n",customer.getPhoneNumber());
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.printf("| -Email : %-30s                                      |\n",customer.getEmail());
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.printf("| -Pass : %-69s|\n",customer.getPassword());
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|          1. Update information          |            2. Exit                 |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

                int choice = IMethod.getNumber("Enter choice : ");
                if(choice == 1){
                    List<Customer> customers = IMethod.listCustomer();
                    int index = customers.stream().map(Customer::getCustomerName).toList().indexOf(checkLogin.getFirst().getCustomerName());
                    customers.get(index).updateData(IMethod.scanner);
                    IMethod.saveDatabase(IMethod.fileCustomer,customers);
                    checkLogin.set(0,customers.get(index));
                    IMethod.saveDatabase(IMethod.fileCheckLogin,checkLogin);
                    checkLogin = IMethod.checkLogin();
                    customer = checkLogin.getFirst();
                    System.out.println("Update information successfully !");
                }else if(choice == 2) {
                    return;
                }else {
                    System.err.println("Enter choice 1 or 2 !");
                }
            }

        }
    }
}
