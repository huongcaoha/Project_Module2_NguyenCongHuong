package business.feature;

import business.common.IMethod;
import business.entity.*;
import business.util.GetColor;
import presentation.managementSystem.AdminManagement;
import presentation.managementSystem.Login;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShopFeature {
    public static void displayList(String[] args ,List<Product> products){
        ProductFeature productFeature = new ProductFeature();
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
                System.out.println("|                                                        LIST PRODUCT                                                       |");
                System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
                System.out.printf("[ %-3s | %-28s | %-3s | %-13s | %-3s | %-18s | %-8s | %-3s | %-18s ]\n","ID","Product Name","-%","FinalPrice","Inv","Size","Color","CID","Status");
                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
                for(int i = skip ; i < (skip + itemPerPage) ; i++){
                    if(i < size){
                        products.get(i).displayData();
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
                System.out.println("|               1. Previous               |                   2. Back                 |              3. Next                |");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.println("|           4. Search Product             |            5. See product detail          |             6. Sort product         |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

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
                        productFeature.searchProduct();
                        break;
                    }
                    case 5 : {
                        List<Customer> checkLogin = IMethod.checkLogin();
                        List<Product> products1 = IMethod.listProduct();
                        int idProduct = IMethod.getNumber("Enter id product : ");
                        int index = products1.stream().map(Product::getProductId).toList().indexOf(idProduct);
                        if(index == -1){
                            System.err.println("Not found id product !");
                        }else {
                            NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
                            Product product = products1.get(index);
                            int width = 112 ;
                            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                            System.out.printf("|                                                  %-60s|\n",product.getProductName());
                            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                            System.out.printf("| - %-107s|\n","Price : "+format.format(product.getFinalPrice()) + "VNĐ");
                            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                            System.out.printf("| - %-107s|\n","Discount : " + product.getDiscount() + " %");
                            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                            System.out.printf("| - %-107s|\n","Inventory : " + product.getInventory());
                            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                            System.out.printf("| - %-107s|\n","Size : " + product.getSize());
                            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                            System.out.printf("| - %-107s|\n","Color : " + product.getColor());
                            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                            System.out.printf("| - %-107s|\n","Description : " + product.getDescription());
                            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                            System.out.printf("| - %-107s|\n","Category : " + product.getCategory());
                            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
                            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                            System.out.println("|   1. add to favorites    |       2. Add to cart      |         3. Buy now        |          4 . Back         |");
                            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
                            int select = IMethod.getNumber("Enter choice : ");
                            int idCustomer = -1 ;
                            if(IMethod.checkLogin().getFirst() != null){
                                idCustomer = IMethod.checkLogin().stream().map(Customer::getCustomerId).toList().getFirst();
                            }
                            switch (select){

                                case 1 : {
                                    if(checkLogin.getFirst() == null){
                                        System.err.println("Please log in first !");
                                        Login.main(args);
                                    }
                                    List<FavoriteProduct> favoriteProducts = IMethod.listFavoriteProduct();
                                    FavoriteProduct favoriteProduct = new FavoriteProduct(product,idCustomer);
                                    favoriteProducts.add(favoriteProduct);
                                   boolean result = IMethod.saveDatabase("listFavoriteProduct.txt",favoriteProducts);
                                    if(result){
                                        System.out.println("Add favorite product successfully !");
                                    }else {
                                        System.err.println("Add favorite product error !");
                                    }
                                    break;
                                }
                                case 2 : {
                                    if(checkLogin.getFirst() == null){
                                        System.err.println("Please log in first !");
                                        Login.main(args);
                                    }
                                    List<ProductCart> listCarts = IMethod.listProductCart();
                                    int quantity = IMethod.getNumber("Enter quantity : ");
                                    int indexInCart = listCarts.stream().map(ProductCart::getProductName).toList().indexOf(product.getProductName());
                                    if(indexInCart == -1){
                                        ProductCart productCart = new ProductCart(idCustomer,product.getProductName(),product.getFinalPrice(),product.getSize(),product.getColor(),product.getCateId(),quantity,product.getFinalPrice()*quantity);
                                        listCarts.add(productCart);
                                    }else {
                                        listCarts.get(index).setQuantity(listCarts.get(index).getQuantity()+quantity);
                                    }
                                    boolean result = IMethod.saveDatabase("listProductCart.txt",listCarts);
                                    if(result){
                                        System.out.println("Add to cart successfully !");
                                    }else {
                                        System.err.println("Add to cart error !");
                                    }
                                    break;
                                }
                                case 3 : {
                                    if(checkLogin.getFirst() == null){
                                        System.err.println("Please log in first !");
                                        Login.main(args);
                                    }
                                    int quantity = IMethod.getNumber("Enter quantity : ");
                                    ProductCart productCart = new ProductCart(idCustomer,product.getProductName(),product.getFinalPrice(),product.getSize(),product.getColor(),product.getCateId(),quantity,product.getFinalPrice()*quantity);
                                    List<ProductCart> listCarts = new ArrayList<>();
                                    listCarts.add(productCart);
                                    Payment.payment(listCarts);
                                    break;
                                }
                                case 4 : {
                                    return;
                                }
                                default: {
                                    System.err.println("Enter choice from 1 to 4 !");
                                }
                            }

                        }
                        break;
                    }
                    case 6 : {
                        productFeature.sortProduct();
                        break;
                    }
                    default: {
                        System.out.println("Choice invalid !");
                    }
                }
            }
        }
    }

    public static void viewInformation(){
        List<Customer> checkLogin = IMethod.checkLogin();
        Customer customer = checkLogin.getFirst();
        if(checkLogin.getFirst() == null){
            System.err.println("Please log in first !");
        }else {
            //length 80 tất cả
           while (true){
               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
               System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
               System.out.printf("| %-76s |\n","              Customer Name : " +customer.getCustomerName());
               System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
               System.out.printf("| -Gender : %-10s                                                         |\n",customer.getGender() ? "male" : "female");
               System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
               System.out.printf("| -Birthday : %-15s                                                  |\n",simpleDateFormat.format(customer.getBirthday()));
               System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
               System.out.printf("| -Phone number : %-15s                                              |\n",customer.getPhoneNumber());
               System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
               System.out.printf("| -Email : %-30s                                      |\n",customer.getEmail());
               System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
               System.out.printf("| -Pass : %-69s|\n",customer.getPassword());
               System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
               System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
               System.out.println("|          1. Update information          |            2. Exit                 |");
               System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

               int choice = IMethod.getNumber("Enter choice : ");
               if(choice == 1){
                   List<Customer> customers = IMethod.listCustomer();
                   int index = customers.stream().map(Customer::getCustomerName).toList().indexOf(checkLogin.getFirst().getCustomerName());
                   customers.get(index).updateData(IMethod.scanner);
                   IMethod.saveDatabase(IMethod.fileCustomer,customers);
                   checkLogin.set(0,customers.get(index));
                   IMethod.saveDatabase(IMethod.fileCheckLogin,checkLogin);
                   checkLogin = IMethod.checkLogin();
                   customer = checkLogin.getFirst();
                   System.out.println("Update information successfully !");
               }else if(choice == 2) {
                   return;
               }else {
                   System.err.println("Enter choice 1 or 2 !");
               }
           }

        }
    }
}
