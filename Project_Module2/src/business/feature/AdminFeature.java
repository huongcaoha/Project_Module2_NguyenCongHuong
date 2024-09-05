package business.feature;

import business.MyInterface.ICRUD;
import business.common.IMethod;
import business.entity.Admin;

import java.util.List;
import java.util.Scanner;

public class AdminFeature implements ICRUD <Admin>{
    String fileName = "listAdmin.txt";
    Scanner scanner = new Scanner(System.in);

    @Override
    public void displayList(List<Admin>list) {
        List<Admin> admins = IMethod.getListObject(fileName);
        if(admins != null){
            System.out.println("************************************* List Admin ************************************");
            for(Admin admin : admins){
                admin.displayData();
            }
        }else {
            System.out.println("List admin empty !");
        }
    }

    @Override
    public boolean add() {
        List<Admin> admins = IMethod.getListObject(fileName);
        int number = IMethod.getNumber("Enter number admin want add  : ");
        for(int i = 1 ; i <= number ; i++){
            System.out.println("Enter admin " + i);
            Admin admin = new Admin();
            admin.inputData(scanner);
            admins.add(admin);
            IMethod.saveDatabase(fileName,admins);
        }
        boolean rs = IMethod.saveDatabase(fileName,admins);
        if(rs){
            System.out.println("Add successfully " + number + " admin !");
        }else {
            System.err.println("Add error !");
        }
        return rs;
    }

    @Override
    public boolean update() {
        List<Admin> admins = IMethod.getListObject(fileName);
        int idAdmin = IMethod.getNumber("Enter admin id number  :");
       int  index = -1 ;
      for(int i = 0 ; i < admins.size() ; i++){
          if(admins.get(i).getAdminId() == idAdmin){
                index = i ;
                break;
          }
      }
        boolean rs = false ;
      if(index == -1){
          System.err.println("Not found admin !");
      }else {
          admins.get(index).updateData(scanner);
          rs  =  IMethod.saveDatabase(fileName,admins);
      }
      return rs ;
    }

    @Override
    public boolean delete() {
        List<Admin> admins = IMethod.getListObject(fileName);
        int idAdmin = IMethod.getNumber("Enter admin id number  :");
        int  index = -1 ;
        for(int i = 0 ; i < admins.size() ; i++){
            if(admins.get(i).getAdminId() == idAdmin){
                index = i ;
                break;
            }
        }
        boolean rs = false ;
        if(index == -1){
            System.err.println("Not found admin !");
        }else {
           admins.remove(index);
            rs  =  IMethod.saveDatabase(fileName,admins);
        }
        return rs ;
    }
}
