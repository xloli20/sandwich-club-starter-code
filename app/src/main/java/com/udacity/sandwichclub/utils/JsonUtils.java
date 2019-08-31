package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwich = new JSONObject(json);
        JSONObject name = sandwich.getJSONObject("name");
        String mainName = name.getString("mainName");

        String placeOfOrigin = sandwich.getString("placeOfOrigin");
        if (placeOfOrigin.isEmpty()) {
            placeOfOrigin = "none";
        }

        String description = sandwich.getString("description");
        if (description.isEmpty()) {
            description = "Unknown";
        }
        String image = sandwich.getString("image");

        JSONArray jsonArrayAlsoKnownAs = name.getJSONArray("alsoKnownAs");
        JSONArray jsonArrayIngredients = sandwich.getJSONArray("ingredients");


        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();

        if (jsonArrayAlsoKnownAs.length() != 0) {
            for (int i = 0; i < jsonArrayAlsoKnownAs.length(); i++) {
                alsoKnownAs.add(String.valueOf(jsonArrayAlsoKnownAs.get(i)) + "\n");
            }
        } else {
            alsoKnownAs.add("None");
        }

        for (int i = 0; i < jsonArrayIngredients.length(); i++) {
            ingredients.add(String.valueOf(jsonArrayIngredients.get(i)) + "\n");
        }


        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
