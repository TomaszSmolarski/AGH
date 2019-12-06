import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

public class FulfillmentCenterContainer {
    Map<String, FulfillmentCenter> magazyny = new TreeMap<String, FulfillmentCenter>();

    public void addCenter(String magazyn, double pojemnosc) {
        FulfillmentCenter mag = new FulfillmentCenter(magazyn, pojemnosc);
        magazyny.put(magazyn, mag);
    }


    public void removeCenter(String magazyn) {
        for (String x : magazyny.keySet()) {
            if (x.equals(magazyn)) {
                magazyny.remove(x);
                return;
            }

        }
    }

    public List<FulfillmentCenter> findEmpty() {
        List<FulfillmentCenter> lista = new ArrayList<FulfillmentCenter>();
        for (FulfillmentCenter x : magazyny.values()) {
            if (x.zajetaPojemnosc == 0) {
                lista.add(x);
                x.print();
            }
        }
        return lista;
    }

    public void summary() {
        for (FulfillmentCenter x : magazyny.values()) {
            x.print();
        }
    }


}
