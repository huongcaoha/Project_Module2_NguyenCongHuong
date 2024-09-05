package presentation.managementSystem;

import business.common.IMethod;
import business.entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class StatisticalManagement {
    public static void main(String[] args) {
        List<Category> categories = IMethod.listCategory();
        List<Product> products = IMethod.listProduct();
        List<Order> orders = IMethod.listOrder();
        List<Customer> customers = IMethod.listCustomer();
        List<ProductCart> productCarts = IMethod.listProductCart();
        int totalCategory = categories.size();
        int totalProduct = products.size();
        int totalOrder = orders.size();
        int totalCustomer = customers.size();
        Integer currentMonth = LocalDate.now().getMonthValue();
        Date date = new Date(); // Lấy thời gian hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("MM"); // Định dạng tháng
        double salesMonth = orders.stream()
                .filter(order -> Boolean.parseBoolean(sdf.format(order.getCreatedDate()
                        .equals(currentMonth.toString()))))
                .toList().stream().map(Order::getTotalMoney).reduce((double) 0,(pre, current) -> pre + current);
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                     STATISTICAL                                                      |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|           Total Categories :           |          Total Products :          |               Total Orders :           |");
            System.out.printf("|                 %-12d Category |                %-12d Product |                %-15d Order |",totalCategory,totalProduct,totalOrder);
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|             Total Customers :          |          Sales this month :        |           1. View monthly sales        |");
            System.out.printf("|                %-13d Customer |                %-16.1f VNĐ |                                       |",totalCustomer,salesMonth);
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("|                                                        2. Exit                                                       |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    int currentYear = LocalDate.now().getYear();
                    String title = "Year : " + currentYear ;
                    System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                    System.out.printf("|                                                 %15s                                                      |",title);
                    System.out.println("┏━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                    System.out.println("|    Month          |     Total order     |   Total order cancel   |   Total order success  |        Total money       |");
                    System.out.println("┗━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                    List<Order> rs = orders.stream().filter(order -> {
                        LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                        return localDate.getYear() == currentYear;
                    }).toList();
                    for(int i = 1 ; i <= 12 ; i++){
                        String month = "Month " + i ;
                        int finalI = i;
                        int orderTotal = rs.stream().filter(order -> {
                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                            return localDate.getMonthValue() == finalI;
                        }).toList().size();

                        int orderCancel = rs.stream().filter(order -> {
                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                            return localDate.getMonthValue() == finalI &&  order.getStatus() == 0;
                        }).toList().size();

                        int orderSuccess = rs.stream().filter(order -> {
                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                            return localDate.getMonthValue() == finalI &&  order.getStatus() == 4;
                        }).toList().size();

                        double totalMoney = rs.stream().filter(order -> {
                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                            return localDate.getMonthValue() == finalI &&  order.getStatus() == 4;
                        }).toList().stream().map(Order::getTotalMoney).reduce((double) 0, Double::sum);

                        System.out.println("┏━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                        System.out.printf("| %-17s | %-19d | %-22d | %-22d | %-23.1f |",month,orderTotal,orderCancel,orderSuccess,totalMoney);
                        System.out.println("┗━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                     }
                    break;
                }
                case 2 : {
                    return;
                }
                default: {
                    System.err.println("Enter choice 1 or 2 !");
                }
            }
        }
    }
}
