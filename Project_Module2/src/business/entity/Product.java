package business.entity;

import business.common.IMethod;
import business.feature.CategoryFeature;
import business.util.GetColor;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 7618092267567483597L;
    private Integer productId ;
    private String productName ;
    private Double rootPrice ;
    private Integer discount ;
    private  Double finalPrice ;
    private Integer inventory ;
    private String size ;
    private String color ;
    private String description ;
    private Integer cateId ;
    private Integer status ;
    private Date createdDate ;

    public Product() {
    }

    public Product( String productName, Double rootPrice, Integer discount,  Integer inventory,String size ,String color ,String description, Integer cateId) {
        this.productId = getIdProduct();
        this.productName = productName;
        this.rootPrice = rootPrice;
        this.discount = discount;
        this.finalPrice = rootPrice - ((rootPrice * discount) / 100);
        this.inventory = inventory;
        this.size = size ;
        this.color = color ;
        this.description = description ;
        this.cateId = cateId;
        this.status = 1;
       this.createdDate = new Date();
    }

    public Integer getProductId() {
        return productId;
    }

    public void inputData(Scanner scanner){
        this.productId = getIdProduct();

        inputProductName(scanner);

        inputPrice(scanner);

        inputDiscount(scanner);

        this.finalPrice = rootPrice - ((rootPrice * discount) / 100);

        inputInventory(scanner);

        inputSize(scanner);

        inputColor(scanner);

        inputDescription(scanner);

        String fileCate = "listCategory.txt";
        List<Category> categories = IMethod.getListObject(fileCate);
        CategoryFeature categoryFeature = new CategoryFeature();
        categoryFeature.displayList(categories);
        inputIdCategory(scanner);

        this.status = 1;
        this.createdDate = new Date();
    }

    private void inputDescription(Scanner scanner) {
        while (true){
            System.out.println("Enter description : ");
            this.description = scanner.nextLine().trim();
            if(description.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
    }

    private void inputColor(Scanner scanner) {
        while (true){
            System.out.println("*************** List Color ****************");
            for(int i = 0 ; i < GetColor.colors.size() ; i++){
                System.out.println((i+1) + " : " + GetColor.colors.get(i));
            }
            try {
                System.out.println("Enter color number : ");
                int color = Integer.parseInt(scanner.nextLine().trim());
                if(color > 0 && color < 9){
                    this.color = GetColor.colors.get(color-1);
                    break;
                }else {
                    System.err.println("Enter number from 1 to 8 !");
                }
            }catch (Exception e){
                System.err.println("Color invalid !");
            }
        }
    }

    private void inputSize(Scanner scanner) {
        while (true){
            System.out.println("Enter size :");
            this.size = scanner.nextLine().replaceAll("\\s+" , "");
            if(size.length() <= 0){
                System.out.println("Do not leave blank !");
            }else {
                break;
            }
        }
    }

    private void inputIdCategory(Scanner scanner) {
        while (true){
            try {
                System.out.println("Enter id category : ");
                this.cateId = Integer.parseInt(scanner.nextLine().trim());
                if(IMethod.checkExisCategory(cateId)){
                    break;
                }else {
                    System.out.println("Cate id not match !");
                }
            }catch (Exception e){
                System.out.println("Cate id invalid !");
            }
        }
    }

    private void inputInventory(Scanner scanner) {
        while (true){
            try {
                System.out.println("Enter inventory : ");
                this.inventory = Integer.parseInt(scanner.nextLine().trim());
                if(inventory > 0){
                    break;
                }else {
                    System.err.println("Inventory > 0 !");
                }
            }catch (Exception e){
                System.out.println("Inventory invalid !");
            }
        }
    }

    private void inputDiscount(Scanner scanner) {
        while (true){
            try {
                System.out.println("Enter discount product (0% - 100%) : ");
                this.discount = Integer.parseInt(scanner.nextLine().trim());
                if(discount >= 0 && discount <= 100){
                    break;
                }else {
                    System.err.println("Enter discount from 0 to 100 ! ");
                }
            }catch (Exception e){
                System.err.println("Discount invalid !");
            }
        }
    }

    private void inputPrice(Scanner scanner) {
        while (true){
            try {
                System.out.println("Enter selling price : ");
                this.rootPrice = Double.parseDouble(scanner.nextLine().trim());
                if(rootPrice > 0){
                    break;
                }else {
                    System.err.println("Selling price > 0 !");
                }
            }catch (Exception e){
                System.err.println("Selling price invalid !");
            }
        }
    }

    private void inputProductName(Scanner scanner) {
        while (true){
            System.out.println("Enter product name : ");
            this.productName = scanner.nextLine().trim();
            if(productName.length() == 0){
                System.err.println("Product name cannot be left blank !");
            }else {
                break;
            }
        }
    }

    public void updateData(Scanner scanner){
        inputProductName(scanner);

        inputPrice(scanner);

        inputDiscount(scanner);

        this.finalPrice = rootPrice - ((rootPrice * discount) / 100);

        inputInventory(scanner);

        inputSize(scanner);

        inputColor(scanner);

        inputDescription(scanner);

        IMethod.displayListCategory();

        inputIdCategory(scanner);

        updateStatus(scanner);
    }

    private void updateStatus(Scanner scanner) {
        while (true){
           try {
               System.out.println("Enter status (0 : not sell , 1 : selling , 2 :out of stock)");
               this.status = Integer.parseInt(scanner.nextLine().trim());
               if(status >= 0 && status < 3){
                   break;
               }else {
                   System.err.println("Enter 0 : not sell , 1 : selling , 2 :out of stock !");
               }
           }catch (Exception e){
               System.err.println("Status invalid !");
           }
       }
    }

    public static int getIdProduct(){
       String fileName = "listProduct.txt";
       List<Product> arr = IMethod.getListObject(fileName);
        if(arr.size() > 0){
            return  arr.get(arr.size()-1).getProductId() +1 ;
        }else {
            return 1 ;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getRootPrice() {
        return rootPrice;
    }

    public void setRootPrice(Double rootPrice) {
        this.rootPrice = rootPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
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
    public String getCategory(){
        List<Category> categories = IMethod.listCategory();
        int index = categories.stream().map(Category::getCateId).toList().indexOf(this.cateId);
        return categories.get(index).getCateName();
    }
    public void displayData(){
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-28s | %-3s | %-13s | %-3d | %-18s | %-8s | %-3d | %-18s |\n",productId,productName,discount+"%",format.format(finalPrice)+"VNĐ",inventory,size,color,cateId,status == 0 ?"Not sell" : (status == 1 ? "selling" : "out of stock"));
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
    }

//    public static void main(String[] args) {
//        Product product = new Product();
//        product.inputData();
//        product.displayData();
//    }
}
