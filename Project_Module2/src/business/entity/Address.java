package business.entity;

import business.common.IMethod;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Address implements Serializable {
    private Integer addressId ;
    private String addressName ;
    private String receiveName ;
    private String phoneNumber ;
    private LocalDate createdDate ;

    public Address() {
    }

    public Address(Integer addressId, String addressName , String receiveName ,String phoneNumber) {
        this.addressId = addressId;
        this.addressName = addressName;
        this.receiveName = receiveName;
        this.phoneNumber = phoneNumber;
        this.createdDate = LocalDate.now();
    }

    public void inputData(Scanner scanner , List<Address> addresses){
        inputAddressId(addresses);

        inputAddressName(scanner);

        inputReceiveName(scanner);

        inputPhoneNumber(scanner);

        this.createdDate = LocalDate.now();

    }

    private void inputPhoneNumber(Scanner scanner) {
        while (true){
            System.out.println("Enter phone number : ");
            phoneNumber = scanner.nextLine().trim();
            if(phoneNumber.matches("^0[35789][0-9]{8}$")){
                break;
            }else {
                System.err.println("Phone number invalid !");
            }
        }
    }

    private void inputReceiveName(Scanner scanner) {
        while (true){
            System.out.println("Enter receive name : ");
            this.receiveName = scanner.nextLine().trim();
            if(receiveName.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
    }

    private void inputAddressId(List<Address> addresses) {
        if(addresses.isEmpty()){
            this.addressId = 1 ;
        }else {
            this.addressId = addresses.getLast().getAddressId() + 1 ;
        }
    }

    private void inputAddressName(Scanner scanner) {
        while (true){
            System.out.println("Enter address name : ");
            this.addressName = scanner.nextLine().trim();
            if(addressName.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return addressName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public void setAddress(String addressName) {
        this.addressName = addressName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void displayData(){
        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━┓");
        System.out.printf("| %-8d | %-28s | %-45s | %-13s | %-10s |\n" ,addressId,receiveName,addressName,phoneNumber,createdDate);
        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━┛");
    }
}
