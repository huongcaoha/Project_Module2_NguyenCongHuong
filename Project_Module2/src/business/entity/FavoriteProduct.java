package business.entity;

import business.common.IMethod;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class FavoriteProduct implements Serializable {
    private Integer id ;
    private Product favoriteProducts ;
    private Integer customerId ;
    private LocalDate createdDate;

    public FavoriteProduct() {
    }

    public FavoriteProduct( Product favoriteProducts, Integer customerId) {
        this.id = inputId();
        this.favoriteProducts = favoriteProducts;
        this.customerId = customerId;
        this.createdDate = LocalDate.now();
    }

    public int inputId(){
        List<FavoriteProduct> favoriteProductList = IMethod.listFavoriteProduct();
        if(favoriteProductList.isEmpty()){
            return 1 ;
        }else {
            return favoriteProductList.getLast().id + 1 ;
        }
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(Product favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void displayData(){
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-28s | %-3s | %-13s | %-18s | %-8s | %-7d | %-13s |\n",id,favoriteProducts.getProductName(),favoriteProducts.getDiscount()+"%",format.format(favoriteProducts.getFinalPrice())+"VNĐ",favoriteProducts.getSize(),favoriteProducts.getColor(),favoriteProducts.getCateId(),dateTimeFormatter.format(createdDate));
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
    }
}
