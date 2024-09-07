package presentation;

import business.common.IMethod;
import business.entity.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Demo {
    public static void main(String[] args) {

//        String currentDir = System.getProperty("user.dir");
//        String filePath = currentDir + "/src/database/checkLogin.txt";
//        Customer customer = new Customer();

//        Category category = new Category("Đồ gia dụng");
//        categories.add(category);
//        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
//            out.writeObject(customer);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        List<Customer> newArr = new ArrayList<>();
//        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
//            newArr = (List<Customer>) in.readObject();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        for(Customer c : newArr){
//            System.out.println(c.getCustomerName());
//        }
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//
//        System.out.println(format.format(date));
//        Scanner scanner = new Scanner(System.in);
//        List<Customer> list = IMethod.getListObject("listCustomer.txt");
//        Customer admin = new Customer();
//        admin.inputData(scanner);
//        admin.setRole(Role.ADMIN);
//        list.add(admin);
//       IMethod.saveDatabase("listCustomer.txt",list);
//        Scanner scanner = new Scanner(System.in);
//        String fileName = "listCustomer.txt";
//        List<Customer> customers = new ArrayList<>();
//        Customer customer = new Customer();
//        customer.inputData(scanner);
//        customer.setRole(Role.ADMIN);
//        customers.add(customer);
//        IMethod.saveDatabase(fileName,customers);

//        System.out.printf("[%-30s | %-10s | %-10d ]\n" , "huongcaoha","123456789",50);
//        System.out.printf("[%-30s | %-10s | %-10d ]\n","123","456",123456789);
     List<FavoriteProduct> favoriteProducts = new ArrayList<>();
     IMethod.saveDatabase(IMethod.fileFavoriteProduct,favoriteProducts);
    }
}