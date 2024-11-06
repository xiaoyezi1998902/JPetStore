package persistence.implement;

import domain.Product;
import persistence.DBUtil;
import persistence.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    public static final String GET_PRODUCT_LIST_BY_CATEGORY =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";

    public static final String GET_PRODUCT =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";

    public static final String SEARCH_PRODUCT_LIST =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT " +
                    "WHERE lower(name) like \"%\" ? \"%\"";

    @Override
    public List<Product> getProductListByCategory(String var1) {
        List<Product> productList = new ArrayList<>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getString("categoryId"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Product getProduct(String var1) {
        Product product = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getString("categoryId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> searchProductList(String var1) {
        List<Product> productList = new ArrayList<>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("PRODUCTID"));
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryId(resultSet.getString("categoryId"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
}
