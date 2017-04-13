package com.xiberty.ecotips.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.xiberty.ecotips.R;

public class WebFragment extends Fragment {

    public static String PAGE = "page";
    private static Bundle args;

    public WebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_web, container, false);
        final WebView webContent = (WebView) rootView.findViewById(R.id.webView);
        if (args.getString(PAGE).equals("presentation"))
        webContent.loadUrl("file:///android_asset/presentacion.html");
        else if (args.getString(PAGE).equals("about"))
            webContent.loadUrl("file:///android_asset/about.html");
        else if (args.getString(PAGE).equals("service"))
            webContent.loadUrl("file:///android_asset/service.html");

        return rootView;
    }

    public static WebFragment newInstance(String page) {
        WebFragment fragment = new WebFragment();
        args = new Bundle();
        args.putString(PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
