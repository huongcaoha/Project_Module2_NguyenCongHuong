package business.feature;

import business.MyInterface.ICRUD;
import business.common.IMethod;
import business.entity.Category;
import business.entity.Product;
import business.util.GetColor;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CategoryFeature implements ICRUD <Category> {
    Scanner scanner = new Scanner(System.in);
    String fileName = "listCategory.txt";

    @Override
    public void displayList(List<Category> categories) {
        int currentPage = 1 ;
        int catePerPage = 5 ;
        int totalPage = (int) Math.ceil((double) categories.size() /catePerPage);
        int skip = (currentPage -1 ) * catePerPage ;
        int size = categories.size();
        if(categories.isEmpty()){
            System.err.println("List category is empty !");
        }else {
           while (true){
               System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
               System.out.println("|                                   "+GetColor.GREEN+"LIST CATEGORIES"+GetColor.RESET+"                                 |");
               System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
               System.out.printf("| %-8s | %-38s | %-8s | %-18s |\n" ,"ID" , "Category Name" , "Status","createdDate");
               System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
               for(int i = skip ; i < (skip + catePerPage) ; i++){
                   if(i < size){
                       categories.get(i).displayData();
                   }else {
                       break;
                   }
               }
               StringBuilder pagination = new StringBuilder();
               int startPage = Math.max(currentPage - 2, 1);
               int endPage = Math.min(currentPage + 2, totalPage);
               for(int i = startPage ; i <= endPage ; i++){
                   if(currentPage == i){
                       pagination.append(GetColor.RED +"["+ i +"]"+ GetColor.RESET) ;
                   }else {
                       pagination.append("[" + i + "]") ;
                   }

                       pagination.append("     ");

               }
               String rs ="|";
               int spaceStart = (92 - (pagination.length())) / 2 ;
               int spaceEnd = (92 - pagination.length()) - spaceStart ;
               for(int j = 1 ; j <= spaceStart ; j++){
                   rs += " ";
               }
              rs = rs.concat(pagination.toString());
               for(int j = 1 ; j <= spaceEnd ; j++){
                   rs += " ";
               }
               rs += "|";
               System.out.println(rs);
               System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
               System.out.println("|     1. Previous page       |         2. Back          |        3. Next page       |");
               System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

               int choice = IMethod.getNumber("Enter choice : ");
               switch (choice){
                   case 1 : {
                       if(currentPage > 1){
                           currentPage--;
                           skip = (currentPage -1 ) * catePerPage ;
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
                           skip = (currentPage -1 ) * catePerPage ;
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


    @Override
    public boolean add() {
        List<Category> categories = IMethod.getListObject(fileName);
        int number = IMethod.getNumber("Enter number category want add : ");
        for(int i = 1 ; i <= number ; i++){
            System.out.println("Enter category " + i);
            Category category = new Category();
            category.inputData(scanner);
            categories.add(category);
            IMethod.saveDatabase(fileName,categories);
        }
        boolean rs = IMethod.saveDatabase(fileName,categories);
        if(rs){
            System.out.println("Add successfully " + number + " category !");
            return true ;
        }else {
            System.out.println("Add error !");
            return false ;
        }
    }

    @Override
    public boolean update() {
        List<Category> categories = IMethod.getListObject(fileName);
        int idCate = IMethod.getNumber("Enter category id to update :");
        int  index = -1 ;
        for(int i = 0 ; i < categories.size() ; i++){
            if(categories.get(i).getCateId() == idCate){
                index = i ;
                break;
            }
        }
        boolean rs = false ;
        if(index == -1){
            System.err.println("Not found category !");
        }else {
            categories.get(index).updateData(scanner);
            rs  =  IMethod.saveDatabase(fileName,categories);
        }
        return rs ;
    }

    @Override
    public boolean delete() {
        List<Category> categories = IMethod.getListObject(fileName);
        int idCate = IMethod.getNumber("Enter category id number to delete :");
        int  index = -1 ;
        for(int i = 0 ; i < categories.size() ; i++){
            if(categories.get(i).getCateId() == idCate){
                index = i ;
                break;
            }
        }
        boolean rs = false ;
        if(index == -1){
            System.err.println("Not found category !");
        }else {
            List<Product> products = IMethod.listProduct();
            int idCatalog = categories.get(index).getCateId();
            int checkExist = products.stream().map(Product::getCateId).toList().indexOf(idCatalog);
            String catalogName = categories.get(index).getCateName();
            if(checkExist == -1){
                categories.remove(index);
                rs  =  IMethod.saveDatabase(fileName,categories);
                System.out.println("Delete success catalog : " + catalogName);
            }else {
                System.err.println("Cannot delete catalog : " + catalogName + " because this catalog exist product !");
            }
        }
        return rs ;
    }

    public void searchCateByName(){
        List<Category> categories = IMethod.getListObject(fileName);
        String search = "";
        while (true){
            System.out.println("Enter name categories want search : ");
            search = scanner.nextLine().trim();
            if(search.isEmpty()){
                System.err.println("Cannot be left blank !");
            }else {
                break;
            }
        }
        String finalSearch = search;
        List<Category> rs = categories.stream().filter(cate -> cate.getCateName().contains(finalSearch)).toList();
        if(rs.isEmpty()){
            System.err.println("Not found categories with key word :" + search);
        }else {
            System.out.println("Result search of key word : " + search);
            displayList(rs);
        }
    }

    public void sortCategories (){
        List<Category> categories = IMethod.getListObject(fileName);
        List<Category> newCate = categories.stream().sorted(Comparator.comparing(Category::getCateName)).toList();
        System.out.println("List categories after sort :");
        displayList(newCate);
    }

}






