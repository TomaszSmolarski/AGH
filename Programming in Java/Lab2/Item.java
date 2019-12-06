public class Item implements Comparable<Item> {
    public String nazwa;
    public double masa;
    public int ilosc;
    public ItemCondition stan;

    Item(String nazwa, double masa, ItemCondition stan, int ilosc) {
        this.nazwa = nazwa;
        this.masa = masa;
        this.ilosc = ilosc;
        this.stan = stan;
    }

    public void print() {
        System.out.println("Nazwa :" + nazwa + "\tMasa: " + masa + "\tIlosc: " + ilosc + "\tStan: " + stan);
    }

    @Override
    public int compareTo(Item x) {
        int compareName=nazwa.compareTo(x.nazwa);
        return compareName;
    }

    public void dodajIlosc(Item x) {
        this.ilosc = this.ilosc + x.ilosc;
    }
}
