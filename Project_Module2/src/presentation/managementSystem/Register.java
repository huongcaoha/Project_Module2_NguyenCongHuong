package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Customer;

import java.util.List;
import java.util.Scanner;

public class Register {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "listCustomer.txt";
        List<Customer> customers = IMethod.getListObject(fileName);
        Customer customer = new Customer();
        customer.inputData(scanner);
        customers.add(customer);
        IMethod.saveDatabase(fileName,customers);
        System.out.println("Register successfully !");
        Login.main(args);
    }
}
