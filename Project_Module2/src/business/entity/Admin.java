package business.entity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Admin implements Serializable {
    private Integer adminId ;
    private String adminName ;
    private String password ;
    private  String email ;
    private String phoneNumber ;
    private Date createdDate ;

    public Admin() {
    }

    public Admin(String adminName, String password, String email, String phoneNumber) {
        adminId =  getIdInput();
        this.adminName = adminName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        createdDate = new Date();
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public void inputData(Scanner scanner){
      adminId =  getIdInput();

      inputAdminName(scanner);

      inputPassword(scanner);

        inputEmail(scanner);

        inputPhoneNumber(scanner);

        createdDate = new Date();
    }
    public void updateData(Scanner scanner){
        inputAdminName(scanner);

        inputPassword(scanner);

        inputEmail(scanner);

        inputPhoneNumber(scanner);
    }

    public void displayData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("[ %-10d | %-30s | %-50s | %15s | %-20s ]\n",adminId,adminName,email ,phoneNumber,simpleDateFormat.format(createdDate));
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

    private void inputPassword(Scanner scanner) {
        while (true){
            System.out.println("Enter password (length >= 8 , no spaces): ");
            password = scanner.nextLine().trim();
            if(password.length() >= 8 && !password.matches(".*\\s.*")){
                break;
            }else {
                System.err.println("length >= 6 , no spaces !");
            }
        }
    }

    private void inputAdminName(Scanner scanner) {
        while (true){
            System.out.println("Enter admin name (length >= 6 , no spaces ):");
            adminName = scanner.nextLine().trim();
            if(adminName.length() >= 6 && !adminName.matches(".*\\s.*")){
                break;
            }else {
                System.err.println("Length >= 8, no spaces !");
            }
        }
    }

    private int getIdInput() {
        String currentDir = System.getProperty("user.dir");
        String filename = currentDir + "/src/database/listAdmin.txt";
        List<Admin> arr = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            arr = (List<Admin>) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(arr.size() > 0){
            return arr.get(arr.size() - 1).getAdminId() + 1;
        }else {
            return 1;
        }
    }

}
