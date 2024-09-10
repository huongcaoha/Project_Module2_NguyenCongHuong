package business.feature;

import business.common.IMethod;
import business.entity.*;
import business.util.GetColor;
import presentation.managementSystem.Shop;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PaymentFeature {
    public void paymentPage(List<ProductCart> productCarts,boolean deleteCart){
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("|                                                         "+GetColor.GREEN+"PAGE : PAYMENT"+GetColor.RESET+"                                                          |");
        List<Customer> checkLogin = IMethod.checkLogin();
        if(checkLogin.getFirst() == null){
            System.err.println("Please log in first !");
        }else {
            Customer customer = checkLogin.getFirst();
            int currentPage = 1 ;
            int itemPerPage = 5 ;
            while (true){
                customer = IMethod.checkLogin().getFirst();
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
                            customer = IMethod.checkLogin().getFirst();
                            if(customer.getAddresses().isEmpty()){
                                System.out.println("Please enter a new address : ");
                                Address address = new Address();
                                address.inputData(IMethod.scanner,customer.getAddresses());
                                customer.getAddresses().add(address);
                                checkLogin.set(0,customer);
                                IMethod.saveDatabase(IMethod.fileCheckLogin,checkLogin);
                                IMethod.saveAddress(customer,customer.getAddresses());
                            }
                            AddressFeature addressFeature = new AddressFeature();
                            addressFeature.display();
                            int idAddress = -1 ;
                            int indexAddress  = -1;
                            while (true){
                                 idAddress = IMethod.getNumber("Enter id address to receive : ");
                                 indexAddress = customer.getAddresses().stream().map(Address::getAddressId).toList().indexOf(idAddress);
                                 if(indexAddress == -1){
                                     System.err.println("Not found id address !");
                                 }else {
                                     break;
                                 }
                            }
                            Address address = customer.getAddresses().get(indexAddress);
                            int customerId = IMethod.checkLogin().getFirst().getCustomerId();
                            Order order = new Order(address.getReceiveName(),customerId,address.getPhoneNumber(),address.getAddressName(),productCarts, (double) sumMoney);
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

    private void displayCart(int skip, int itemPerPage, int size, List<ProductCart> productCarts, int currentPage, int totalPage, int sumMoney) {

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
        System.out.println("|         1. Previous           |            2. Checkout         |             3. Next            |           4. Back             |");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}
