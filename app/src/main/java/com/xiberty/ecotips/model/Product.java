package com.xiberty.ecotips.model;

import java.util.ArrayList;

/**
 * Created by growcallisaya on 21/3/17.
 */

public class Product {

    private String name;
    private ArrayList<Ingredient> ingredients;
    private String recet;
    private int votes;
    private String image;
        

    public Product(String image,String name, ArrayList<Ingredient> ingredients, String recet, int votes) {
        this.image = image;
        this.name = name;
        this.ingredients = ingredients;
        this.recet = recet;
        this.votes = votes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecet() {
        return recet;
    }

    public void setRecet(String recet) {
        this.recet = recet;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }


    public int getId() {
        return name.hashCode();
    }
}

