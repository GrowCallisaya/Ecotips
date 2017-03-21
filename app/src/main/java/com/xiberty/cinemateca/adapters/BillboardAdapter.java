package com.xiberty.cinemateca.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiberty.cinemateca.R;
import com.xiberty.cinemateca.model.Event;
import com.xiberty.cinemateca.model.Fecha;

import java.util.List;

/**
 * Created by growcallisaya on 9/3/17.
 */

public class BillboardAdapter extends BaseAdapter {
    private Context context;
    List<Event> items;

    public BillboardAdapter(Context context, List<Event> items) {
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
            view = inflater.inflate(R.layout.grid_item, parent, false);
        }

        ImageView event_image = (ImageView) view.findViewById(R.id.event_image);
        TextView event_title = (TextView) view.findViewById(R.id.event_title);
        TextView event_date = (TextView) view.findViewById(R.id.event_date);

        //Changing fonts
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-ExtraBold.ttf");
        event_title.setTypeface(myTypeface);



        final Event item = items.get(position);
        Glide.with(event_image.getContext())
                .load(item.getImage())
                .into(event_image);
        event_title.setText(item.getTitle());
        Fecha f= new Fecha(item.getDate());
        event_date.setText(f.getFormatDate());

        return view;
    }


}
