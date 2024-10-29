package persistence;

import domain.LineItem;

import java.util.List;

public interface LineItemDao {

    List<LineItem> getLineItemsByOrderId(int var1);

    void insertLineItem(LineItem var1);

}
