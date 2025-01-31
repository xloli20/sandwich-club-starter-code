package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView alsoKnown = findViewById(R.id.also_known_tv);
        TextView ingredients = findViewById(R.id.ingredients_tv);
        TextView placeOfOrigin = findViewById(R.id.origin_tv);
        TextView description = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            return;
        }

        //
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            return;
        }

        alsoKnown.setText(TextUtils.join(",", sandwich.getAlsoKnownAs()));
        ingredients.setText(TextUtils.join(", ", sandwich.getIngredients()));

        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        description.setText(sandwich.getDescription());

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void populateUI() {

    }
}
