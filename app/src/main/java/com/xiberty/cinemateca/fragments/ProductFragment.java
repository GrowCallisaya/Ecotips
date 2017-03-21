package com.xiberty.cinemateca.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.xiberty.cinemateca.EventDetailActivity;
import com.xiberty.cinemateca.R;
import com.xiberty.cinemateca.adapters.BillboardAdapter;
import com.xiberty.cinemateca.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProductFragment extends Fragment {
    private GridView gridView;
    private BillboardAdapter adaptador;
    private List<Event> items = new ArrayList<>();
    private ProgressDialog circular;
    private DatabaseReference database;
    private DatabaseReference eventRef;
    private View rootView;
    private String titleEvent;
    private String dateEvent;
    private String contentEvent;
    private String priceEvent;
    private String imageEvent;
    private int c=0;

    public ProductFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_products, container, false);
            circular = ProgressDialog.show(rootView.getContext(), "Cargando", "Aguarde un momento...", true);
            circular.setProgress(R.color.colorPrimaryDark);
        //Firebase
        database = FirebaseDatabase.getInstance().getReference();
        eventRef = database.child("cartelera");

        return rootView;
    }

    @Override
    public void onStart() {
        items.clear();
        super.onStart();
        eventRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot.hasChildren()){
                        Map<String, Object> dataChild = (Map<String, Object>) dataSnapshot.getValue();
                        titleEvent = dataChild.get("title").toString();
                        dateEvent = dataChild.get("date").toString();
                        contentEvent = dataChild.get("content").toString();
                        priceEvent = dataChild.get("precio").toString();
                        DataSnapshot image = dataSnapshot.child("image").child("sizes").child("medium");
                        Map<String, Object> dataImage = (Map<String, Object>) image.getValue();
                        imageEvent = dataImage.get("source_url").toString();

                        c++;
                    }else
                        Toast.makeText(getContext(), "No se encurntran datos en la db", Toast.LENGTH_LONG).show();

                    items.add(new Event(imageEvent,titleEvent,priceEvent,dateEvent,contentEvent));
                    usarGridView();
                    if (c == 1) {
                        circular.dismiss();
                        c = 0;
                    }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void usarGridView() {
        gridView = (GridView) rootView.findViewById(R.id.grid);
        adaptador = new BillboardAdapter(getActivity(), items);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(getContext(), EventDetailActivity.class);
                Gson gson = new Gson();
                String jsonEvent = gson.toJson(items.get(position));
                intent.putExtra("EVENT_OBJ",jsonEvent);
                startActivity(intent);
            }
        });
    }


}
