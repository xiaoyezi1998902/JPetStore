package persistence.implement;

import domain.Order;
import persistence.DBUtil;
import persistence.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    public static final String GET_ORDER_BY_USERNAME =
            "SELECT BILLADDR1 AS billAddress1,\n" +
                    "      BILLADDR2 AS billAddress2,\n" +
                    "      BILLCITY,\n" +
                    "      BILLCOUNTRY,\n" +
                    "      BILLSTATE,\n" +
                    "      BILLTOFIRSTNAME,\n" +
                    "      BILLTOLASTNAME,\n" +
                    "      BILLZIP,\n" +
                    "      SHIPADDR1 AS shipAddress1,\n" +
                    "      SHIPADDR2 AS shipAddress2,\n" +
                    "      SHIPCITY,\n" +
                    "      SHIPCOUNTRY,\n" +
                    "      SHIPSTATE,\n" +
                    "      SHIPTOFIRSTNAME,\n" +
                    "      SHIPTOLASTNAME,\n" +
                    "      SHIPZIP,\n" +
                    "      CARDTYPE,\n" +
                    "      COURIER,\n" +
                    "      CREDITCARD,\n" +
                    "      EXPRDATE AS expiryDate,\n" +
                    "      LOCALE,\n" +
                    "      ORDERDATE,\n" +
                    "      ORDERS.ORDERID,\n" +
                    "      TOTALPRICE,\n" +
                    "      USERID AS username,\n" +
                    "      STATUS\n" +
                    "    FROM ORDERS, ORDERSTATUS\n" +
                    "    WHERE ORDERS.USERID = ?\n" +
                    "      AND ORDERS.ORDERID = ORDERSTATUS.ORDERID\n" +
                    "    ORDER BY ORDERDATE";
    public static final String GET_ORDER =
            "select BILLADDR1 AS billAddress1,\n" +
                    "      BILLADDR2 AS billAddress2,\n" +
                    "      BILLCITY,\n" +
                    "      BILLCOUNTRY,\n" +
                    "      BILLSTATE,\n" +
                    "      BILLTOFIRSTNAME,\n" +
                    "      BILLTOLASTNAME,\n" +
                    "      BILLZIP,\n" +
                    "      SHIPADDR1 AS shipAddress1,\n" +
                    "      SHIPADDR2 AS shipAddress2,\n" +
                    "      SHIPCITY,\n" +
                    "      SHIPCOUNTRY,\n" +
                    "      SHIPSTATE,\n" +
                    "      SHIPTOFIRSTNAME,\n" +
                    "      SHIPTOLASTNAME,\n" +
                    "      SHIPZIP,\n" +
                    "      CARDTYPE,\n" +
                    "      COURIER,\n" +
                    "      CREDITCARD,\n" +
                    "      EXPRDATE AS expiryDate,\n" +
                    "      LOCALE,\n" +
                    "      ORDERDATE,\n" +
                    "      ORDERS.ORDERID,\n" +
                    "      TOTALPRICE,\n" +
                    "      USERID AS username,\n" +
                    "      STATUS\n" +
                    "    FROM ORDERS, ORDERSTATUS\n" +
                    "    WHERE ORDERS.ORDERID = ?\n" +
                    "      AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
    public static final String INSERT_ORDER =
            "INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,\n" +
                    "      SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY,\n" +
                    "      COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,\n" +
                    "      CREDITCARD, EXPRDATE, CARDTYPE, LOCALE)\n" +
                    "    VALUES(?, ?, ?, ?, ?, ?,\n" +
                    "      ?, ?, ?, ?, ?, ?,\n" +
                    "      ?, ?, ?, ?, ?, ?, ?,\n" +
                    "      ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_ORDER_STATUS =
            "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)\n" +
                    "    VALUES (?, ?, ?, ?)";

    @Override
    public List<Order> getOrdersByUsername(String var1) {
        List<Order> orderList = new ArrayList<>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USERNAME);
            preparedStatement.setString(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setBillAddress1(resultSet.getString(1));
                order.setBillAddress2(resultSet.getString(2));
                order.setBillCity(resultSet.getString(3));
                order.setBillCountry(resultSet.getString(4));
                order.setBillState(resultSet.getString(5));
                order.setBillToFirstName(resultSet.getString(6));
                order.setBillToLastName(resultSet.getString(7));
                order.setBillZip(resultSet.getString(8));
                order.setShipAddress1(resultSet.getString(9));
                order.setShipAddress2(resultSet.getString(10));
                order.setShipCity(resultSet.getString(11));
                order.setShipCountry(resultSet.getString(12));
                order.setShipState(resultSet.getString(13));
                order.setShipToFirstName(resultSet.getString(14));
                order.setShipToLastName(resultSet.getString(15));
                order.setShipZip(resultSet.getString(16));
                order.setCardType(resultSet.getString(17));
                order.setCourier(resultSet.getString(18));
                order.setCreditCard(resultSet.getString(19));
                order.setExpiryDate(resultSet.getString(20));
                order.setLocale(resultSet.getString(21));
                order.setOrderDate(resultSet.getDate(22));
                order.setOrderId(resultSet.getInt(23));
                order.setTotalPrice(resultSet.getBigDecimal(24));
                order.setUsername(resultSet.getString(25));
                order.setStatus(resultSet.getString(26));

                orderList.add(order);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public Order getOrder(int var1) {
        Order order = null;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER);
            preparedStatement.setInt(1, var1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = new Order();
                order.setBillAddress1(resultSet.getString(1));
                order.setBillAddress2(resultSet.getString(2));
                order.setBillCity(resultSet.getString(3));
                order.setBillCountry(resultSet.getString(4));
                order.setBillState(resultSet.getString(5));
                order.setBillToFirstName(resultSet.getString(6));
                order.setBillToLastName(resultSet.getString(7));
                order.setBillZip(resultSet.getString(8));
                order.setShipAddress1(resultSet.getString(9));
                order.setShipAddress2(resultSet.getString(10));
                order.setShipCity(resultSet.getString(11));
                order.setShipCountry(resultSet.getString(12));
                order.setShipState(resultSet.getString(13));
                order.setShipToFirstName(resultSet.getString(14));
                order.setShipToLastName(resultSet.getString(15));
                order.setShipZip(resultSet.getString(16));
                order.setCardType(resultSet.getString(17));
                order.setCourier(resultSet.getString(18));
                order.setCreditCard(resultSet.getString(19));
                order.setExpiryDate(resultSet.getString(20));
                order.setLocale(resultSet.getString(21));
                order.setOrderDate(resultSet.getDate(22));
                order.setOrderId(resultSet.getInt(23));
                order.setTotalPrice(resultSet.getBigDecimal(24));
                order.setUsername(resultSet.getString(25));
                order.setStatus(resultSet.getString(26));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void insertOrder(Order var1) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(1, var1.getOrderId());
            preparedStatement.setString(2, var1.getUsername());
            preparedStatement.setDate(3, new java.sql.Date(var1.getOrderDate().getTime()));
            preparedStatement.setString(4, var1.getShipAddress1());
            preparedStatement.setString(5, var1.getShipAddress2());
            preparedStatement.setString(6, var1.getShipCity());
            preparedStatement.setString(7, var1.getShipState());
            preparedStatement.setString(8, var1.getShipZip());
            preparedStatement.setString(9, var1.getShipCountry());
            preparedStatement.setString(10, var1.getBillAddress1());
            preparedStatement.setString(11, var1.getBillAddress2());
            preparedStatement.setString(12, var1.getBillCity());
            preparedStatement.setString(13, var1.getBillState());
            preparedStatement.setString(14, var1.getBillZip());
            preparedStatement.setString(15, var1.getBillCountry());
            preparedStatement.setString(16, var1.getCourier());
            preparedStatement.setBigDecimal(17, var1.getTotalPrice());
            preparedStatement.setString(18, var1.getBillToFirstName());
            preparedStatement.setString(19, var1.getBillToLastName());
            preparedStatement.setString(20, var1.getShipToFirstName());
            preparedStatement.setString(21, var1.getShipToLastName());
            preparedStatement.setString(22, var1.getCreditCard());
            preparedStatement.setString(23, var1.getExpiryDate());
            preparedStatement.setString(24, var1.getCardType());
            preparedStatement.setString(25, var1.getLocale());
            int ret = preparedStatement.executeUpdate();

            if (ret == 1) {
                System.out.println("正常");
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Order var1) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_STATUS);
            preparedStatement.setInt(1, var1.getOrderId());
            preparedStatement.setInt(2, var1.getLineItems().size());
            preparedStatement.setDate(3, new java.sql.Date(var1.getOrderDate().getTime()));
            preparedStatement.setString(4, var1.getStatus());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
