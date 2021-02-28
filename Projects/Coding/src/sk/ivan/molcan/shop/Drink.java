package sk.ivan.molcan.shop;

import java.math.BigDecimal;

/**
 * @author Ivan
 */

public class Drink extends Product {

    public Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }
}
