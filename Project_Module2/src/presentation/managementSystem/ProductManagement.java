package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Product;
import business.feature.ProductFeature;
import business.util.GetColor;

import java.util.List;

public class ProductManagement {
    public static void main(String[] args) {
        String fileName = "listProduct.txt";
        ProductFeature productFeature = new ProductFeature();
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                  "+ GetColor.GREEN+"PRODUCT MANAGEMENT"+GetColor.RESET+"                                                  |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|   1. Display list products   |     2. Add new product     |    3. Update product      |   4. Search product by id    |");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|      5. Delete product       |     6. Search product      |     7. Sort product       |           8. Back            |");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = IMethod.getNumber("Enter choice : ");
            switch (choice){
                case 1 : {
                    List<Product> products = IMethod.getListObject(fileName);
                    productFeature.displayList(products);
                    break;
                }
                case 2 : {
                    productFeature.add();
                    break;
                }
                case 3 : {
                    productFeature.update();
                    break;
                }
                case 4 : {
                    productFeature.searchProductById();
                    break;
                }
                case 5 : {
                    productFeature.delete();
                    break;
                }
                case 6 : {
                    productFeature.searchProduct();
                    break;
                }
                case 7 : {
                    productFeature.sortProduct();
                }
                case 8 : {
                    return;
                }
                default:{
                    System.err.println("Enter choice from 1 to 7 !");
                }
            }
        }
    }
}
