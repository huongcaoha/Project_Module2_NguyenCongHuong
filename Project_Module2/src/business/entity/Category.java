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

public class Category implements Serializable {
    private Integer cateId ;
    private String cateName ;
    private Boolean status = true;
    private Date createdDate ;

    public Category() {
    }

    public Category( String cateName) {
        this.cateId = inputCateId();
        this.cateName = cateName;
        this.status = true ;
        this.createdDate = new Date();
    }

    public void inputData(Scanner scanner){
        cateId = inputCateId();

        inputCateName(scanner);

        status = true ;

        createdDate = new Date() ;
    }

    public void updateData(Scanner scanner){
        inputCateName(scanner);

        updateStatus(scanner);
    }

    private void updateStatus(Scanner scanner) {
        while (true){
            System.out.println("Enter status category (true or false ):");
            String st = scanner.nextLine().trim().toLowerCase();
            if(st.equals("true") || st.equals("false")){
                try {
                    status = Boolean.parseBoolean(st);
                    break;
                }catch (Exception e){
                    System.out.println("Enter true or false !");
                }
            }else {
                System.out.println("Enter true or false !");
            }

        }
    }

    private void inputCateName(Scanner scanner) {
        while (true){
            System.out.println("Enter category name :");
            cateName = scanner.nextLine().trim();
            if(cateName.length() == 0 ){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
    }

    private static int inputCateId() {
        String fileName = "listCategory.txt";
        List<Category> arr = IMethod.getListObject(fileName);
        if(!arr.isEmpty()){
            return arr.getLast().getCateId() + 1;
        }else {
            return 1;
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void displayData(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-8d | %-38s | %-8s | %-18s |\n" ,cateId , cateName , status ? "on" : "off",format.format(createdDate));
        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
    }


}
