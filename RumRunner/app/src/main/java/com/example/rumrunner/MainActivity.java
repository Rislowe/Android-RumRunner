package com.example.rumrunner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements OnCocktailRequestCompleted{
    public static final String COCKTAIL_KEY = "COCKTAIL_DATA";
    public static final String LOG_KEY = "RumRunner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the buttons
        Button menuButton = findViewById(R.id.cocktailMenuButton);
        Button randomButton = findViewById(R.id.randomDrinkButton);
        Button faveButton = findViewById(R.id.favouriteListButton);

        //Set up link to the TextView
        TextView warningBox = findViewById(R.id.warningTextView);

        //OnClick Listeners for the Menu, Random and Favourites buttons.
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeFragment startup = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentSocket, startup).commit();
            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CocktailRequest task = new CocktailRequest(MainActivity.this);
                task.execute();
            }
        });

        faveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FaveListFragment faveListFragment = new FaveListFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentSocket, faveListFragment).commit();
            }
        });

        //Set up the Home Fragment
        HomeFragment startup = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentSocket, startup).commit();
    }

    @Override
    public void onTaskCompleted(CocktailData data) {
        Bundle args = new Bundle();
        args.putParcelable(COCKTAIL_KEY, data);

        DrinkViewFragment drinkDetails = new DrinkViewFragment();
        drinkDetails.setArguments(args);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentSocket, drinkDetails).commit();
    }

    @Override
    public void onTaskFailed() {
        String text = getResources().getString(R.string.search_error);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(MainActivity.this, text, duration);
        toast.show();
    }

    public static class CocktailRequest extends AsyncTask<String, Void, CocktailData>
    {
        private OnCocktailRequestCompleted listener;

        public CocktailRequest(OnCocktailRequestCompleted listener) {this.listener = listener;}

        @Override
        protected CocktailData doInBackground(String... strings) {
            CocktailData data = null;

            CocktailDBApiManager apiManager = new CocktailDBApiManager();

            try
            {
                data = apiManager.getRandomCocktail();
            }
            catch (ApiException | JSONException e)
            {
                Log.e(LOG_KEY, e.getClass().getName() + " : " + e.getMessage());
            }

            return data;
        }

        @Override
        protected void onPostExecute(CocktailData data)
        {
            if(data!=null)
            {
                listener.onTaskCompleted(data);
            }
            else
            {
                listener.onTaskFailed();
            }
        }
    }
}