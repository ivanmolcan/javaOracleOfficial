package sk.ivan.molcan.shop;

import java.math.BigDecimal;

/**
 * @author Ivan
 */

public final class Drink extends Product {

    Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }
}
