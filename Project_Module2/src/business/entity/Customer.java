package business.entity;

import business.common.IMethod;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Customer implements Serializable {
    private Integer customerId ;
    private String customerName ;
    private String password ;
    private String repassword ;
    private Boolean gender ;
    private Date birthday ;
    private String phoneNumber ;
    private String email ;
    private Role role = Role.CUSTOMER;
    private Boolean status = true ;
    private Date createdDate ;

    public Customer() {
    }

    public void inputData(Scanner scanner){
        customerId =  inputIdCustomer();

        inputCustomerName(scanner);

        inputPassword(scanner);

        inputRepassword(scanner);

        inputGender(scanner);

        inputBirthday(scanner);

        inputPhoneNumber(scanner);

        inputEmail(scanner);

        this.createdDate = new Date() ;
    }

    public void updateData(Scanner scanner){

        inputPassword(scanner);

        inputRepassword(scanner);

        inputGender(scanner);

        inputBirthday(scanner);

        inputPhoneNumber(scanner);

        inputEmail(scanner);
    }
   public void updateCustomerName(Scanner scanner){
       List<Customer> customers = IMethod.listCustomer();
       String oddName = customerName;
       while (true){
           System.out.println("Enter customer name :");
           customerName = scanner.nextLine().replaceAll("\\s+","");
           if(customerName.length() >= 6){
               boolean isExist = false ;
               for(Customer customer : customers){
                   if(customer.getCustomerName().equalsIgnoreCase(customerName) && !customerName.equals(oddName)){
                       isExist = true ;
                       break;
                   }
               }
               if(!isExist){
                   break;
               }else {
                   System.err.println("Customer name existed !");
               }

           }else {
               System.err.println("Length name >= 6 character !");
           }
       }
    }

    public void updatePassword (Scanner scanner){
        inputPassword(scanner);
        inputRepassword(scanner);
    }

    public void updateCustomerStatus(){
        this.status = !this.status ;
    }
    private void inputEmail(Scanner scanner) {
        while (true){
            System.out.println("Enter email :");
            email = scanner.nextLine().trim();
            if(email.matches("^\\w{8,}@\\w{2,}\\.[a-zA-Z]{2,}$")){
                break;
            }else {
                System.err.println("Email invalid !");
            }
        }
    }

    private void inputPhoneNumber(Scanner scanner) {
        while (true){
            System.out.println("Enter phone number : ");
            phoneNumber = scanner.nextLine().trim();
            if(phoneNumber.matches("^0[35789][0-9]{8}$")){
                break;
            }else {
                System.err.println("Phone numberr invalid !");
            }
        }
    }

    private void inputBirthday(Scanner scanner) {
        while (true){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Enter birthday (dd/MM/yyyy) : ");
            String dob = scanner.nextLine().trim();
            if(dob.matches("^[0-3][0-9]/[01][0-9]/[12][0-9]{3}$")){
                try {
                    birthday = simpleDateFormat.parse(dob);
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                System.err.println("Birthday invalid !");
            }
        }
    }

    private void inputGender(Scanner scanner) {
        while (true){
            System.out.println("Enter gender (true : male , false : female) :");
            String gen = scanner.nextLine().trim().toLowerCase();
            if(gen.equals("true") || gen.equals("false")){
                try {
                    gender = Boolean.parseBoolean(gen);
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                System.err.println("Enter true or false !");
            }
        }
    }

    private void inputRepassword(Scanner scanner) {
        while (true){
            System.out.println("Enter repassword (length >= 6 & match password ) :");
            repassword = scanner.nextLine().replaceAll("\\s+","");
            if(repassword.length() >= 6){
                repassword = IMethod.hashPassword(repassword);
                if(repassword.equals(password)){
                    break;
                }else {
                    System.err.println("Repassword not match password !");
                }
            }else {
                System.err.println("Enter length >= 6 character !");
            }
        }
    }

    private void inputPassword(Scanner scanner) {
        while (true){
            System.out.println("Enter password (length >= 6) :");
            password = scanner.nextLine().replaceAll("\\s+","");
            if(password.length() >= 6){
                password = IMethod.hashPassword(password);
                break;
            }else {
                System.err.println("Enter length >= 6 character !");
            }
        }
    }

    private void inputCustomerName(Scanner scanner) {
        List<Customer> customers = IMethod.listCustomer();
        while (true){
            System.out.println("Enter customer name :");
            customerName = scanner.nextLine().replaceAll("\\s+","");
            if(customerName.length() >= 6){
                int index = customers.stream().map(Customer::getCustomerName).toList().indexOf(customerName);
                if(index == -1){
                    break;
                }else {
                    System.err.println("Customer name existed !");
                }

            }else {
                System.err.println("Length name >= 6 character !");
            }
        }
    }

    private static int inputIdCustomer() {
        String fileName = "listCustomer.txt";
        List<Customer> arr = IMethod.getListObject(fileName);
        if(!arr.isEmpty()){
            return arr.getLast().customerId + 1;
        }else {
            return 1;
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setFullname(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void displayData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
        System.out.printf("[ %-3d | %-28s | %-8s | %-13s | %-13s | %-28s | %-8s | %-13s ]\n" , customerId,customerName,gender ? "male" : "female" ,simpleDateFormat.format(birthday),phoneNumber,email,status ? "on" : "off" ,simpleDateFormat.format(createdDate));
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
    }

}