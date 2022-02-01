package com.example.rumrunner;

public interface OnCocktailRequestCompleted {
    void onTaskCompleted(CocktailData data);
    void onTaskFailed();
}
