package business.feature;

import business.MyInterface.ICRUD;
import business.common.IMethod;
import business.entity.Order;
import business.util.GetColor;

import java.text.NumberFormat;
import java.util.*;

public class OrderFeature implements ICRUD <Order> {
    String fileName = "listOrder.txt";
    Scanner scanner = new Scanner(System.in);
    @Override
    public void displayList(List<Order> orders) {
        int currentPage = 1 ;
        int itemPerPage = 5 ;
        int totalPage = (int) Math.ceil((double) orders.size() /itemPerPage);
        int skip = (currentPage -1 ) * itemPerPage ;
        int size = orders.size();
        if(orders.isEmpty()){
            System.err.println("List orders is empty !");
        }else {
           while (true){
               System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
               System.out.println("|                                                              LIST ORDERS                                                               |");
               System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
               System.out.printf("| %-3s | %-28s | %-13s | %-28s | %-18s | %-13s | %-13s |\n" ,"ID","Customer Name","Phone Number","Address","Total Money","Status","Created Date");
               System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");

               for(int i = skip ; i < (skip + itemPerPage) ; i++){
                   if(i < size){
                       orders.get(i).displayData();
                   }else {
                       break;
                   }
               }
               StringBuilder pagination = new StringBuilder();
               int startPage = Math.max(currentPage - 2, 1);
               int endPage = Math.min(currentPage + 2, totalPage);
               for(int i = startPage ; i <= endPage ; i++){
                   if(currentPage == i){
                       pagination.append(GetColor.RED + "[").append(i).append("]").append(GetColor.RESET);
                   }else {
                       pagination.append("[").append(i).append("]");
                   }

                   pagination.append("     ");
               }
               String rs ="|";
               int spaceStart = (130 - (pagination.length())) / 2 ;
               int spaceEnd = (130 - pagination.length()) - spaceStart ;
               for(int j = 1 ; j <= spaceStart ; j++){
                   rs += " ";
               }
               rs = rs.concat(pagination.toString());
               for(int j = 1 ; j <= spaceEnd ; j++){
                   rs += " ";
               }
               rs += "|";
               System.out.println(rs);
               System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
               System.out.println("|                  1. Previous                |                      2. Back                   |                  3. Next                |");
               System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

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

    public void searchById(){
        List<Order> orders = IMethod.getListObject(fileName);
        Integer orderId ;
        while (true){
            try {
                System.out.println("Enter number id order to search : ");
                orderId = Integer.parseInt(scanner.nextLine().trim());
                if(orderId <= 0){
                    System.err.println("Enter order id > 0 !");
                }else {
                    break;
                }
            }catch (NumberFormatException n){
                System.err.println("Order id invalid !");
            }
        }
        Integer finalOrderId = orderId;
        Order rs = orders.stream().filter(order -> Objects.equals(order.getOrderId(), finalOrderId)).findFirst().orElse(null);
        if(rs == null){
            System.err.println("Not found order id !");
        }else {
            System.out.println("Result search is : ");
            rs.displayData();
        }
    }

    public void searchByStatus(){
        List<Order> orders = IMethod.getListObject(fileName);
        Integer status ;
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("|       0. Cancel        |       1. Ordered       |       2. Confirm       |      3. Delivering      |      4. Delivered       |");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        while (true){
            try {
                System.out.println("Enter status want search : ");
                status = Integer.parseInt(scanner.nextLine().trim());
                if(status >= 0 && status < 5){
                    break;
                }else {
                    System.err.println("Enter status from 0 to 4 !");
                }
            }catch (NumberFormatException n){
                System.err.println("Status invalid !");
            }
        }
        Integer finalStatus = status;
        List<Order> rs = orders.stream().filter(order -> Objects.equals(order.getStatus(), finalStatus)).toList();
        if(rs.isEmpty()){
            System.err.println("Not found order with status = " + status);
        }else {
            System.out.println("Result search status = " + status);
            displayList(rs);
        }
    }

    public void seeOrderDetail(){
        List<Order> orders = IMethod.getListObject(fileName);
        int orderId ;
        while (true){
            try {
                System.out.println("Enter number id order : ");
                orderId = Integer.parseInt(scanner.nextLine().trim());
                if(orderId <= 0){
                    System.err.println("Enter order id > 0 !");
                }else {
                    break;
                }
            }catch (NumberFormatException n){
                System.err.println("Order id invalid !");
            }
        }
        Integer finalOrderId = orderId;
        Order rs = orders.stream().filter(order -> Objects.equals(order.getOrderId(), finalOrderId)).findFirst().orElse(null);
        if(rs == null){
            System.err.println("Not found order id !");
        }else {
            ProductCartFeature productCartFeature = new ProductCartFeature();
            productCartFeature.displayList(rs.getCarts());
            System.out.println("Total money : " + rs.getTotalMoney());
        }

    }

    public void updateStatusOrder(){
        List<Order> orders = IMethod.getListObject(fileName);
        int orderId ;
        while (true){
            try {
                System.out.println("Enter number id order to update status : ");
                orderId = Integer.parseInt(scanner.nextLine().trim());
                if(orderId <= 0){
                    System.err.println("Enter order id > 0 !");
                }else {
                    break;
                }
            }catch (NumberFormatException n){
                System.err.println("Order id invalid !");
            }
        }
        int index = orders.stream().map(Order::getOrderId).toList().indexOf(orderId);
        if(index == -1){
            System.err.println("Not found order id !");
        }else {
          boolean isSuccess = orders.get(index).updateStatus();
          if(isSuccess){
              System.out.println("Update successfully !");
              IMethod.saveDatabase(fileName,orders);
          }else {
              System.err.println("Update error !");
          }
        }
    }

    @Override
    public boolean add() {
        return false ;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
