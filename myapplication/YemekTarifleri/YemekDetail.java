package com.example.ongun.myapplication.YemekTarifleri;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ongun.myapplication.Giris.HomeActivity;
import com.example.ongun.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class YemekDetail extends AppCompatActivity {

    TextView food_isim,food_calori,food_malzemeler,food_adimlar;
    ImageView food_foto;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;
    CoordinatorLayout coordinatorLayout;
    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;

    String foodId="";

    FirebaseDatabase database;
    DatabaseReference foods;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_detail);

        coordinatorLayout = findViewById(R.id.layout);

        //Firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Tarifler");

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        food_adimlar =findViewById(R.id.foodDescription);
        food_isim =findViewById(R.id.food_name);
        food_calori =findViewById(R.id.kalori);
        food_malzemeler =findViewById(R.id.malzemeler);
        food_foto = findViewById(R.id.img_food);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        appBarLayout = findViewById(R.id.app_bar_layout);
        floatingActionButton = findViewById(R.id.back_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapsingToolbarLayout.setVisibility(View.INVISIBLE);
                floatingActionButton.setVisibility(View.INVISIBLE);
                nestedScrollView.setVisibility(View.INVISIBLE);
                appBarLayout.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.layout,new YemekTarifleriFragment()).commit();
            }
        });

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);

        //Get food Id

        if(getIntent() != null)
            foodId = getIntent().getStringExtra("foodid");
        if(!foodId.isEmpty()){
            getDetailFood(foodId);
        }
    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Yemek yemek = dataSnapshot.getValue(Yemek.class);

                String postImage = getIntent().getExtras().getString("yemekfoto");
                Glide.with(getBaseContext()).load(postImage).into(food_foto);

                String tarifadi = getIntent().getExtras().getString("tarifadi");
                food_isim.setText(tarifadi);

                String kalori = getIntent().getExtras().getString("kalori");
                food_calori.setText(kalori);

                String malzemeler = getIntent().getExtras().getString("malzemeler");
                food_malzemeler.setText(malzemeler);

                String adimlar = getIntent().getExtras().getString("adimlar");
                food_adimlar.setText(adimlar);

                //collapsingToolbarLayout.setTitle(tarifadi);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
