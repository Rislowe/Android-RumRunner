package com.example.rumrunner;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.res.FontResourcesParserCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rumrunner.domain.DatabaseHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkViewFragment extends Fragment {

    DrinkListManager drinkListManager;
    public DrinkViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        CocktailData data = getArguments().getParcelable(MainActivity.COCKTAIL_KEY);

        View view = inflater.inflate(R.layout.fragment_drink_view, container, false);

        TextView drinkNameTextView = view.findViewById(R.id.drinkNameTextView);

        ImageView drinkThumbImageView = view.findViewById(R.id.drinkThumbImageView);

        TextView ingredientTextView = view.findViewById(R.id.ingredientsTextView);

        TextView methodTextView = view.findViewById(R.id.methodTextView);

        Button favouriteButton = view.findViewById(R.id.favouriteButton);

        //set fave button
        drinkListManager = new DrinkListManager(view.getContext());
        boolean exists = false;

        List<DrinkItem> items = drinkListManager.getItems();

        for(DrinkItem item: items)
        {
            if(Long.parseLong(data.getIdDrink()) == item.getId())
            {
                exists = true;
            }
        }

        if (exists)
        {
            favouriteButton.setSelected(true);
        }
        else
        {
            favouriteButton.setSelected(false);
        }

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrinkItem item = new DrinkItem(Long.parseLong(data.getIdDrink()), data.getStrDrink(), data.getStrDrinkThumb());

                favouriteButton.setSelected(!favouriteButton.isSelected());


                if(favouriteButton.isSelected())
                {
                    favouriteButton.setText(R.string.confirmed_favourite);

                    drinkListManager.addItem(item);
                }
                else
                {
                    favouriteButton.setText(R.string.fave_button_text);
                    drinkListManager.removeItem(item);
                }

            }
        });

        if (data!=null)
        {
            String ingredientString = "";
            List<String> ingredientList = data.getIngredientList();
            List<String> measureList = data.getMeasureList();

            drinkNameTextView.setText(data.getStrDrink());

           Picasso.get().load(data.getStrDrinkThumb()).into(drinkThumbImageView);

            for (int i = 0; i < 15; i++)
            {
                if(measureList.get(i) != "null" && ingredientList.get(i) != "null"){
                    ingredientString += measureList.get(i) + " " + ingredientList.get(i) + ", ";
                }
            }

            char ingredientArray[] = ingredientString.toCharArray();
            ingredientTextView.setText(ingredientArray, 0, ingredientArray.length);

            methodTextView.setText(data.getStrInstructions());
        }
        // Inflate the layout for this fragment
        return view;
    }
}