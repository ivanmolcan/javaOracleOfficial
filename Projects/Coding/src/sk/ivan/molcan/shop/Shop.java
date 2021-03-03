package sk.ivan.molcan.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

/**
 * @author Ivan
 */

public class Shop {

    public static void main(String[] args) {

        Product p1 = new Product(101, "Tea", BigDecimal.valueOf(1.99));
        Product p2 = new Drink(102, "Coffee", BigDecimal.valueOf(1.99), Rating.THREE_STAR);
        Product p3 = new Food(103, "Cake", BigDecimal.valueOf(1.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        Product p4 = p3.applyRating(Rating.FOUR_STAR);
        Product p5 = new Drink(104, "chocolate", BigDecimal.valueOf(2.99), Rating.TWO_STAR);
        Product p6 = new Food(104, "chocolate", BigDecimal.valueOf(2.99), Rating.TWO_STAR, LocalDate.now().plusDays(2));

        ProductManager pm = new ProductManager(Locale.UK);
        Product p7 = pm.createProduct(105, "Pie", BigDecimal.valueOf(1.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        Product p8 = pm.createProduct(106, "Soda", BigDecimal.valueOf(1.99), Rating.FIVE_STAR);


//        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getDiscount() + " " + p1.getRating().getStars());
//        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getDiscount() + " " + p2.getRating().getStars());
//        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getDiscount() + " " + p3.getRating().getStars());

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5.equals(p6));

        pm.printProductReport(p1);

        Comparator<Product> ratingSorter = (p7,p8) -> p8.getRating().ordinal() - p7.getRating().ordinal();
        Comparator<Product> priceSorter = (p7,p8) -> p8.getPrice().compareTo(p7.getPrice());
//        pm.printProducts((p7,p8) -> p8.getRating().ordinal() - p7.getRating().ordinal());
        pm.printProducts(ratingSorter.thenComparing(priceSorter));
        pm.printProducts(ratingSorter.thenComparing(priceSorter).reversed());

    }
}

