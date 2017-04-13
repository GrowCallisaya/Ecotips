package com.xiberty.ecotips.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.xiberty.ecotips.ProductDetailActivity;
import com.xiberty.ecotips.R;
import com.xiberty.ecotips.adapters.CategoryAdapter;
import com.xiberty.ecotips.model.Product;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    private GridView gridView;
    private CategoryAdapter adaptador;
    private List<Product> items = new ArrayList<>();
    private View rootView;
    private int c=0;

    public CategoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        items.clear();
        super.onStart();
        items.add(new Product("http://www.girlmakesfood.com/wp-content/uploads/2013/03/Healthy-Thin-Mint-Smoothie-1.jpg","Stevia Natural","Mrooron,agua,sal,etc","100%",1));
        items.add(new Product("http://frugalbeautiful.com/blog/wp-content/uploads/2012/01/the-smoothiesrecipeforhealthyfoods.jpg","Te de Soya","cafeina,agua,sal,etc","100%",3));
        items.add(new Product("http://cdn-jpg.allyou.com/sites/default/files/image/2015/04/300x300/berry-smoothie-300.jpg","Smothie Paceño","cafeina,agua,sal,etc","100%",3));
        items.add(new Product("http://media3.onsugar.com/files/2013/05/20/835/n/1922729/b69113e292e68915_mango-kiwi-smoothie.preview.jpg","Mate de Manzanilla","cafeina,agua,sal,etc","100%",3));
        items.add(new Product("https://s-media-cache-ak0.pinimg.com/564x/a9/21/6d/a9216d0e6b816aeaf31ce02c05a18db0.jpg","Mantequilla organica","Mrooron,agua,sal,etc","100%",1));
        items.add(new Product("http://cdn6.dibujos.net/images/recetas/big/smoothie-de-pina-natural.jpg","Mascarilla de Coco","cafeina,agua,sal,etc","100%",3));
        items.add(new Product("http://images.clarin.com/2015/03/20/Skfj8KS0Qx_930x525.jpg","Crema Natural","cafeina,agua,sal,etc","100%",3));
        items.add(new Product("http://tucocinafacil.net/wp-content/uploads/2008/10/crema.jpg","MakeUp Natural","cafeina,agua,sal,etc","100%",3));
        items.add(new Product("http://www.lavidalucida.com/wp-content/uploads/2015/04/aplicaci%C3%B3n-de-la-mascarilla-casera.jpg","MakeUp Natural","cafeina,agua,sal,etc","100%",3));
        items.add(new Product("http://www.argentour.com/images/mate_tomar.jpg","Tereré para la gastritis","cafeina,agua,sal,etc","100%",3));
        usarGridView();
    }

    private void usarGridView() {
        gridView = (GridView) rootView.findViewById(R.id.grid);
        adaptador = new CategoryAdapter(getActivity(), items);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                Gson gson = new Gson();
                String jsonEvent = gson.toJson(items.get(position));
                intent.putExtra("EVENT_OBJ",jsonEvent);
                startActivity(intent);
            }
        });
    }


}
