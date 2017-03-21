package com.xiberty.cinemateca.adapters;


import android.support.v4.app.Fragment;

/**
 * Created by growcallisaya on 14/3/17.
 */

public class PageItem  {
    public Fragment fragment;
    public String title;
    public int icon;

    public PageItem() {
    }

    public PageItem(Fragment fragment, String title, int icon) {
        this.fragment = fragment;
        this.title = title;
        this.icon = icon;
    }

    public PageItem(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }
}
