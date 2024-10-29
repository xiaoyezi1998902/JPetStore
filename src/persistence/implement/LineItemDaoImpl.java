package persistence.implement;

import domain.LineItem;
import persistence.DBUtil;
import persistence.LineItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoImpl implements LineItemDao {
    public static final String GET_LINE_ITEMS_BY_ORDER_ID =
            "SELECT ORDERID,\n" +
            "      LINENUM AS lineNumber,\n" +
            "      ITEMID,\n" +
            "      QUANTITY,\n" +
            "      UNITPRICE\n" +
            "    FROM LINEITEM\n" +
            "    WHERE ORDERID = ?";
    public static final String INSERT_LINE_ITEM =
            "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE)\n" +
            "    VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int var1) {
        List<LineItem> lineItemList = new ArrayList<>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LINE_ITEMS_BY_ORDER_ID);
            preparedStatement.setInt(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));

                lineItemList.add(lineItem);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem var1) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LINE_ITEM);
            preparedStatement.setInt(1, var1.getOrderId());
            preparedStatement.setInt(2, var1.getLineNumber());
            preparedStatement.setString(3, var1.getItemId());
            preparedStatement.setInt(4, var1.getQuantity());
            preparedStatement.setBigDecimal(5, var1.getUnitPrice());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
