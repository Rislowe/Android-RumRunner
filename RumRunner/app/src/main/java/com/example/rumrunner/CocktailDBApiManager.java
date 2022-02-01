package com.example.rumrunner;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CocktailDBApiManager {
    private String name;

    public CocktailDBApiManager(){};

    public CocktailDBApiManager(String name)
    {
        this.name = name;
    }


    public CocktailData getRandomCocktail() throws ApiException, JSONException
    {
        JSONObject json = requestRandom();

        JSONArray drinks = json.getJSONArray("drinks");
        JSONObject drink = drinks.getJSONObject(0);

        CocktailData data = parseJSON(drink);

        return data;
    }


    private JSONObject requestRandom() throws ApiException
    {
        final String route = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

        HttpURLConnection urlConnection;

        JSONObject json = null;

        try
        {
            URL url = new URL(route);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inStream = urlConnection.getInputStream();

            BufferedReader bReader = new BufferedReader((
                    new InputStreamReader(inStream)
            ));

            String temp;

            StringBuilder response = new StringBuilder();

            while((temp = bReader.readLine()) != null)
            {
                response.append(temp);
            }

            json = (JSONObject) new JSONTokener(response.toString()).nextValue();
            Log.e(MainActivity.LOG_KEY, json.toString());

        } catch (IOException | JSONException e) {
            Log.e(MainActivity.LOG_KEY, e.getClass().getName() + " : " + e.getMessage());
            throw new ApiException("Unable to process request");
        }

        return json;
    }

    private CocktailData parseJSON(JSONObject json) throws ApiException
    {
        if(json == null)
        {
            Log.e(MainActivity.LOG_KEY, "parseJson parameter is null");
            return null;
        }

        CocktailData data = null;

        try
        {
            String idDrink = json.getString("idDrink");
            String strDrink = json.getString("strDrink");
            String strAlcoholic = json.getString("strAlcoholic");
            String strGlass = json.getString("strGlass");
            String strInstructions = json.getString("strInstructions");
            String strDrinkThumb = json.getString("strDrinkThumb");

            List<String> ingredientList = assembleIngredientList(json);
            List<String> measureList = assembleMeasureList(json);

            data = new CocktailData(
                    idDrink,
                    strDrink,
                    strAlcoholic,
                    strGlass,
                    strInstructions,
                    strDrinkThumb,
                    ingredientList,
                    measureList
            );
        }
        catch (JSONException e)
        {
            Log.e(MainActivity.LOG_KEY, e.getClass().getName() + " : " + e.getMessage());
            throw new ApiException("Unable to process JSON data");
        }

        return data;
    }

    private List<String> assembleIngredientList(JSONObject json) throws ApiException
    {
        List<String> ingredientList = null;
        try {
            String strIngredient1 = json.getString("strIngredient1");
            String strIngredient2 = json.getString("strIngredient2");
            String strIngredient3 = json.getString("strIngredient3");
            String strIngredient4 = json.getString("strIngredient4");
            String strIngredient5 = json.getString("strIngredient5");
            String strIngredient6 = json.getString("strIngredient6");
            String strIngredient7 = json.getString("strIngredient7");
            String strIngredient8 = json.getString("strIngredient8");
            String strIngredient9 = json.getString("strIngredient9");
            String strIngredient10 = json.getString("strIngredient10");
            String strIngredient11 = json.getString("strIngredient11");
            String strIngredient12 = json.getString("strIngredient12");
            String strIngredient13 = json.getString("strIngredient13");
            String strIngredient14 = json.getString("strIngredient14");
            String strIngredient15 = json.getString("strIngredient15");

            ingredientList = new ArrayList<String>();
            ingredientList.add(strIngredient1);
            if(!strIngredient2.equals(""))
            {
                ingredientList.add(strIngredient2);
            }
            if(!strIngredient3.equals(""))
            {
                ingredientList.add(strIngredient3);
            }
            if(!strIngredient4.equals(""))
            {
                ingredientList.add(strIngredient4);
            }
            if(!strIngredient5.equals(""))
            {
                ingredientList.add(strIngredient5);
            }
            if(!strIngredient6.equals(""))
            {
                ingredientList.add(strIngredient6);
            }
            if(!strIngredient7.equals(""))
            {
                ingredientList.add(strIngredient7);
            }
            if(!strIngredient8.equals(""))
            {
                ingredientList.add(strIngredient8);
            }
            if(!strIngredient9.equals(""))
            {
                ingredientList.add(strIngredient3);
            }
            if(!strIngredient10.equals(""))
            {
                ingredientList.add(strIngredient10);
            }
            if(!strIngredient11.equals(""))
            {
                ingredientList.add(strIngredient11);
            }
            if(!strIngredient12.equals(""))
            {
                ingredientList.add(strIngredient12);
            }
            if(!strIngredient13.equals(""))
            {
                ingredientList.add(strIngredient13);
            }
            if(!strIngredient14.equals(""))
            {
                ingredientList.add(strIngredient14);
            }
            if(!strIngredient15.equals(""))
            {
                ingredientList.add(strIngredient15);
            }
        }
        catch(JSONException e)
        {
            Log.e(MainActivity.LOG_KEY, e.getClass().getName() + " : " + e.getMessage());
            throw new ApiException("Unable to process JSON data");
        }
        return ingredientList;
    }

    private List<String> assembleMeasureList(JSONObject json) throws ApiException {
        List<String> measureList = null;
        try {
            String strMeasure1 = json.getString("strMeasure1");
            String strMeasure2 = json.getString("strMeasure2");
            String strMeasure3 = json.getString("strMeasure3");
            String strMeasure4 = json.getString("strMeasure4");
            String strMeasure5 = json.getString("strMeasure5");
            String strMeasure6 = json.getString("strMeasure6");
            String strMeasure7 = json.getString("strMeasure7");
            String strMeasure8 = json.getString("strMeasure8");
            String strMeasure9 = json.getString("strMeasure9");
            String strMeasure10 = json.getString("strMeasure10");
            String strMeasure11 = json.getString("strMeasure11");
            String strMeasure12 = json.getString("strMeasure12");
            String strMeasure13 = json.getString("strMeasure13");
            String strMeasure14 = json.getString("strMeasure14");
            String strMeasure15 = json.getString("strMeasure15");

            measureList = new ArrayList<String>();
            measureList.add(strMeasure1);
            if(!strMeasure1.equals(""))
            {
                measureList.add(strMeasure2);
            }
            if(!strMeasure3.equals(""))
            {
                measureList.add(strMeasure3);
            }
            if(!strMeasure4.equals(""))
            {
                measureList.add(strMeasure4);
            }
            if(!strMeasure5.equals(""))
            {
                measureList.add(strMeasure5);
            }
            if(!strMeasure6.equals(""))
            {
                measureList.add(strMeasure6);
            }
            if(!strMeasure7.equals(""))
            {
                measureList.add(strMeasure7);
            }
            if(!strMeasure8.equals(""))
            {
                measureList.add(strMeasure8);
            }
            if(!strMeasure9.equals(""))
            {
                measureList.add(strMeasure9);
            }
            if(!strMeasure10.equals(""))
            {
                measureList.add(strMeasure10);
            }
            if(!strMeasure11.equals(""))
            {
                measureList.add(strMeasure11);
            }
            if(!strMeasure12.equals(""))
            {
                measureList.add(strMeasure12);
            }
            if(!strMeasure13.equals(""))
            {
                measureList.add(strMeasure13);
            }
            if(!strMeasure14.equals(""))
            {
                measureList.add(strMeasure14);
            }
            if(!strMeasure15.equals(""))
            {
                measureList.add(strMeasure15);
            }
        }
        catch(JSONException e)
        {
            Log.e(MainActivity.LOG_KEY, e.getClass().getName() + " : " + e.getMessage());
            throw new ApiException("Unable to process JSON data");
        }
        return measureList;
    }
}
