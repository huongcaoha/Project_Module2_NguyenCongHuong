package business.feature;

import business.common.IMethod;
import business.entity.Customer;
import business.entity.Order;
import business.entity.Product;
import business.entity.ProductCart;
import business.util.GetColor;
import presentation.managementSystem.Shop;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PaymentFeature {
    public static void paymentPage(List<ProductCart> productCarts,boolean deleteCart){
        List<Customer> checkLogin = IMethod.checkLogin();
        if(checkLogin.getFirst() == null){
            System.err.println("Please log in first !");
        }else {
            Customer customer = checkLogin.getFirst();
            int currentPage = 1 ;
            int itemPerPage = 5 ;
            while (true){
                int skip = (currentPage -1 ) * itemPerPage ;
                int totalPage = (int) Math.ceil((double) productCarts.size() /itemPerPage);
                int size = productCarts.size();
                int sumMoney = 0 ;
                for(ProductCart productCart : productCarts){
                    sumMoney += productCart.getTotalMoney();
                }
                if(productCarts.isEmpty()){
                    System.err.println("Cart empty !");
                    break;
                }else {
                    displayCart(skip, itemPerPage, size, productCarts, currentPage, totalPage, sumMoney);
                    int choice = IMethod.getNumber("Enter your choice : ");
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
                            String phoneNumber ;
                            String address ;
                            String customerName ;
                            while (true){
                                System.out.println("Enter the consignee's name : ");
                                customerName = IMethod.scanner.nextLine().trim();
                                if(customerName.isEmpty()){
                                    System.err.println("Cannot be left blank !");
                                }else {
                                    break;
                                }
                            }

                            while (true){
                                System.out.println("Enter address : ");
                                address = IMethod.scanner.nextLine().trim();
                                if(address.isEmpty()){
                                    System.err.println("Cannot be left blank !");
                                }else {
                                    break;
                                }
                            }

                            while (true){
                                System.out.println("Enter phone number : ");
                                phoneNumber = IMethod.scanner.nextLine().trim();
                                if(phoneNumber.matches("^0[35789][0-9]{8}$")){
                                    break;
                                }else {
                                    System.err.println("Phone number invalid !");
                                }
                            }
                            int customerId = IMethod.checkLogin().getFirst().getCustomerId();
                            Order order = new Order(customerName,customerId,phoneNumber,address,productCarts, (double) sumMoney);
                            List<Order> orders = IMethod.listOrder();
                            orders.add(order);
                            IMethod.saveDatabase(IMethod.fileOrder,orders);
                            List<Product> products = IMethod.listProduct();
                            for(ProductCart pro : productCarts){
                                int indexProduct = products.stream().map(Product::getProductName).toList().indexOf(pro.getProductName());
                                products.get(indexProduct).setInventory(products.get(indexProduct).getInventory()-pro.getQuantity());
                            }
                            IMethod.saveDatabase(IMethod.fileProduct,products);

                            List<ProductCart> totalProductCart = IMethod.listProductCart();
                            if(deleteCart){
                                for(int i = 0 ; i < totalProductCart.size() ; i++){
                                   if(totalProductCart.get(i).getCustomerId() == customerId){
                                       totalProductCart.remove(i);
                                   }
                                }
                                IMethod.saveDatabase(IMethod.fileProductCart,totalProductCart);
                            }
                            System.out.println("Order successful !");
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
                            return;
                        }
                        default: {
                            System.err.println("Enter choice from 1 to 6 !");
                        }
                    }
                }
            }
        }

    }

    private static void displayCart(int skip, int itemPerPage, int size, List<ProductCart> productCarts, int currentPage, int totalPage, int sumMoney) {

        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("|                                                             LIST PRODUCT CART                                                   |");
        System.out.println("|━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.printf("| %-3s | %-28s | %-13s | %-8s | %-8s | %-13s | %-13s | %-20s |\n","ID","Product Name","finalPrice","CateId","Quantity","Size","Color","TotalMoney");
        System.out.println("|━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━|");
        for(int i = skip; i < (skip + itemPerPage) ; i++){
            if(i < size){
                productCarts.get(i).displayData();
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
        int spaceStart = (138 - (pagination.length())) / 2 ;
        int spaceEnd = (138 - pagination.length()) - spaceStart ;
        for(int j = 1 ; j <= spaceStart ; j++){
            rs += " ";
        }
        rs = rs.concat(pagination.toString());
        for(int j = 1 ; j <= spaceEnd ; j++){
            rs += " ";
        }
        rs += "|";
        System.out.println(rs);
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.printf("|                                                                                     Total money : %-29s |\n",format.format(sumMoney)+" VNĐ");
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.println("|         1. Previous           |            2. Payment          |             3. Next            |           4. Back             |");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}
