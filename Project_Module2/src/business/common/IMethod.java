package business.common;

import business.entity.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IMethod {
    public static Scanner scanner = new Scanner(System.in);
    public final static String fileCategory = "listCategory.txt";
    public final static String fileProduct = "listProduct.txt";
    public final static String fileOrder = "listOrder.txt";
    public final static String fileCustomer = "listCustomer.txt";
    public final static String fileProductCart = "listProductCart.txt";
    public final static String fileCheckLogin = "checkLogin.txt";
    public final static String fileFavoriteProduct = "listFavoriteProduct.txt";

    public static <T> List<T> getListObject(String nameFile){
        List<T> list = new ArrayList<>();
        String filename =  "src/business/database/" + nameFile;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
           list = (List<T>) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    };

   public static <T> boolean saveDatabase(String nameFile, List<T> data){
       String filename = "src/business/database/"+ nameFile;
       try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
           out.writeObject(data);
       }catch (Exception e){
           e.printStackTrace();
       }
       return true;
   }
    public static int getNumber(String label){
        int number = 0 ;
        while (true){
            try {
                System.out.println(label);
                number = Integer.parseInt(scanner.nextLine().trim());
                if(number > 0){
                    break;
                }else {
                    System.err.println("Enter number > 0 !");
                }
            }catch (Exception e){
                System.err.println("Number invalid !");
            }
        }
        return number;
    }
    public static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Logout(){
       String fileName = "checkLogin.txt";
       List<Customer> customers = IMethod.getListObject(fileName);
       customers.set(0,null);
       IMethod.saveDatabase(fileName,customers);
        System.out.println("Logout successfully !");
    }

        public static String getString(String label){
                String rs ;
            while (true){
                System.out.println(label);
                rs = IMethod.scanner.nextLine().trim();
                if(rs.isEmpty()){
                    System.err.println("Cannot be left blank !");
                }else {
                    break;
                }
            }
            return rs ;
        }

        public static List<Customer> checkLogin(){
            return getListObject(fileCheckLogin);
        }

        public static List<Category> listCategory(){
            return getListObject(fileCategory);
        }

        public static List<Customer> listCustomer(){
            return getListObject(fileCustomer);
        }

        public static List<Order> listOrder(){
            return getListObject(fileOrder);
        }
        public static List<Product> listProduct(){
            return getListObject(fileProduct);
        }

        public static List<ProductCart> listProductCart(){
            return getListObject(fileProductCart);
        }
        public static List<FavoriteProduct> listFavoriteProduct(){
            return getListObject(fileFavoriteProduct);
        }
        public static List<Address> listAddresses(Customer customer){
            List<Address> addresses = new ArrayList<>() ;
            int idCustomer = customer.getCustomerId();
            List<Customer> customers = listCustomer();
            int indexCustomer = customers.stream().map(Customer::getCustomerId).toList().indexOf(idCustomer);
            if(indexCustomer == - 1){
                 return addresses ;
            }else {
                return customers.get(indexCustomer).getAddresses();
            }
        }
        public static void saveAddress(Customer customer , List<Address> addresses){
            List<Customer> customers = listCustomer();
            int idCustomer = customer.getCustomerId();
            int indexCustomer = customers.stream().map(Customer::getCustomerId).toList().indexOf(idCustomer);
            if(indexCustomer == -1){
                System.err.println("Not found customer !");
            }else {
                customers.get(indexCustomer).setAddresses(addresses);
                IMethod.saveDatabase(IMethod.fileCustomer,customers);
            }
        }
        public static void logout(){
            List<Customer> customers = checkLogin();
            customers.set(0,null);
            IMethod.saveDatabase("checkLogin.txt",customers);
        }
}
