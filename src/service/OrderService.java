package service;

import domain.Item;
import domain.LineItem;
import domain.Order;
import domain.Sequence;
import persistence.ItemDao;
import persistence.LineItemDao;
import persistence.OrderDao;
import persistence.SequenceDao;
import persistence.implement.ItemDaoImpl;
import persistence.implement.LineItemDaoImpl;
import persistence.implement.OrderDaoImpl;
import persistence.implement.SequenceDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private ItemDao itemDao;
    private OrderDao orderDao;
    private SequenceDao sequenceDao;
    private LineItemDao lineItemDao;

    public OrderService() {
        itemDao = new ItemDaoImpl();
        orderDao = new OrderDaoImpl();
        sequenceDao = new SequenceDaoImpl();
        lineItemDao = new LineItemDaoImpl();
    }

    public void insertOrder(Order order) {
        order.setOrderId(this.getNextId("ordernum"));

        int i;
        LineItem lineItem;
        for(i = 0; i < order.getLineItems().size(); ++i) {
            lineItem = (LineItem)order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            this.itemDao.updateInventoryQuantity(param);
        }

        this.orderDao.insertOrder(order);
        this.orderDao.insertOrderStatus(order);

        for(i = 0; i < order.getLineItems().size(); ++i) {
            lineItem = (LineItem)order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());

            this.lineItemDao.insertLineItem(lineItem);
        }
    }

    public Order getOrder(int orderId) {
        Order order = this.orderDao.getOrder(orderId);
        order.setLineItems(this.lineItemDao.getLineItemsByOrderId(orderId));

        for(int i = 0; i < order.getLineItems().size(); ++i) {
            LineItem lineItem = (LineItem)order.getLineItems().get(i);
            Item item = this.itemDao.getItem(lineItem.getItemId());
            item.setQuantity(this.itemDao.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return this.orderDao.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = this.sequenceDao.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name + " sequence).");
        } else {
            Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
            this.sequenceDao.updateSequence(parameterObject);
            return sequence.getNextId();
        }
    }
}
