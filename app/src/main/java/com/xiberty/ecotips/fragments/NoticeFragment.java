package com.xiberty.ecotips.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.xiberty.ecotips.model.Notice;
import com.xiberty.ecotips.R;
import com.xiberty.ecotips.adapters.NoticeAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class NoticeFragment extends Fragment {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference database;
    private DatabaseReference noticeRef;
    private ProgressDialog circular;
    private View rootView;
    List<Notice> items;
    private String titleNotice;
    private String dateNotice;
    private String contentNotice;
    private String priceNotice;
    private String imageNotice;

    int c = 0;

    public NoticeFragment() {
        items = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_notice, container, false);
        circular = ProgressDialog.show(rootView.getContext(), "Cargando", "Aguarde un momento...", true);
        circular.setProgress(R.color.colorPrimaryDark);
        //Firebase
        database = FirebaseDatabase.getInstance().getReference();
        noticeRef = database.child("noticias");
        return rootView;
    }

    public void usarRecycleView() {
        //Setting the Recycler
        mRecycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        mRecycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecycler.setLayoutManager(mLayoutManager);

        // Crear un nuevo adaptador
        mAdapter = new NoticeAdapter(items,rootView.getContext());
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        items.clear();
        super.onResume();
        noticeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot.hasChildren()) {
                        Map<String, Object> dataChild = (Map<String, Object>) dataSnapshot.getValue();
                        titleNotice = dataChild.get("title").toString();
                        dateNotice = dataChild.get("date").toString();
                        contentNotice = dataChild.get("content").toString();
                        priceNotice = dataChild.get("precio").toString();
                        DataSnapshot image = dataSnapshot.child("image").child("sizes").child("medium");
                        Map<String, Object> dataImage = (Map<String, Object>) image.getValue();
                        imageNotice = dataImage.get("source_url").toString();

                        c++;

                    } else
                        Toast.makeText(getContext(), "No se encurntran datos en la db", Toast.LENGTH_LONG).show();

                    items.add(new Notice(imageNotice, titleNotice, priceNotice, dateNotice, contentNotice));
                    usarRecycleView();
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
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
