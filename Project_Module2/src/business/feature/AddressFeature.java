package business.feature;

import business.common.IMethod;
import business.entity.Address;
import business.entity.Customer;
import business.util.GetColor;

import java.util.List;

public class AddressFeature {
    public static void displayListAddress(Customer customer){
        List<Address> addresses = customer.getAddresses();
        int currentPage = 1 ;
        int itemPerPage = 5 ;
        int totalPage = (int) Math.ceil((double) addresses.size() /itemPerPage);
        int skip = (currentPage -1 ) * itemPerPage ;
        int size = addresses.size();
        if(addresses.isEmpty()){
            System.err.println("List products is empty !");
        }else {
            while (true){
                addresses = IMethod.listAddresses(customer);
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|                                                   "+ GetColor.GREEN+"LIST ADDRESS"+GetColor.RESET+"                                                   |");
                System.out.println("|━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━|");
                System.out.printf("| %-8s | %-88s | %-18s |\n" ,"ID","Address Name","CreatedDate");
                System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━┛");
                for(int i = skip ; i < (skip + itemPerPage) ; i++){
                    if(i < size){
                        addresses.get(i).displayData();
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
                int spaceStart = (118 - (pagination.length())) / 2 ;
                int spaceEnd = (118 - pagination.length()) - spaceStart ;
                for(int j = 1 ; j <= spaceStart ; j++){
                    rs += " ";
                }
                rs = rs.concat(pagination.toString());
                for(int j = 1 ; j <= spaceEnd ; j++){
                    rs += " ";
                }
                rs += "|";
                System.out.println(rs);
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|             1. Previous               |                 2. Back              |                3. Next                |");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
                System.out.println("|             4. Add address            |          5. Delete one address       |        6. Search address by id        |                     |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

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
                        addAddress(customer);
                        break;
                    }
                    case 5 : {
                        deleteAddress(customer);
                        break;
                    }
                    case 6 : {
                        List<Address> addresses1 = IMethod.listAddresses(customer);
                        int idAddress = IMethod.getNumber("Enter id address want delete : ");
                        int indexAddress = addresses1.stream().map(Address::getAddressId).toList().indexOf(idAddress);
                        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                        System.out.println("|                                                   "+GetColor.GREEN+"RESULT SEARCH"+GetColor.RESET+"                                                      |");
                        addresses1.get(indexAddress).displayData();
                        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                        System.out.println("|                                                        0. Back                                                       |");
                        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                        while (true){
                            try {
                                System.out.println("Enter 0 to back !");
                                int choose = Integer.parseInt(IMethod.scanner.nextLine().trim());
                                if(choose == 0){
                                    break;
                                }
                            }catch (NumberFormatException e){
                                System.out.println("Input invalid !");
                            }
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

    private static void deleteAddress(Customer customer) {
        List<Address> addresses1 = IMethod.listAddresses(customer);
        int idAddress = IMethod.getNumber("Enter id address want delete : ");
        int indexAddress = addresses1.stream().map(Address::getAddressId).toList().indexOf(idAddress);
        if(indexAddress == -1){
            System.err.println("Not found id address !");
        }else {
            addresses1.remove(indexAddress);
            IMethod.saveAddress(customer,addresses1);
            System.out.println("Delete success address !");
        }
    }

    private static void addAddress(Customer customer) {
        List<Address> addresses1 = IMethod.listAddresses(customer);
        int number = IMethod.getNumber("Enter number address want add : ");
        for(int i = 1 ; i <= number ; i++){
            System.out.println("Enter address number : " + i);
            Address address = new Address();
            address.inputData(IMethod.scanner,addresses1);
            addresses1.add(address);
            IMethod.saveAddress(customer,addresses1);
        }
        System.out.println("Add success : " + number + " address !");
    }
}
