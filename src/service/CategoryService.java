package service;

import domain.Category;
import domain.Item;
import domain.Product;
import persistence.CategoryDao;
import persistence.ItemDao;
import persistence.ProductDao;
import persistence.implement.CategoryDaoImpl;
import persistence.implement.ItemDaoImpl;
import persistence.implement.ProductDaoImpl;

import java.util.List;

public class CategoryService {
    public CategoryDao categoryDao;
    public ProductDao productDao;
    public ItemDao itemDao;

    public CategoryService() {
        categoryDao = new CategoryDaoImpl();
        productDao = new ProductDaoImpl();
        itemDao = new ItemDaoImpl();
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
