import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Item a = new Item("aaa", 5, ItemCondition.NEW, 4);
            Item b = new Item("bbb", 3, ItemCondition.REFURBISHED, 2);
            Item c = new Item("ccc", 2, ItemCondition.USED, 3);
            Item d = new Item("ddd", 2, ItemCondition.USED, 5);
            Item e = new Item("ede", 6, ItemCondition.NEW, 3);
            FulfillmentCenter magazyn = new FulfillmentCenter("mag", 100);

            System.out.println("1 tworzenie");
            magazyn.addProduct(a);
            magazyn.addProduct(b);
            magazyn.addProduct(c);
            magazyn.summary();
            System.out.println(" sortowanie po ilosci");
            magazyn.summary();
            magazyn.sortByAmount();
            magazyn.summary();
            System.out.println(" sortowanie po nazwie");
            magazyn.sortByName();
            magazyn.summary();
            System.out.println("2 odejmowanie 1");
            magazyn.getProduct(b);
            magazyn.summary();
            magazyn.getProduct(b);
            magazyn.summary();
            System.out.println();

            System.out.println("4 usuwanie");
            magazyn.removeProduct(c);
            magazyn.summary();
            System.out.println();

            System.out.println("5 dodanie na nowo");
            magazyn.addProduct(c);
            magazyn.addProduct(d);
            magazyn.addProduct(e);
            magazyn.summary();
            System.out.println("6 najwieksza ilosc");
            magazyn.max().print();

            System.out.println("7 stan");
            System.out.println("NEW: " + magazyn.countByCondition(ItemCondition.NEW));
            System.out.println("USED: " + magazyn.countByCondition(ItemCondition.USED));
            System.out.println("REFURBISHED: " + magazyn.countByCondition(ItemCondition.REFURBISHED));
            System.out.println("8 wyszukiwanie po nazwie");
            magazyn.search("ddd").print();
            System.out.println();
            System.out.println("9 wyszukiwanie po literze");
            magazyn.searchPartial("d");
            System.out.println();
            System.out.println("10");


            FulfillmentCenterContainer kkk = new FulfillmentCenterContainer();
            kkk.addCenter("m1", 100);
            kkk.addCenter("m3", 60);
            kkk.addCenter("m5", 64);
            kkk.addCenter("m2", 44);
            kkk.addCenter("m4", 64);
            Item f = new Item("fff", 9, ItemCondition.NEW, 2);
            kkk.summary();
            System.out.println();
            kkk.magazyny.get("m1").addProduct(f);
            kkk.magazyny.get("m1").addProduct(a);
            kkk.magazyny.get("m1").addProduct(b);
            kkk.magazyny.get("m2").addProduct(c);
            kkk.magazyny.get("m2").addProduct(d);
            kkk.magazyny.get("m3").addProduct(e);
            kkk.magazyny.get("m3").addProduct(f);
            kkk.magazyny.get("m1").summary();
            kkk.magazyny.get("m2").summary();
            kkk.magazyny.get("m3").summary();
            kkk.magazyny.get("m4").summary();
            kkk.magazyny.get("m5").summary();
            System.out.println();
            kkk.summary();
            System.out.println();
            kkk.findEmpty();
            System.out.println();
            kkk.removeCenter("m2");
            kkk.summary();
            System.out.println();
        } catch (Exception x) {
            System.out.println(x);
        }
    }
}
