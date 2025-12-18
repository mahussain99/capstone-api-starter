package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
    public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    private ProductDao productDao;

    public MySqlShoppingCartDao(DataSource dataSource, ProductDao productDao) {
        super(dataSource);
        this.productDao = productDao;
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("""
                     SELECT * FROM shopping_cart
                     WHERE user_id = ?""")) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSetId = preparedStatement.executeQuery()) {
                if (resultSetId.next()) {
                    return mapRow(resultSetId);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public void addingItems(int userId, int productId) {
        try {
            Connection connection = getConnection();
            PreparedStatement addingItemStatement = connection.prepareStatement ""INSERT INTO shopping_cart(user_id, product_id) VALUES (?, ?)
                    """
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void Update() {

    }

    @Override
    public void delete() {

    }

    private ShoppingCart mapRow(ResultSet row) throws SQLException {
        int productId = row.getInt("product_id");
        int quantity = row.getInt("quantity");
        
        return new ShoppingCart(productId,quantity);
    }
}