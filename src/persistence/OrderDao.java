package persistence;

import domain.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getOrdersByUsername(String var1);

    Order getOrder(int var1);

    void insertOrder(Order var1);

    void insertOrderStatus(Order var1);

}
