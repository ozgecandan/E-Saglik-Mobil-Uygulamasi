package com.example.ongun.myapplication.Giris;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ongun.myapplication.AboutApp.IntroActivity;
import com.example.ongun.myapplication.Aktivite.AktiviteSayfasi;
import com.example.ongun.myapplication.Diyetisyen.MainActivity;
import com.example.ongun.myapplication.KanBagis.KanBagis;
import com.example.ongun.myapplication.R;
import com.example.ongun.myapplication.YemekTarifleri.YemekTarifleriFragment;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements YemekTarifleriFragment.OnFragmentInteractionListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    FirebaseAuth firebaseAuth;
    CardView saglikliCard, diyetisyenCard, kanCard, aktiviteCard;
    ImageView logo;
    ImageView imageView_about;
    FloatingActionButton exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView_about = findViewById(R.id.imageView_about);


        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        saglikliCard = findViewById(R.id.saglikliCard);
        diyetisyenCard = findViewById(R.id.diyetisyenCard);
        kanCard = findViewById(R.id.kanCard);
        aktiviteCard = findViewById(R.id.aktiviteCard);

        logo = findViewById(R.id.logo);

        firebaseAuth = FirebaseAuth.getInstance();

        imageView_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, IntroActivity.class);
                startActivity(intent);

            }
        });

        kanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KanBagis.class);
                startActivity(intent);

            }
        });

        saglikliCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer,new YemekTarifleriFragment()).commit();
                saglikliCard.setVisibility(View.INVISIBLE);
                kanCard.setVisibility(View.INVISIBLE);
                diyetisyenCard.setVisibility(View.INVISIBLE);
                aktiviteCard.setVisibility(View.INVISIBLE);
                logo.setVisibility(View.INVISIBLE);


            }
        });

        diyetisyenCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        aktiviteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AktiviteSayfasi.class);
                startActivity(intent);
            }
        });



    }





    public void onFragmentInteraction(Uri uri) {

    }


}
