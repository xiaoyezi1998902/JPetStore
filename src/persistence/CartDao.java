package persistence;

import domain.CartItem;
import domain.Item;

import java.math.BigDecimal;
import java.util.List;

public interface CartDao {

    List<CartItem> getCartItemsByUserId(String var1);

    void addItemToCart(CartItem cartItem, String username);

    void removeItem(String username, String itemId);

    void updateCart(String username, String itemId, int quantity, BigDecimal totalCost);

    void clearTheCart(String username);

}
