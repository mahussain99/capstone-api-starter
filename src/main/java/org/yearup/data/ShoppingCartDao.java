package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{

    ShoppingCart getByUserId(int userId );
    void addingItems (int userId, int productId);
    // add additional method signatures here
    void Update(int userId, int productId, int quantity);
    void delete(int userId);

}
