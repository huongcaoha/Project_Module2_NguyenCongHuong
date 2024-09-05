package business.entity;

import business.common.IMethod;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
}
