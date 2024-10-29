package web.servlet;


import domain.Category;
import domain.Item;
import domain.Product;
import domain.Search;
import persistence.CategoryDao;
import persistence.ItemDao;
import persistence.ProductDao;
import persistence.SearchDao;
import persistence.implement.CategoryDaoImpl;
import persistence.implement.ItemDaoImpl;
import persistence.implement.ProductDaoImpl;
import persistence.implement.SearchDaoImpl;

import java.util.List;

public class CatalogService {
    private CategoryDao categoryDao;
    private ItemDao itemDao;
    private ProductDao productDao;
    private SearchDao searchDao;
    public CatalogService(){
        this.categoryDao=new CategoryDaoImpl();
        this.itemDao=new ItemDaoImpl();
        this.productDao=new ProductDaoImpl();
        this.searchDao=new SearchDaoImpl();
    }
    public List<Category> getCategoryList() {
        return this.categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return this.categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return this.productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return this.productDao.getProductListByCategory(categoryId);
    }
    public List<Search> getSearchList(String productid){
        return this.searchDao.getSearchList(productid);
    }
    public List<Search> nameSearchList(String productid){
        return this.searchDao.nameSearchList(productid);
    }
    public Search itemSearchList(String productid){
        return this.searchDao.itemSearchList(productid);
    }
    public List<Product> searchProductList(String keyword) {
        return this.productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return this.itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return this.itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return this.itemDao.getInventoryQuantity(itemId) > 0;
    }
}
