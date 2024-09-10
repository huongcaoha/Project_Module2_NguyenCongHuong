package business.feature;

import business.common.IMethod;
import business.entity.Customer;
import business.entity.FavoriteProduct;
import business.entity.Product;
import business.entity.ProductCart;
import business.util.GetColor;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FavoritesFeature {
    public void displayFavorites(Customer customer) {
        List<FavoriteProduct> products = IMethod.listFavoriteProduct().stream().filter(favoriteProduct -> Objects.equals(favoriteProduct.getCustomerId(), customer.getCustomerId())).toList();
        int currentPage = 1 ;
        int itemPerPage = 5 ;
        if(products.isEmpty()){
            System.err.println("List products favorite is empty !");
        }else {
            while (true){
                products = IMethod.listFavoriteProduct().stream().filter(favoriteProduct -> Objects.equals(favoriteProduct.getCustomerId(), customer.getCustomerId())).toList();
                int totalPage = (int) Math.ceil((double) products.size() /itemPerPage);
                int skip = (currentPage -1 ) * itemPerPage ;
                int size = products.size();
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|                                                 LIST PRODUCT FAVORITES                                             |");
                System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
                System.out.printf("| %-3s | %-28s | %-3s | %-13s | %-18s | %-8s | %-7s | %-13s |\n","ID","Product Name","-%","Final Price","Size","Color","CateID","Created Date");
                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
                for(int i = skip ; i < (skip + itemPerPage) ; i++){
                    if(i < size){
                        products.get(i).displayData();
                    }else {
                        break;
                    }
                }
                StringBuilder pagination = new StringBuilder();
                int startPage;
                int endPage ;
                if(currentPage < 3){
                    startPage = 1 ;
                    endPage = 5 ;
                }else if(currentPage + 2 <= totalPage){
                    startPage = currentPage - 2 ;
                    endPage = currentPage + 2 ;
                }else {
                    startPage = totalPage - 4 ;
                    endPage = totalPage;
                }
                for(int i = startPage ; i <= endPage ; i++){
                    if(i > totalPage){
                        break;
                    }
                    if(i < 1 ){
                        continue;
                    }
                    if(currentPage == i){
                        pagination.append(GetColor.RED + "[").append(i).append("]").append(GetColor.RESET);
                    }else {
                        pagination.append("[").append(i).append("]");
                    }

                    pagination.append("     ");

                }
                String rs ="|";
                int spaceStart = (125 - (pagination.length())) / 2 ;
                int spaceEnd = (125 - pagination.length()) - spaceStart ;
                for(int j = 1 ; j <= spaceStart ; j++){
                    rs += " ";
                }
                rs = rs.concat(pagination.toString());
                for(int j = 1 ; j <= spaceEnd ; j++){
                    rs += " ";
                }
                rs += "|";
                System.out.println(rs);
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|             1. Previous              |                 2. Back                  |             3. Next              |");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.println("|                                                4. Delete favorite product                                          |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

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
                        int idProduct = IMethod.getNumber("Enter id product favorite : ");
                        int indexProduct = products.stream().map(FavoriteProduct::getId).toList().indexOf(idProduct);
                        if(indexProduct == -1){
                            System.err.println("Not found id product favorite !");
                        }else {
                            List<FavoriteProduct> favoriteProducts = new ArrayList<>(products);
                            favoriteProducts.remove(indexProduct);
                            IMethod.saveDatabase(IMethod.fileFavoriteProduct,favoriteProducts);
                            System.out.println("Delete product favorite success !");
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
}
