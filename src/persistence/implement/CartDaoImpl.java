package persistence.implement;

import domain.CartItem;
import domain.Item;
import persistence.CartDao;
import persistence.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    public static final String GET_CART_ITEMS_BY_USER_ID =
            "SELECT USERNAME,\n" +
                    "      ITEMID,\n" +
                    "      PRODUCTID,\n" +
                    "      DESCRIPTION,\n" +
                    "      IN_STOCK,\n" +
                    "      QUANTITY,\n" +
                    "      LISTPRICE,\n" +
                    "      TOTALCOST\n" +
                    "    FROM CART\n" +
                    "    WHERE USERNAME = ?";

    public static final String ADD_ITEM_TO_CART =
            "INSERT INTO CART (USERNAME, ITEMID, PRODUCTID, DESCRIPTION, IN_STOCK, QUANTITY, LISTPRICE, TOTALCOST)\n" +
                    "    VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String REMOVE_ITEM =
            "DELETE FROM CART WHERE USERNAME=? AND ITEMID=?";

    public static final String UPDATE_CART =
            "UPDATE CART SET QUANTITY =?, TOTALCOST=?\n" +
            "    WHERE USERNAME =? AND ITEMID=?";

    public static final String CLEAR_THE_CART =
            "DELETE FROM CART WHERE USERNAME=?";

    @Override
    public List<CartItem> getCartItemsByUserId(String var1) {
        List<CartItem> cartItemList = new ArrayList<>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_ITEMS_BY_USER_ID);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CartItem cartItem = new CartItem();
                Item item = new Item();
                item.setItemId(resultSet.getString(2));
                item.setProductId(resultSet.getString(3));
                cartItem.setDescription(resultSet.getString(4));
                cartItem.setInStock(resultSet.getInt(5) == 1 ? true : false);
                cartItem.setQuantity(resultSet.getInt(6));
                item.setListPrice(resultSet.getBigDecimal(7));
                cartItem.setTotal(resultSet.getBigDecimal(8));
                cartItem.setItem(item);
                cartItemList.add(cartItem);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItemList;
    }

    @Override
    public void addItemToCart(CartItem cartItem, String username) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ITEM_TO_CART);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, cartItem.getItem().getItemId());
            preparedStatement.setString(3, cartItem.getItem().getProductId());
            preparedStatement.setString(4, cartItem.getDescription());
            preparedStatement.setInt(5, cartItem.isInStock() ? 1 : 0);
            preparedStatement.setInt(6, cartItem.getQuantity());
            preparedStatement.setBigDecimal(7, cartItem.getItem().getListPrice());
            preparedStatement.setBigDecimal(8, cartItem.getTotal());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItem(String username, String itemId) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ITEM);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCart(String username, String itemId, int quantity, BigDecimal totalCost) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setBigDecimal(2, totalCost);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, itemId);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearTheCart(String username) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_THE_CART);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
