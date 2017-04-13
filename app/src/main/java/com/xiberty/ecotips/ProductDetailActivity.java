package com.xiberty.ecotips;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiberty.ecotips.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView event_image;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetail);

        Intent intent = getIntent();
        String item= intent.getStringExtra("EVENT_OBJ");
        Gson gson = new Gson();
        product =  gson.fromJson(item, Product.class);

        event_image = (ImageView) findViewById(R.id.event_image_small);
        TextView event_title = (TextView) findViewById(R.id.new_text_title);
        TextView event_author = (TextView) findViewById(R.id.new_text_author);
        TextView event_date = (TextView) findViewById(R.id.new_text_date);
        event_title.setText(product.getName());
        event_author.setText(product.getIngredients());
        event_date.setText(product.getVotes()+"");

        //Changing fonts
        Typeface myTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface myTypeface2 = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Regular.ttf");
        event_title.setTypeface(myTypeface);
        event_date.setTypeface(myTypeface);
        event_author.setTypeface(myTypeface2);


        //Mostrar Imagen Detallada
        Glide.with(event_image.getContext())
                .load(product.getImage())
                .into(event_image);

        final Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this,R.color.accent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setTitle("");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
              return super.onOptionsItemSelected(item);
//                return true;
        }
    }


}
