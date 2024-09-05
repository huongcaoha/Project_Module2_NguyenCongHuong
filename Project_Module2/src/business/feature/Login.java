package business.feature;

import business.common.IMethod;
import business.entity.Customer;
import business.entity.Role;
import presentation.managementSystem.AdminManagement;
import presentation.managementSystem.Shop;

import java.util.List;
import java.util.Scanner;

public class Login {
    public static void login(String[] args, Scanner scanner, String fileCheckLogin) {
        String fileName = "listCustomer.txt" ;
        List<Customer> customers = IMethod.getListObject(fileName);
        System.out.println("Enter customer name : ");
        String customerName = scanner.nextLine().trim();

        System.out.println("Enter password :");
        String password = IMethod.hashPassword(scanner.nextLine().trim());
        int  index = customers.stream().map(Customer::getCustomerName).toList().indexOf(customerName);
        if(index != -1){
            boolean checkPassword = customers.get(index).getPassword().equalsIgnoreCase(password);
            if(checkPassword){
                System.out.println("Login successfully !");
                List<Customer> list = IMethod.getListObject(fileCheckLogin);
                if(list.isEmpty()){
                    list.add(customers.get(index));
                    IMethod.saveDatabase(fileCheckLogin,list);
                }else {
                    list.set(0,customers.get(index));
                    IMethod.saveDatabase(fileCheckLogin,list);
                }
                if(customers.get(index).getRole() == Role.CUSTOMER){
                    Shop.main(args);
                }else {
                    AdminManagement.main(args);
                }

            }
        }else {
            System.err.println("Customer name non-existent !");
        }
    }
}
