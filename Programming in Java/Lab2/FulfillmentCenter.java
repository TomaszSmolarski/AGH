import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class FulfillmentCenter {
    public String nazwaMagazynu;
    List<Item> produkty = new ArrayList<Item>();
    public double maxPojemnosc;
    public double zajetaPojemnosc = 0;

    public FulfillmentCenter(String nazwaMagazynu, double maxPojemnosc) {
        this.nazwaMagazynu = nazwaMagazynu;
        this.maxPojemnosc = maxPojemnosc;
    }


    public void print() {
        System.out.println("Nazwa :" + nazwaMagazynu + "\tMaxPojemnosc: " + maxPojemnosc + "\tZajetaPojemnosc: " + zajetaPojemnosc + "\t%zapelnienie: " + (zajetaPojemnosc / maxPojemnosc) * 100 + "%");
    }

    public void addProduct(Item produkt) {//ok
        if(produkt.ilosc==0){ return;}
        for (Item x : produkty) {
            if (x.compareTo(produkt)==0 && zajetaPojemnosc + produkt.masa * produkt.ilosc <= maxPojemnosc) {
                x.dodajIlosc(produkt);
                zajetaPojemnosc=zajetaPojemnosc+produkt.masa * produkt.ilosc;
                return;
            }

        }
        if (zajetaPojemnosc + produkt.masa * produkt.ilosc <= maxPojemnosc) {
            zajetaPojemnosc=zajetaPojemnosc+produkt.masa * produkt.ilosc;
            produkty.add(produkt);
            return;
        }

        System.err.println("Nie mozna dodac");

    }


    public void getProduct(Item produkt) {//ok
        for (Item x : produkty) {
            if (x.compareTo(produkt)==0) {
                x.ilosc--;
                zajetaPojemnosc=zajetaPojemnosc-x.masa;
                if (x.ilosc == 0) {
                    produkty.remove(x);
                }
                return;
            }
        }

    }

    public void removeProduct(Item produkt) {//ok
        for (Item x : produkty) {
            if (x.compareTo(produkt)==0) {
                zajetaPojemnosc=zajetaPojemnosc-x.masa*x.ilosc;
                produkty.remove(x);
                return;
            }
        }

    }

    public Item search(String nazwa) {//ok
        for (Item x : produkty) {
            if (nazwa.equals(x.nazwa)) {
                return x;
            }
        }
        return null;
    }


    public List<Item> searchPartial(String nazwa) {//ok
        List<Item> nowa = new ArrayList<Item>();

        for (Item x : produkty) {
            if (x.nazwa.contains(nazwa)) {
                nowa.add(x);
                x.print();
            }
        }
        return nowa;
    }


    public int countByCondition(ItemCondition s) {//ok
        int ile = 0;
        for (Item x : produkty) {
            if (x.stan == s) {
                ile++;
            }
        }
        return ile;
    }


    public void summary() {//ok
        System.out.println("MAGAZYN: " + nazwaMagazynu);
        for (Item x : produkty) {
            x.print();
        }
        System.out.println("zajeta masa: " + zajetaPojemnosc);
    }

    public List<Item> sortByName() {
        produkty.sort(new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.nazwa.compareTo(o2.nazwa);
        }
    });
        return produkty;
}


    public List<Item> sortByAmount() {
        Collections.sort(produkty, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.ilosc - o2.ilosc;
            }
        });
        return produkty;
    }


    public Item max() {//ok
        Item x = Collections.max(produkty, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(o1.ilosc, o2.ilosc);
            }
        });
        return x;
    }


}


