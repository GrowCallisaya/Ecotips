package com.xiberty.ecotips;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiberty.ecotips.adapters.IngredientAdapter;
import com.xiberty.ecotips.model.Ingredient;
import com.xiberty.ecotips.model.Product;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView ivImageExtended;
    private TextView tvReceta;

    private Product product;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;
    private IngredientAdapter mIngredientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail2);

        Intent intent = getIntent();
        String item= intent.getStringExtra("EVENT_OBJ");
        Gson gson = new Gson();
        product =  gson.fromJson(item, Product.class);

        ivImageExtended = (ImageView) findViewById(R.id.imagen_extendida);
        TextView product_title = (TextView) findViewById(R.id.new_text_title);
        tvReceta= (TextView) findViewById(R.id.txt_receta);

        product_title.setText(product.getName());
        tvReceta.setText(product.getRecet());
        //Changing fonts
        Typeface myTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Bold.ttf");
         product_title.setTypeface(myTypeface);


        //Mostrar imagen en Collapsing
        Glide.with(this)
                .load(product.getImage())
                .into(ivImageExtended);

        //Obtener el Reclycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        lManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(lManager);


        //TODO Lista FAKE


        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients = product.getIngredients();
        //Crear un nuevo adaptador
        mIngredientAdapter = new IngredientAdapter(ingredients);
        recycler.setAdapter(mIngredientAdapter);

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
