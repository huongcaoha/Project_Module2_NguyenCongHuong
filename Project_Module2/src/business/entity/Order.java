package business.entity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Order implements Serializable {
    private Integer orderId ;
    private String customerName ;
    private Integer customerId ;
    private String phoneNumber ;
    private String address ;
    private List<ProductCart> carts ;
    private Double totalMoney ;
    private Integer status ;
    // status = 1 => ordered
    // status = 2 => confirm
    // status = 3 => delivery
    // status = 4 => done
    // status = 0 => cancel
    private Date createdDate ;

    public Order() {
    }

    public Order( String customerName, Integer customerId, String phoneNumber, String address, List<ProductCart> carts, Double totalMoney) {
        this.orderId = getIdInput();
        this.customerName = customerName;
        this.customerId = customerId ;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.carts = carts;
        this.totalMoney = totalMoney;
        this.status = 1;
        this.createdDate = new Date();
    }
    private int getIdInput() {
        String currentDir = System.getProperty("user.dir");
        String filename = currentDir + "/src/database/listOrder.txt";
        List<Order> arr = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            arr = (List<Order>) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(arr.size() > 0){
            return arr.get(arr.size() - 1).getOrderId() + 1;
        }else {
            return 1;
        }
    }

    public boolean updateStatus(){
        if(status == 0){
            System.err.println("Canceled orders cannot be changed !");
            return false;
        }else if (status == 4){
            System.err.println("Orders that have been successfully delivered cannot be changed !");
            return false ;
        }else {
           if(status == 1){
               status = 2 ;
               System.out.println("Update success order  to confirm  !");
           }else if(status == 2){
               status = 3 ;
               System.out.println("Update success order  to delivering !");
           }else {
               status = 4 ;
               System.out.println("Update success order  to delivered !");
           }
           return true ;
        }
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ProductCart> getCarts() {
        return carts;
    }

    public void setCarts(List<ProductCart> carts) {
        this.carts = carts;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String printStatus(int status){
        String rs = "" ;
        switch (status){
            case 0 : {
                rs = "Cancel";
            }
            case 1 : {
                rs = "ordered";
            }
            case 2 : {
                rs = "Confirm" ;
            }
            case 3 : {
                rs = "delivering";
            }
            case 4 : {
                rs = "delivered";
            }
        }
        return rs ;
    }
    public void displayData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-28s | %-13s | %-28s | %-18s | %-13s | %-13s |\n" , orderId,customerName,phoneNumber,address,numberFormat.format(totalMoney)+" VND",printStatus(status),simpleDateFormat.format(createdDate));
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
    }

}
