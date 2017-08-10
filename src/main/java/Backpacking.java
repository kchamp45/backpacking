import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 8/10/17.
 */
public class Backpacking {


    private String activity;
    private static ArrayList<Backpacking> instances = new ArrayList<>();
    private LocalDateTime createdAt;
    private int id;
    private List<Item> items;

    public Backpacking(String activity) {
        this.activity = activity;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = instances.size();
        items = new ArrayList<Item>();
    }
    //Getters
    public String getActivity() {
        return activity;
    }

    public static ArrayList<Backpacking> getAll(){
        return instances;
    }

    public static void clearAllBackpackings(){
        instances.clear();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public static Backpacking findById(int id){
        return instances.get(id-1);
    }

    public List<Item> getItems() {
        return items;
    }
    //Setters
    public void addItem(Item item){
        items.add(item);
    }


}