package com.xiberty.ecotips.model;

/**
 * Created by growcallisaya on 20/4/17.
 */

public class Ingredient {
    private String image;
    private String name;
    private String nutricional_value;

    public Ingredient(String image, String name, String nutricional_value) {
        this.image = image;
        this.name = name;
        this.nutricional_value = nutricional_value;
    }

    public String getNutricional_value() {
        return nutricional_value;
    }

    public void setNutricional_value(String nutricional_value) {
        this.nutricional_value = nutricional_value;
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
}
