package presentation.managementSystem;

import business.common.IMethod;
import business.entity.Category;
import business.feature.CategoryFeature;
import business.util.GetColor;

import java.util.List;

public class CategoriesManagement {
    public static void main(String[] args) {
        String fileName = "listCategory.txt";
        CategoryFeature categoryFeature = new CategoryFeature();
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                 "+ GetColor.GREEN+"CATEGORIES MANAGEMENT"+GetColor.RESET+"                                                |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|      1. Display list categories        |          2. Add categories         |          3. Update categories          |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━┻━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━┻━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("|     4. Delete categories     |    5. Search categories    |    6. Sort categories     |           7. Back            |");
            System.out.println("|                              |                            |                           |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

            int choice = IMethod.getNumber("Enter choice : ");
            switch (choice){
                case 1 : {
                    List<Category> categories = IMethod.getListObject(fileName);
                    categoryFeature.displayList(categories);
                    break;
                }
                case 2 : {
                    categoryFeature.add();
                    break;
                }
                case 3 : {
                    categoryFeature.update();
                    break;
                }
                case 4 : {
                    categoryFeature.delete();
                    break;
                }
                case 5 : {
                    categoryFeature.searchCateByName();
                    break;
                }
                case 6 : {
                    categoryFeature.sortCategories();
                    break;
                }
                case 7 : {
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 7 !");
                }
            }
        }

    }
}
