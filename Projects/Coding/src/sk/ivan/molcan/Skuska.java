package sk.ivan.molcan;

/**
 * @author Ivan
 */

import java.time.LocalDate;

public class Skuska {

    static void script() {
        String a = "Tea";
        String b = a.concat(" at 5 o'clock");
        System.out.println(b.indexOf('o', 1));
    }

    static void builder() {
        StringBuilder txt = new StringBuilder("TEA TEA");
        System.out.println(txt.capacity());
    }

    static void timeFun() {
        LocalDate today = LocalDate.now();
        System.out.println(today);

    }

    static void setFiscal(int... values) {
        switch (values.length) {
            case 3:
                System.out.println(values[2]);
            case 2:
                System.out.println(values[1]);
            case 1:
                System.out.println(values[0]);
        }
    }

    public static void main(String[] args) {
//        int a =55;
//        System.out.println(~a);

//        script();
//        builder();
//        timeFun();
        setFiscal(1, 2, 3);

    }
}
