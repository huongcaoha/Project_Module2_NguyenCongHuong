package business.entity;

import business.common.IMethod;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductCart implements Serializable {
    private Integer productCartId ;
    private Integer customerId ;
    private String productName ;
    private  Double finalPrice ;
    private String size ;
    private String color ;
    private Integer cateId ;
    private Integer quantity ;
    private Double totalMoney;

    public ProductCart() {
    }

    public ProductCart( Integer customerId ,String productName, Double finalPrice, String size, String color, Integer cateId, Integer quantity, Double totalMoney) {
        this.customerId = customerId ;
        this.productCartId = getIdProductCart();
        this.productName = productName;
        this.finalPrice = finalPrice;
        this.size = size;
        this.color = color;
        this.cateId = cateId;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
    }

    public int getIdProductCart(){
        List<ProductCart> arr = IMethod.listProductCart();
        if(!arr.isEmpty()){
            return  arr.getLast().getProductCartId() +1 ;
        }else {
            return 1 ;
        }
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public int getProductCartId() {
        return productCartId;
    }

    public void setProductCartId(int productCartId) {
        this.productCartId = productCartId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.totalMoney = quantity*this.finalPrice;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void displayData(){
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-28s | %-13s | %-8d | %-8d | %-13s | %-13s | %-20s |\n",productCartId,productName,format.format(finalPrice)+"VNĐ",cateId,quantity,size,color,format.format(totalMoney)+"VNĐ");
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}
