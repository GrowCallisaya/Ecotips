package com.xiberty.ecotips;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xiberty.ecotips.model.Fecha;
import com.xiberty.ecotips.model.Notice;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String item= intent.getStringExtra("OBJECT");
        Gson gson = new Gson();
        Notice notice =  gson.fromJson(item, Notice.class);

        ImageView notice_image = (ImageView) findViewById(R.id.news_image_collapsed);
        TextView notice_title = (TextView) findViewById(R.id.new_text_title);
        TextView notice_author = (TextView) findViewById(R.id.new_text_author);
        TextView notice_date = (TextView) findViewById(R.id.new_text_date);
        Glide.with(this)
                .load(notice.getImage())
                .into(notice_image);

        notice_title.setText(notice.getTitle());
        notice_author.setText(notice.getAuthor());
        Fecha mDate= new Fecha(notice.getDate());
        notice_date.setText(mDate.getFormatDate());

        final WebView notice_description = (WebView) findViewById(R.id.new_text_description);

        String data = "<html><link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\" /><body class=\"homepage\"> \t<div id=\"page-wrapper\">" +
                "\t\t\t\t<div id=\"header-wrapper\">\n" +
                "\t\t\t\t\t<div id=\"header\">"+notice.getDescription()+"</div></div></body></html>";
        notice_description.loadDataWithBaseURL("file:///android_asset/", data,"text/html; charset=utf-8", "UTF-8",null);


        //Changing fonts
        Typeface myTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Bold.ttf");
        notice_title.setTypeface(myTypeface);



        final Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this,R.color.accent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setTitle(" ");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
