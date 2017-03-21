package com.xiberty.cinemateca;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiberty.cinemateca.model.Fecha;
import com.xiberty.cinemateca.model.Event;

public class EventDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "com.xiberty.cinemanteca.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private ImageView event_image;
    private Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetail);

        Intent intent = getIntent();
        String item= intent.getStringExtra("EVENT_OBJ");
        Gson gson = new Gson();
          event =  gson.fromJson(item, Event.class);

        event_image = (ImageView) findViewById(R.id.event_image_small);
        TextView event_title = (TextView) findViewById(R.id.new_text_title);
        TextView event_author = (TextView) findViewById(R.id.new_text_author);
        TextView event_date = (TextView) findViewById(R.id.new_text_date);
        event_title.setText(event.getTitle());
        event_author.setText(event.getAuthor());
        Fecha date = new Fecha(event.getDate());
        event_date.setText(date.getFormatDate());

        final WebView event_descrition = (WebView) findViewById(R.id.new_text_description);

        String data = "<html><link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\" /><body class=\"homepage\"> \t<div id=\"page-wrapper\">" +
                "\t\t\t\t<div id=\"header-wrapper\">\n" +
                "\t\t\t\t\t<div id=\"header\">"+event.getDescription()+"</div></div></body></html>";
        event_descrition.loadDataWithBaseURL("file:///android_asset/", data,"text/html; charset=utf-8", "UTF-8",null);

        //Changing fonts
        Typeface myTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Bold.ttf");
        Typeface myTypeface2 = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Regular.ttf");
        event_title.setTypeface(myTypeface);
        event_date.setTypeface(myTypeface);
        event_author.setTypeface(myTypeface2);


        //Mostrar Imagen Detallada
        Glide.with(event_image.getContext())
                .load(event.getImage())
                .into(event_image);

        final Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this,R.color.accent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setTitle(" ");
        //Appear text when is above
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Eventos" );
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(EventDetailActivity.this, MainActivity.class);
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
