package com.example.rumrunner;

public class DrinkItem {
    private final long id;
    private final String strDrinkName;
    private final String strDrinkThumb;

    public DrinkItem(long id, String strDrinkName, String strDrinkThumb)
    {
        this.id=id;
        this.strDrinkName=strDrinkName;
        this.strDrinkThumb = strDrinkThumb;
    }

    public String getStrDrinkName(){return strDrinkName;}

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public long getId()
    {
        return id;
    }
}
