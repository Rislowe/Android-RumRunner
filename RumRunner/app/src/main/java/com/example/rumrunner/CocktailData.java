package com.example.rumrunner;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CocktailData implements Parcelable {
    final private String idDrink;
    final private String strDrink;
    final private String strAlcoholic;
    final private String strGlass;
    final private String strInstructions;
    final private String strDrinkThumb;
    private List<String> ingredientList = new ArrayList<String>();
    private List<String> measureList = new ArrayList<String>();

    //This is the Constructor for all Single Cocktail Objects
    public CocktailData(
            String idDrink,
            String strDrink,
            String strAlcoholic,
            String strGlass,
            String strInstructions,
            String strDrinkThumb,
            List<String> ingredientList,
            List<String> measureList
    ){
        this.idDrink = idDrink;
        this.strAlcoholic = strAlcoholic;
        this.strGlass = strGlass;
        this.strDrink = strDrink;
        this.strInstructions = strInstructions;
        this.strDrinkThumb = strDrinkThumb;
        this.ingredientList.addAll(ingredientList);
        this.measureList.addAll(measureList);
    }

    //Getter Functions

    public String getIdDrink()
    {
        return idDrink;
    }

    public String getStrDrink()
    {
        return strDrink;
    }

    public String getStrAlcoholic()
    {
        return strAlcoholic;
    }

    public String getStrGlass()
    {
        return strGlass;
    }

    public String getStrInstructions()
    {
        return strInstructions;
    }

    public String getStrDrinkThumb()
    {
        return strDrinkThumb;
    }

    public List<String> getIngredientList()
    {
        return ingredientList;
    }

    public List<String> getMeasureList()
    {
        return measureList;
    }


    //Parcelable Functions
    protected CocktailData(Parcel in) {
        idDrink = in.readString();
        strDrink = in.readString();
        strAlcoholic = in.readString();
        strGlass = in.readString();
        strInstructions = in.readString();
        strDrinkThumb = in.readString();
        ingredientList = in.createStringArrayList();
        measureList = in.createStringArrayList();
    }
    public static final Creator<CocktailData> CREATOR = new Creator<CocktailData>() {
        @Override
        public CocktailData createFromParcel(Parcel in) {
            return new CocktailData(in);
        }

        @Override
        public CocktailData[] newArray(int size) {
            return new CocktailData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idDrink);
        parcel.writeString(strDrink);
        parcel.writeString(strAlcoholic);
        parcel.writeString(strGlass);
        parcel.writeString(strInstructions);
        parcel.writeString(strDrinkThumb);
        parcel.writeList(ingredientList);
        parcel.writeList(measureList);
    }
}
