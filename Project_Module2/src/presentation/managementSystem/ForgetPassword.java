package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Customer;

import java.util.List;

public class ForgetPassword {
    public static void forgetPassword(){
        List<Customer> customers = IMethod.listCustomer();
        String customerName , email , phoneNumber;
        boolean checkCustomer = false ;
        customerName = IMethod.getString("Enter your customer name : ");
        int indexCustomer = customers.stream().map(Customer::getCustomerName).toList().indexOf(customerName);
        if (indexCustomer == - 1){
            System.err.println("Not found customer name !");
        }else {
            email = IMethod.getString("Enter your email : ");
           phoneNumber = IMethod.getPhoneNumber();

            if(customers.get(indexCustomer).getEmail().equals(email) && customers.get(indexCustomer).getPhoneNumber().equals(phoneNumber)){
                String password , rePassword ;
                password = IMethod.getString("Enter new password : ");
                while (true){
                    rePassword = IMethod.getString("re-Enter password : ");
                    if(password.equals(rePassword)){
                        break;
                    }else {
                        System.err.println("Re-password not match !");
                    }
                }
                customers.get(indexCustomer).setPassword(IMethod.hashPassword(password));
                IMethod.saveDatabase(IMethod.fileCustomer,customers);
                System.out.println("Change password success !");
            }else {
                System.err.println("Email or phone number is incorrect !");
            }

        }

    }
}
