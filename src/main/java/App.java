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
            model.put("backpacking", backpacking);

            List<Item> thisBackpack = backpacking.getItems();
            model.put("myBackpack", thisBackpack);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("backpacks", Backpacking.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
