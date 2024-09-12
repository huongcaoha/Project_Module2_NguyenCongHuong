package business.feature;

import business.MyInterface.ICRUD;
import business.common.IMethod;
import business.entity.Product;
import business.util.GetColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductFeature implements ICRUD <Product> {
    String fileName = "listProduct.txt";
    Scanner scanner = new Scanner(System.in);
    @Override
    public void displayList(List<Product> products) {
        int currentPage = 1 ;
        int itemPerPage = 5 ;
        int totalPage = (int) Math.ceil((double) products.size() /itemPerPage);
        int skip = (currentPage -1 ) * itemPerPage ;
        int size = products.size();
        if(products.isEmpty()){
            System.err.println("List products is empty !");
        }else {
            while (true){
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|  "+GetColor.GREEN+"SHOP : IPHONE STORE"+GetColor.RESET+"                                   LIST PRODUCT                                                       |");
                System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
                System.out.printf("| %-3s | %-28s | %-3s | %-13s | %-3s | %-18s | %-8s | %-3s | %-18s |\n","ID","Product Name","-%","FinalPrice","Inv","Size","Color","CID","Status");
                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
                for(int i = skip ; i < (skip + itemPerPage) ; i++){
                    if(i < size){
                        products.get(i).displayData();
                    }else {
                        break;
                    }
                }
                StringBuilder pagination = new StringBuilder();
                int startPage;
                int endPage ;
                if(currentPage <= 3){
                    startPage = 1 ;
                    endPage = 5 ;
                }else if(currentPage + 2 <= totalPage){
                    startPage = currentPage - 2 ;
                    endPage = currentPage + 2 ;
                }else {
                    startPage = totalPage - 4 ;
                    endPage = totalPage;
                }
                for(int i = startPage ; i <= endPage ; i++){
                    if(i > totalPage){
                        break;
                    }
                    if(i < 1 ){
                        continue;
                    }
                    if(currentPage == i){
                        pagination.append(GetColor.RED + "[").append(i).append("]").append(GetColor.RESET);
                    }else {
                        pagination.append("[").append(i).append("]");
                    }

                    pagination.append("     ");

                }
                String rs ="|";
                int spaceStart = (132 - (pagination.length())) / 2 ;
                int spaceEnd = (132 - pagination.length()) - spaceStart ;
                for(int j = 1 ; j <= spaceStart ; j++){
                    rs += " ";
                }
                rs = rs.concat(pagination.toString());
                for(int j = 1 ; j <= spaceEnd ; j++){
                    rs += " ";
                }
                rs += "|";
                System.out.println(rs);
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|            1. Previous page             |                   2. Back                 |           3. Next page              |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

                int choice = IMethod.getNumber("Enter choice : ");
                switch (choice){
                    case 1 : {
                        if(currentPage > 1){
                            currentPage--;
                            skip = (currentPage -1 ) * itemPerPage ;
                        }else {
                            System.err.println("Cannot previous !");
                        }
                        break;
                    }
                    case 2 : {
                        return;
                    }
                    case 3 : {
                        if(currentPage < totalPage){
                            currentPage++;
                            skip = (currentPage -1 ) * itemPerPage ;
                        }else {
                            System.err.println("Cannot next !");
                        }
                        break;
                    }
                    default: {
                        System.out.println("Choice invalid !");
                    }
                }
            }
        }
    }
    public void searchProductById(){
        int idProduct = IMethod.getNumber("Enter id product to search : ");
        List<Product> products = IMethod.listProduct();
        int indexProduct = products.stream().map(Product::getProductId).toList().indexOf(idProduct);
        if(indexProduct == -1){
            System.err.println("Not found product id !");
        }else {
            List<Product> products1 = new ArrayList<>();
            products1.add(products.get(indexProduct));
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                      "+GetColor.GREEN+"RESULT SEARCH"+GetColor.RESET+"                                                        |");
            displayList(products1);
        }
    }

    @Override
    public boolean add() {
        List<Product> products = IMethod.getListObject(fileName);
        int number = IMethod.getNumber("Enter number product want add : ");
        for(int i = 1 ; i <= number ; i++){
            System.out.println("Enter product number " + i);
            Product product = new Product();
            product.inputData(scanner);
            products.add(product);
            IMethod.saveDatabase(fileName,products);
        }
       boolean rs = IMethod.saveDatabase(fileName,products);
        if(rs){
            System.out.println("Add successfully " + number + " product !");
            return  true ;
        }else {
            System.err.println("Add product error !");
            return false ;
        }
    }

    @Override
    public boolean update() {
        List<Product> products = IMethod.getListObject(fileName);
        int idProduct = IMethod.getNumber("Enter id product want update : ");
        int index = products.stream().map(Product::getProductId).toList().indexOf(idProduct);
        if(index == -1){
            System.err.println("Not found id product !");
            return false ;
        }else {
            products.get(index).updateData(scanner);
            IMethod.saveDatabase(fileName,products);
            System.out.println("Update product successfully !");
            return true ;
        }
    }

    @Override
    public boolean delete() {
        List<Product> products = IMethod.getListObject(fileName);
        int idProduct = IMethod.getNumber("Enter id product want delete : ");
        int index = products.stream().map(Product::getProductId).toList().indexOf(idProduct);
        if(index == -1){
            System.err.println("Not found id product !");
            return false ;
        }else {
            products.remove(index);
            IMethod.saveDatabase(fileName,products);
            System.out.println("Delete product successfully !");
            return true ;
        }
    }

    public void searchProduct(){
        List<Product> products = IMethod.getListObject(fileName);
        String search ;
        while (true){
            System.out.println("Enter key word or name product want search : ");
             search = scanner.nextLine().trim();
            if(search.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
        String finalSearch = search.toLowerCase();
        List<Product> rs = products.stream().filter(product -> product.getProductName().toLowerCase().contains(finalSearch) || product.getDescription().toLowerCase().contains(finalSearch)).toList();
        if(rs.isEmpty()){
            System.err.println("List product empty or not found name product !");
        }else {
            System.out.println("Result search product :");
            displayList(rs);
        }
    }

    public void sortProduct(){
        List<Product> products = IMethod.getListObject(fileName);
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("|              1. Sort price in descending order               |              2. Sort price in ascending order           |");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        int choice = 0 ;
        while (true){
            try {
                System.out.println("Enter choice :");
                choice = Integer.parseInt(scanner.nextLine().trim());
                if(choice == 1 || choice == 2 ){
                    break;
                }else {
                    System.err.println("Enter choice 1 or 2 !");
                }
            }catch (NumberFormatException e){
                System.err.println("Choice invalid !");
            }
        }
        List<Product> rs = new ArrayList<>();
        if(choice == 1){
            rs = products.stream().sorted((pro1,pro2) -> pro1.getFinalPrice().compareTo(pro2.getFinalPrice())).toList();
        }else {
            rs = products.stream().sorted((pro1,pro2) -> pro2.getFinalPrice().compareTo(pro1.getFinalPrice())).toList();
        }
        if(rs.isEmpty()){
            System.err.println("List product empty or not found product !");
        }else {
            System.out.println("Result search product : ");
            displayList(rs);
        }
    }
}
