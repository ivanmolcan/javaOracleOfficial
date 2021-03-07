package sk.ivan.molcan.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ivan
 */

public class Shop {

    public static void main(String[] args) {
        ProductManager pm = ProductManager.getInstance();

        AtomicInteger clientCount = new AtomicInteger(0);
        Callable<String> client = () -> {
            String clientId = "Client " + clientCount.incrementAndGet();
            String threadName = Thread.currentThread().getName();
            int productId = ThreadLocalRandom.current().nextInt(63) + 101;
            String languageTag = ProductManager.getSupportedLocales()
                    .stream()
                    .skip(ThreadLocalRandom.current().nextInt(4))
                    .findFirst().get();
            StringBuilder log = new StringBuilder();
            log.append(clientId + threadName + "\n-\tstart of log\t-\n");
            log.append(pm.getDiscounts(languageTag)
                    .entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "\t" + entry.getValue())
                    .collect(Collectors.joining("\n")));
            Product product = pm.reviewProduct(productId, Rating.FOUR_STAR, "Yet another review");
            log.append((product != null) ? "\nProduct " + productId + " reviewed\n" : "\nProduct " + productId + " not reviewed\n");
            pm.printProductReport(productId, languageTag, clientId);
            log.append(clientId + " generated report for " + productId + " product");
            log.append("\n-\tend of log\t-\n");
            return log.toString();
        };

        List<Callable<String>> clients = Stream.generate(() -> client)
                .limit(5)
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            List<Future<String>> results = executorService.invokeAll(clients);
            executorService.shutdown();
            results.stream()
                    .forEach(result -> {
                        try {
                            System.out.println(result.get());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (InterruptedException e) {
            Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, "Error invoking clients", e);
        }

//        Product p1 = new Product(101, "Tea", BigDecimal.valueOf(1.99));
//        Product p2 = new Drink(102, "Coffee", BigDecimal.valueOf(1.99), Rating.THREE_STAR);
//        Product p3 = new Food(103, "Cake", BigDecimal.valueOf(1.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p4 = p3.applyRating(Rating.FOUR_STAR);
//        Product p5 = new Drink(104, "chocolate", BigDecimal.valueOf(2.99), Rating.TWO_STAR);
//        Product p6 = new Food(104, "chocolate", BigDecimal.valueOf(2.99), Rating.TWO_STAR, LocalDate.now().plusDays(2));
//
////        ProductManager pm = new ProductManager();
//        Product p7 = pm.createProduct(105, "Pie", BigDecimal.valueOf(1.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p8 = pm.createProduct(106, "Soda", BigDecimal.valueOf(1.99), Rating.FIVE_STAR);
//
//
////        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getDiscount() + " " + p1.getRating().getStars());
////        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getDiscount() + " " + p2.getRating().getStars());
////        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getDiscount() + " " + p3.getRating().getStars());
//
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(p4);
//        System.out.println(p5.equals(p6));
//
//        pm.printProductReport(p1);
//
//        pm.dumpData();
//        pm.restoreData();
//
//        Comparator<Product> ratingSorter = (p -> p.getPrice().floatValue() < 2,
//        (p7, p8) -> p8.getRating().ordinal() - p7.getRating().ordinal());
//
//        pm.getDiscounts().forEach((rating, discount) -> System.out.println(rating + "\t" + discount));
//
//        Comparator<Product> priceSorter = (p7, p8) -> p8.getPrice().compareTo(p7.getPrice());
//        pm.printProducts((p7, p8) -> p8.getRating().ordinal() - p7.getRating().ordinal());
////        pm.printProducts(ratingSorter.thenComparing(priceSorter));
////        pm.printProducts(ratingSorter.thenComparing(priceSorter).reversed());

    }
}

