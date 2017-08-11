import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by Guest on 8/10/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Backpacking> allBags = Backpacking.getAll();
            model.put("backpackings", allBags);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/backpack/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "backpack-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/backpack/new", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String activity = req.queryParams("bagName");
            String newName = req.queryParams("itemName");
            int newCost = Integer.parseInt(req.queryParams("cost"));
            int newWeight = Integer.parseInt(req.queryParams("weight"));
            String newBrand = req.queryParams("brand");
            Backpacking backpacking = new Backpacking(activity);
            Item item = new Item(newName, newCost, newWeight, newBrand);
            //add item to the backpack class by using the method
            backpacking.addItem(item);
            model.put("backpackings", backpacking);

            List<Item> thisBackpack = backpacking.getItems();
            model.put("myBackpack", thisBackpack);
            return new ModelAndView(model, "backpack-success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/backpacking/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfItemToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Item foundItem = Item.findById(idOfItemToFind); //use it to find post
            model.put("post", foundItem); //add it to model for template to display
            return new ModelAndView(model, "backpack-details.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        get("/backpacking/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfItemToEdit = Integer.parseInt(req.params("id"));
            Item editItem = Item.findById(idOfItemToEdit);
            model.put("editItem", editItem);
            return new ModelAndView(model, "backpack-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/backpacking/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newItemName = req.queryParams("itemName");
            int newCost = Integer.parseInt(req.queryParams("cost"));
            int newWeight = Integer.parseInt(req.queryParams("weight"));
            String newBrand = req.queryParams("brand");
            int idOfItemToEdit = Integer.parseInt(req.params("id"));
            Item editItem = Item.findById(idOfItemToEdit);
            editItem.update(newItemName, newCost, newWeight, newBrand); //don’t forget me
            return new ModelAndView(model, "backpack-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/backpacking/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfItemToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Item itemToBeDeleted = Item.findById(idOfItemToDelete); //use it to find post
            itemToBeDeleted.deleteItem();
            return new ModelAndView(model, "backpack-success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/backpacking/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Item.clearAllItems();
            return new ModelAndView(model, "backpack-success.hbs");
        }, new HandlebarsTemplateEngine());

    }

}
