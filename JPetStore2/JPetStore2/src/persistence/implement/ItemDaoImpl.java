package persistence.implement;

import domain.Item;
import domain.Product;
import persistence.DBUtil;
import persistence.ItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {
    public static final String UPDATE_INVENTORY_QUANTITY =
            "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";

    public static final String GET_INVENTORY_QUANTITY =
            "SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";

    public static final String GET_ITEM_LIST_BY_PRODUCT =
            "SELECT I.ITEMID, LISTPRICE, UNITCOST,\n" +
                    "      SUPPLIER AS supplierId,\n" +
                    "      I.PRODUCTID AS \"product.productId\",\n" +
                    "      NAME AS \"product.name\",\n" +
                    "      DESCN AS \"product.description\",\n" +
                    "      CATEGORY AS \"product.categoryId\",\n" +
                    "      STATUS,\n" +
                    "      ATTR1 AS attribute1,\n" +
                    "      ATTR2 AS attribute2,\n" +
                    "      ATTR3 AS attribute3,\n" +
                    "      ATTR4 AS attribute4,\n" +
                    "      ATTR5 AS attribute5\n" +
                    "    FROM ITEM I, PRODUCT P\n" +
                    "    WHERE P.PRODUCTID = I.PRODUCTID\n" +
                    "    AND I.PRODUCTID = ?";

    public static final String GET_ITEM =
            "select I.ITEMID,\n" +
                    "      LISTPRICE,\n" +
                    "      UNITCOST,\n" +
                    "      SUPPLIER AS supplierId,\n" +
                    "      I.PRODUCTID AS \"product.productId\",\n" +
                    "      NAME AS \"product.name\",\n" +
                    "      DESCN AS \"product.description\",\n" +
                    "      CATEGORY AS \"product.categoryId\",\n" +
                    "      STATUS,\n" +
                    "      ATTR1 AS attribute1,\n" +
                    "      ATTR2 AS attribute2,\n" +
                    "      ATTR3 AS attribute3,\n" +
                    "      ATTR4 AS attribute4,\n" +
                    "      ATTR5 AS attribute5,\n" +
                    "      QTY AS quantity\n" +
                    "    from ITEM I, INVENTORY V, PRODUCT P\n" +
                    "    where P.PRODUCTID = I.PRODUCTID\n" +
                    "      and I.ITEMID = V.ITEMID\n" +
                    "      and I.ITEMID = ?";

    @Override
    public void updateInventoryQuantity(Map<String, Object> var1) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_QUANTITY);
            String itemId = var1.keySet().iterator().next();
            Integer increment = (Integer) var1.get(itemId);
            preparedStatement.setInt(1, increment.intValue());
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String var1) {
        int ret = -1;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getInt("value");
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public List<Item> getItemListByProduct(String var1) {
        List<Item> itemList = new ArrayList<>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM_LIST_BY_PRODUCT);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                itemList.add(item);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemList;
    }

    @Override
    public Item getItem(String var1) {
        Item item = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                item = new Item();

                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;
    }
}
