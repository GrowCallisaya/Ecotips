package com.xiberty.ecotips;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiberty.ecotips.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView product_image;
    private ImageView product_image_extended;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail2);

        Intent intent = getIntent();
        String item= intent.getStringExtra("EVENT_OBJ");
        Gson gson = new Gson();
        product =  gson.fromJson(item, Product.class);

//        product_image = (ImageView) findViewById(R.id.event_image_small);
        product_image_extended = (ImageView) findViewById(R.id.imagen_extendida);
//        TextView event_title = (TextView) findViewById(R.id.new_text_title);
//        TextView event_author = (TextView) findViewById(R.id.new_text_author);
//        TextView event_date = (TextView) findViewById(R.id.new_text_date);
//        event_title.setText(product.getName());
//        event_author.setText(product.getIngredients());
//        event_date.setText(product.getVotes()+"");

        //Changing fonts
//        Typeface myTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Bold.ttf");
//        Typeface myTypeface2 = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Regular.ttf");
//        event_title.setTypeface(myTypeface);
//        event_date.setTypeface(myTypeface);
//        event_author.setTypeface(myTypeface2);


        //Mostrar Imagen Detallada
//        Glide.with(product_image.getContext())
//                .load(product.getImage())
//                .into(product_image);

        //Mostrar imagen en Collapsing
        Glide.with(this)
                .load(product.getImage())
                .into(product_image_extended);


        final Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this,R.color.accent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setTitle(" ");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
