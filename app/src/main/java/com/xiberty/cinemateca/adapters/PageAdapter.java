package com.xiberty.cinemateca.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

/**
 * Created by growcallisaya on 14/3/17.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private final String  TAG = PageAdapter.class.getSimpleName().toUpperCase();
    private SparseArray<PageItem> pages;
    private FragmentManager fm;

    public PageAdapter(SparseArray<PageItem> pages,FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position).fragment;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).title;
    }
}
