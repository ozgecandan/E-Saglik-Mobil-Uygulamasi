package com.example.ongun.myapplication.YemekTarifleri;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ongun.myapplication.Giris.HomeActivity;
import com.example.ongun.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class YemekTarifleriFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    RecyclerView yemekRecyclerView;
    YemekAdapter yemekAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference ;
    List<Yemek> yemekList;
    FloatingActionButton floatingActionButton;


    public YemekTarifleriFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static YemekTarifleriFragment newInstance(String param1, String param2) {
        YemekTarifleriFragment fragment = new YemekTarifleriFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_yemek_tarifleri, container, false);
        yemekRecyclerView  = fragmentView.findViewById(R.id.recyclerViewFood);
        yemekRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yemekRecyclerView.setHasFixedSize(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Tarifler");
        floatingActionButton = fragmentView.findViewById(R.id.image_view_home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
            }
        });




        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        //database'den tarifleri alıcaz
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                yemekList = new ArrayList<>();
                for(DataSnapshot postsnap: dataSnapshot.getChildren()){
                    Yemek yemek = postsnap.getValue(Yemek.class);
                    yemekList.add(yemek);

                }

                yemekAdapter = new YemekAdapter(getActivity(),yemekList);
                yemekRecyclerView.setAdapter(yemekAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
