package com.xiberty.ecotips.adapters;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xiberty.ecotips.R;
import com.xiberty.ecotips.model.Ingredient;

import java.util.ArrayList;

/**
 * Created by growcallisaya on 20/4/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    ArrayList<Ingredient> items;

    public final static class IngredientViewHolder  extends RecyclerView.ViewHolder{
        public ImageView ivImagen;
        public TextView tvName;

        public IngredientViewHolder(View v) {
            super(v);
            ivImagen = (ImageView) v.findViewById(R.id.ingredient_image);
            tvName = (TextView) v.findViewById(R.id.ingredient_name);

        }
    }


    /** Constructor **/
    public IngredientAdapter(ArrayList<Ingredient> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient,parent,false);
        return new IngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, final int position) {
        Glide.with(holder.ivImagen.getContext())
                .load(items.get(position).getImage())
                .into(holder.ivImagen);
        holder.tvName.setText(items.get(position).getName());
        holder.ivImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "ES", Snackbar.LENGTH_LONG);
                // Get the Snackbar's layout view
                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                layout.setBackgroundColor(Color.rgb(71,218,173));
                // Hide the text
                TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                textView.setVisibility(View.INVISIBLE);

                // Inflate our custom view
                LayoutInflater inflater = (LayoutInflater)v.getContext().getSystemService
                        (v.getContext().LAYOUT_INFLATER_SERVICE);
                View snackView = inflater.inflate(R.layout.my_snackbar, null);

                // Configure the view
                ImageView imageView = (ImageView) snackView.findViewById(R.id.ingredient_image);
                TextView textViewTop = (TextView) snackView.findViewById(R.id.ingredient_name);
                Glide.with(imageView.getContext())
                        .load(items.get(position).getImage())
                        .into(imageView);
                textViewTop.setText(items.get(position).getName());
                textViewTop.setTextColor(Color.WHITE);

                // Add the view to the Snackbar's layout
                layout.addView(snackView, 0);
                // Show the Snackbar
                snackbar.show();
            }
        });
    }








}


