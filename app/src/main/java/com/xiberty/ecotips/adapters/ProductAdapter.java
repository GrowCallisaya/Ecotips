package com.xiberty.ecotips.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiberty.ecotips.ProductDetailActivity;
import com.xiberty.ecotips.R;
import com.xiberty.ecotips.model.Product;

import java.util.List;

/**
 * Created by growcallisaya on 9/3/17.
 */

public class ProductAdapter extends BaseAdapter {
    private Context context;
    List<Product> items;

    public ProductAdapter(Context context, List<Product> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_categories, parent, false);
        }

        ImageView event_image = (ImageView) view.findViewById(R.id.event_image);
        TextView event_title = (TextView) view.findViewById(R.id.event_title);
        TextView event_date = (TextView) view.findViewById(R.id.event_date);
        Button btnVer= (Button) view.findViewById(R.id.btn_vermas);
        RatingBar mRatingBar= (RatingBar) view.findViewById(R.id.ratingBar);


        //Changing fonts
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-ExtraBold.ttf");
        event_title.setTypeface(myTypeface);

        final int pos= position;
        final View vi = view;

        final Product item = items.get(position);
        Glide.with(event_image.getContext())
                .load(item.getImage())
                .into(event_image);
        event_title.setText(item.getName());
        event_date.setText(item.getVotes()+"");
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                Gson gson = new Gson();
                String jsonEvent = gson.toJson(items.get(pos));
                intent.putExtra("EVENT_OBJ",jsonEvent);
                vi.getContext().startActivity(intent);
            }
        });

        return view;
    }


}
