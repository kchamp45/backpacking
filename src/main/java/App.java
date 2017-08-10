import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
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
            Backpacking backpacking = new Backpacking(activity);
            model.put("backpacking", backpacking);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("backpacks", Backpacking.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
