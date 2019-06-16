package com.example.myapplication;

import android.widget.ImageView;

public class BeneficiaryClass {
    private String name;
    private ImageView image;

    public BeneficiaryClass(String name, ImageView image) {
        this.name = name;
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

}
