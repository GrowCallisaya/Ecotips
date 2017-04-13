package com.xiberty.ecotips.adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiberty.ecotips.DetailActivity;
import com.xiberty.ecotips.model.Fecha;
import com.xiberty.ecotips.model.Notice;
import com.xiberty.ecotips.R;

import java.util.List;

/**
 * Created by growcallisaya on 9/3/17.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NewsViewHolder>{
    private final Context context;
    private static List<Notice> items;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        private final Context context;
        public ImageView image;
        public TextView title;
        public TextView date;
        public Button btnMore;

        public NewsViewHolder(View view) {
            super(view);
            image = (ImageView)  view.findViewById(R.id.news_image);
            title = (TextView) view.findViewById(R.id.news_title);
            date = (TextView) view.findViewById(R.id.news_date);
            btnMore = (Button) view.findViewById(R.id.btn_seemore);
            context = view.getContext();

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    final Intent intent = new Intent(context, DetailActivity.class);
                    Gson gson = new Gson();
                    String jsonNews = gson.toJson(items.get(pos));
                    intent.putExtra("OBJECT",jsonNews);
                    context.startActivity(intent);

                }
            });
        }

    }

    public NoticeAdapter(List<Notice> items, Context c) {
        this.items = items;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Create a new View
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.notice_card,parent,false);
        return new NewsViewHolder(v);
    }

    //replace the content of one View
    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        //Creating the Card in one position
        Glide.with(context)
                .load(items.get(position).getImage())
                .into(holder.image);
//        holder.image.setImageResource(items.get(position).getImage());
        holder.title.setText(items.get(position).getTitle());
        Fecha fecha = new Fecha(items.get(position).getDate().substring(0,10));
        holder.date.setText(fecha.getFormatDate());
        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Raleway-Bold.ttf");
        holder.title.setTypeface(myTypeface);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, DetailActivity.class);
                Gson gson = new Gson();
                String jsonNews = gson.toJson(items.get(position));
                intent.putExtra("OBJECT",jsonNews);
                context.startActivity(intent);
            }
        });

    }

}
