package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Order;
import business.feature.OrderFeature;

import java.util.List;

public class OrderManagement {
    public static void main(String[] args) {
        OrderFeature orderFeature = new OrderFeature();
        String fileName = "listOrder.txt";
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━ ORDER MANAGEMENT ━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("|      1. Display list orders   |       2. Search order by id         |      3. Search order by status    |  4. See order detail by id   |");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("|      5. Update status order   |            6. Cancel order          |   7. Search order by day a -> b   |           8. Back            |");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    List<Order> orders = IMethod.getListObject(fileName);
                    orderFeature.displayList(orders);
                    break;
                }
                case 2 : {
                    orderFeature.searchById(IMethod.listOrder());
                    break;
                }
                case 3 : {
                    orderFeature.searchByStatus(IMethod.listOrder());
                    break;
                }
                case 4 : {
                    orderFeature.seeOrderDetail();
                    break;
                }
                case 5 : {
                    orderFeature.updateStatusOrder();
                    break;
                }
                case 6 : {
                    orderFeature.cancelOrder();
                    break;
                }
                case 7 : {
                    orderFeature.searchOrderByDay(IMethod.listOrder());
                    break;
                }
                case 8 : {
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }
}
