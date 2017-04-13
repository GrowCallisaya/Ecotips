package com.xiberty.ecotips.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiberty.ecotips.R;
import com.xiberty.ecotips.adapters.PageAdapter;
import com.xiberty.ecotips.adapters.PageItem;


public class HomeFragment extends Fragment {
    ViewPager mViewPager;
    private View view;
    public static int FEED_NOTICE = 1;
    public static int FEED_BILLDBOARD = 0;
    private SparseArray<PageItem> pages = new SparseArray<PageItem>();
    private NoticeFragment noticeFragment = new NoticeFragment();
    private ProductFragment productFragment = new ProductFragment();

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);//Set Adapter

        if(savedInstanceState == null){
                pages.append(HomeFragment.FEED_NOTICE,new PageItem(noticeFragment,"NOTICIAS"));
                pages.append(HomeFragment.FEED_BILLDBOARD,new PageItem(productFragment,"CARTELERA"));
                PageAdapter pagerAdapter  =   new PageAdapter(pages,getChildFragmentManager());
                TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
                mViewPager = (ViewPager) view.findViewById(R.id.pager);
                mViewPager.setAdapter(pagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
        }
        return view;

    }

}
