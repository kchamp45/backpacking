import java.util.ArrayList;

/**
 * Created by Guest on 8/10/17.
 */
public class Item {

    private String name;
    private int cost;
    private int weight;
    private String brand;
    private static ArrayList<Item> instances = new ArrayList<Item>();
    private int iId;

    public Item(String name, int cost, int weight, String brand) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.brand = brand;
        instances.add(this);
        iId = instances.size();
    }


    public static ArrayList<Item> getAll() {
        return instances;
    }

    public static void clearAllItems() {
        instances.clear();
    }

    public int getId() {
        return iId;
    }

    public static Item findById(int id) {
        return instances.get(id - 1);
    }

    public void update(String name, int cost, int weight, String brand) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.brand = brand;
    }

    public void deleteItem() {
        instances.remove(iId-1);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public String getBrand() {
        return brand;
    }


}
