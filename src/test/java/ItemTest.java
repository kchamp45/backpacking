import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/10/17.
 */
public class ItemTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Item.clearAllItems();
    }

    @Test
    public void NewObjectGetsCorrectlyCreated_true() throws Exception {
        Item item = new Item ("Food", 10, 1, "kroger");
        assertTrue(item instanceof Item);
    }

    @Test
    public void AllBackPackingCorrectlyReturned_2() {
        Item item = new Item("Food", 10, 1, "kroger");
        Item otherItem = new Item ("Tent", 60, 10,"coleman");
        assertEquals(2, Item.getAll().size());
    }

    @Test
    public void AllItemsContainsAllItems_true() {
        Item item = new Item("Food", 10, 1, "kroger");
        Item otherItem = new Item ("Tent", 60, 10,"coleman");
        assertTrue(Item.getAll().contains(item));
        assertTrue(Item.getAll().contains(otherItem));
    }

    @Test
    public void clearAllItems_checkIfClearsData_0() throws Exception {
        Item item = new Item("Food", 10, 1, "kroger");
        Item otherItem = new Item ("Tent", 60, 10,"coleman");
        Item.clearAllItems();
        assertEquals(0,Item.getAll().size());
    }

    @Test
    public void getId_itemsInstantiateWithAnID_1() throws Exception {
        Item myItem = new Item("Food", 10, 1, "kroger");
        assertEquals(1, myItem.getId());
    }

    @Test
    public void findById_getItemInstance_otherItem() throws Exception {
        Item item = new Item("Food", 10, 1, "kroger");
        Item otherItem = new Item ("Tent", 60, 10,"coleman");
        assertEquals(otherItem, Item.findById(otherItem.getId()));
    }

    @Test
    public void updateChangesPostContent() throws Exception {
        Item item = new Item("Food", 10, 1, "kroger");
        String formerName = item.getName();
        int formerCost = item.getCost();
        int formerWeight = item.getWeight();
        String formerBrand = item.getBrand();
        int formerId = item.getId();

        item.update("Can food", 10, 1, "del Monte");

        assertEquals(formerId, item.getId());
        assertEquals(formerCost, item.getCost());
        assertNotEquals(formerName, item.getName());
    }
    @Test
    public void deleteDeletesASpecificPost() throws Exception {
        Item item = new Item("Food", 10, 1, "kroger");
        Item otherItem = new Item ("Tent", 60, 10,"coleman");
        item.deleteItem();
        assertEquals(1, Item.getAll().size()); //one is left
        assertEquals(Item.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
    }
}