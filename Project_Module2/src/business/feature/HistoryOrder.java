package business.feature;

import business.common.IMethod;
import business.entity.Customer;
import business.entity.Order;
import business.util.GetColor;

import java.util.List;
import java.util.Objects;

public class HistoryOrder {
    public static void seeOrderDetail(){
        Customer customer = IMethod.checkLogin().getFirst();
        List<Order> orders = IMethod.listOrder().stream().filter(order -> Objects.equals(order.getCustomerId(), customer.getCustomerId())).toList();
        int orderId = IMethod.getNumber("Enter order id : ");
        Order rs = orders.stream().filter(order -> Objects.equals(order.getOrderId(), orderId)).findFirst().orElse(null);
        if(rs == null){
            System.err.println("Not found order id !");
        }else {
            ProductCartFeature productCartFeature = new ProductCartFeature();
            productCartFeature.displayList(rs.getCarts());
        }
    }
    public static void displayList(Customer customer) {
        OrderFeature orderFeature = new OrderFeature();
        List<Order> orders = IMethod.listOrder().stream().filter(order -> Objects.equals(order.getCustomerId(), customer.getCustomerId())).toList();
        int currentPage = 1 ;
        int itemPerPage = 5 ;
        int totalPage = (int) Math.ceil((double) orders.size() /itemPerPage);
        int skip = (currentPage -1 ) * itemPerPage ;
        int size = orders.size();
        if(orders.isEmpty()){
            System.err.println("List orders is empty !");
        }else {
            while (true){
                orders = IMethod.listOrder().stream().filter(order -> Objects.equals(order.getCustomerId(), customer.getCustomerId())).toList();
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
                int spaceStart = (145 - (pagination.length())) / 2 ;
                int spaceEnd = (145 - pagination.length()) - spaceStart ;
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
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|           4. Search order by id             |           5. Search order by day a -> b        |         6. Search order by status       |");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|         7. See order detail by id           |                   8. Cancel order              |           9. Update status order        |");
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
                    case 4 : {
                        orderFeature.searchById(orders);
                        break;
                    }
                    case 5 : {
                        orderFeature.searchOrderByDay(orders);
                        break;
                    }
                    case 6 : {
                       orderFeature.searchByStatus(orders);
                        break;
                    }
                    case 7 : {
                        seeOrderDetail();
                        break;
                    }
                    case 8 : {
                        List<Order> orders1 = IMethod.listOrder();
                        int idOrder = IMethod.getNumber("Enter id order want cancel : ");
                        int indexOrder = orders.stream().map(Order::getOrderId).toList().indexOf(idOrder);
                        if(indexOrder == -1){
                            System.err.println("Not found id order !");
                        }else {
                            indexOrder = orders1.stream().map(Order::getOrderId).toList().indexOf(idOrder);
                            if(orders1.get(indexOrder).getStatus() == 1){
                                orders1.get(indexOrder).setStatus(0);
                                IMethod.saveDatabase(IMethod.fileOrder,orders1);
                                System.out.println("Cancel order success !");
                            }else {
                                System.err.println("Cannot cancel order because order status is : " + orders1.get(indexOrder).printStatus(orders1.get(indexOrder).getStatus()));
                            }
                        }
                        break;
                    }
                    case 9 : {
                        List<Order> orders1 = IMethod.listOrder();
                        int idOrder = IMethod.getNumber("Enter id order want update : ");
                        int indexOrder = orders.stream().map(Order::getOrderId).toList().indexOf(idOrder);
                        if(indexOrder == -1){
                            System.err.println("Not found id order !");
                        }else {
                            indexOrder = orders1.stream().map(Order::getOrderId).toList().indexOf(idOrder);
                            if(orders1.get(indexOrder).getStatus() == 3){
                                orders1.get(indexOrder).setStatus(4);
                                IMethod.saveDatabase(IMethod.fileOrder,orders1);
                                System.out.println("update order status delivered success !");
                            }else {
                                System.err.println("Cannot update order because order status is : " + orders1.get(indexOrder).printStatus(orders1.get(indexOrder).getStatus()));
                            }
                        }
                        break;
                    }
                    default: {
                        System.err.println("Enter choice from 1 to 9 !");
                    }
                }

            }
        }
    }
}
