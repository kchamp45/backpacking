import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/10/17.
 */
public class BackpackingTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Backpacking.clearAllBackpackings();
    }

    @Test
    public void NewObjectGetsCorrectlyCreated_true() throws Exception {
        Backpacking backpacking = new Backpacking ("Camping");
        assertTrue(backpacking instanceof Backpacking);
    }

    @Test
    public void BackpackingInstantiatesWithActivity_camping() throws Exception {
        Backpacking backpacking = new Backpacking ("Camping");
        assertEquals("Camping",backpacking.getActivity());
    }

    @Test
    public void AllBackPackingCorrectlyReturned_2() {
        Backpacking backpacking = new Backpacking("Camping");
        Backpacking otherBackpacking = new Backpacking ("Biking");
        assertEquals(2, Backpacking.getAll().size());
    }

    @Test
    public void AllBackpackingsContainsAllBackpackings_true() {
        Backpacking backpacking = new Backpacking("Camping");
        Backpacking otherBackpacking = new Backpacking ("Biking");
        assertTrue(Backpacking.getAll().contains(backpacking));
        assertTrue(Backpacking.getAll().contains(otherBackpacking));
    }

    @Test
    public void clearAllBackpackings_checkIfClearsData_0() throws Exception {
        Backpacking backpacking = new Backpacking("Camping");
        Backpacking otherBackpacking = new Backpacking ("Biking");
        Backpacking.clearAllBackpackings();
        assertEquals(0,Backpacking.getAll().size());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception{
        Backpacking myBackpacking = new Backpacking("Camping");
        assertEquals(LocalDateTime.now().getDayOfWeek(), myBackpacking.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void getId_backpackingsInstantiateWithAnID_1() throws Exception {
        Backpacking myBackpacking = new Backpacking("Camping");
        assertEquals(1, myBackpacking.getId());
    }

    @Test
    public void findById_getBackpackingInstance_otherBackpacking() throws Exception {
        Backpacking backpacking = new Backpacking("Camping");
        Backpacking otherBackpacking = new Backpacking ("Biking");
        assertEquals(otherBackpacking, Backpacking.findById(otherBackpacking.getId()));
    }

    @Test
    public void getItems_initiallyReturnsEmptyList_arrayList() throws Exception {
        Backpacking backpacking = new Backpacking("Camping");
        assertEquals(0, backpacking.getItems().size());
    }

    @Test
    public void addItems_addItemsToList_true() throws Exception {
        Backpacking backpacking = new Backpacking("Camping");
        Item item = new Item("Food", 10, 1, "kroger");
        backpacking.addItem(item);
        assertTrue(backpacking.getItems().contains(item));
    }

}