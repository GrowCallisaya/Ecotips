package com.xiberty.ecotips.model;

/**
 * Created by growcallisaya on 21/3/17.
 */

public class Product {

    private String name;
    private String ingredients;
    private String nutricional_value;
    private int votes;
    private String image;

    public Product(String image,String name, String ingredients, String nutricional_value, int votes) {
        this.image = image;
        this.name = name;
        this.ingredients = ingredients;
        this.nutricional_value = nutricional_value;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getNutricional_value() {
        return nutricional_value;
    }

    public void setNutricional_value(String nutricional_value) {
        this.nutricional_value = nutricional_value;
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

