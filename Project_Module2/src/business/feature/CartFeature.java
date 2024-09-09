package business.feature;

import business.common.IMethod;
import business.entity.Customer;
import business.entity.Product;
import business.entity.ProductCart;
import business.util.GetColor;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CartFeature {
    public static void viewCart(Customer customer){
        List<Customer> checkLogin = IMethod.checkLogin();
        if(checkLogin.getFirst() == null){
            System.err.println("Please log in first !");
        }else {

            int currentPage = 1 ;
            int itemPerPage = 5 ;
            while (true){
                List<ProductCart> productCarts =IMethod.listProductCart().stream().filter(productCart -> Objects.equals(productCart.getCustomerId(), customer.getCustomerId())).toList();
                int skip = (currentPage -1 ) * itemPerPage ;
                int totalPage = (int) Math.ceil((double) productCarts.size() /itemPerPage);
                int size = productCarts.size();
                productCarts =IMethod.listProductCart().stream().filter(productCart -> Objects.equals(productCart.getCustomerId(), customer.getCustomerId())).toList();
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
                            updateQuantityProduct(productCarts);
                            break;
                        }
                        case 5 : {
                            int idProductCart = IMethod.getNumber("Enter id product cart want delete : ");
                            List<ProductCart> carts = IMethod.listProductCart();
                            int index = productCarts.stream().map(ProductCart::getProductCartId).toList().indexOf(idProductCart);
                            if(index == -1){
                                System.err.println("Not found id product !");
                            }else {
                                index = carts.stream().map(ProductCart::getProductCartId).toList().indexOf(idProductCart);
                                carts.remove(index);
                                IMethod.saveDatabase(IMethod.fileProductCart,carts);
                                currentPage = 1 ;
                                System.out.println("Delete product cart successfully !");
                            }
                            break;
                        }
                        case 6 : {
                            List<ProductCart> productCarts1 = IMethod.listProductCart();
                            productCarts1.clear();
                            IMethod.saveDatabase(IMethod.fileProductCart,productCarts1);
                            System.out.println("Delete all product cart success !");
                            break;
                        }
                        case 7 : {
                            boolean check = true ;
                            List<Product> products = IMethod.listProduct();
                            for(ProductCart productCart : productCarts){
                                int indexProduct = products.stream().map(Product::getProductName).toList().indexOf(productCart.getProductName());
                                if (productCart.getQuantity() > products.get(indexProduct).getInventory()){
                                    System.err.println("Cannot checkout because quantity > inventory !");
                                   check = false ;
                                }
                            }
                           if(check){
                               PaymentFeature.paymentPage(productCarts,true);
                           }
                           break;
                        }
                        default: {
                            System.err.println("Enter choice from 1 to 6 !");
                        }
                    }
                }
            }
        }

    }

    private static void updateQuantityProduct(List<ProductCart> productCarts) {
        List<Product> products = IMethod.listProduct();
        int idProductCart = IMethod.getNumber("Enter id product cart want update : ");
        List<ProductCart> carts = IMethod.listProductCart();
        int index = productCarts.stream().map(ProductCart::getProductCartId).toList().indexOf(idProductCart);
        if(index == -1){
            System.err.println("Not found id product !");
        }else {
            index = carts.stream().map(ProductCart::getProductCartId).toList().indexOf(idProductCart);
            int indexProduct = products.stream().map(Product::getProductName).toList().indexOf(carts.get(index).getProductName());
            int inventory = products.get(indexProduct).getInventory();
            int quantity = IMethod.getNumber("Enter quantity want update : ");
            while (quantity > inventory){
                System.err.println("Quantity product > inventory !");
                quantity = IMethod.getNumber("Enter quantity want update : ");
            }
            carts.get(index).setQuantity(quantity);
            IMethod.saveDatabase(IMethod.fileProductCart,carts);
            System.out.println("Update quantity product successfully !");
        }
    }
    private static void displayCart(int skip, int itemPerPage, int size, List<ProductCart> productCarts, int currentPage, int totalPage, int sumMoney) {

        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("|                                                             CART                                                                |");
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
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.println("|             1. Previous                   |              2. Back                        |               3. Next                 |");
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.println("|                      4. Update quantity product                    |                        5. Delete one product               |");
        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
        System.out.println("|                      6. Delete all product                         |                        7. Proceed to order                 |");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}
