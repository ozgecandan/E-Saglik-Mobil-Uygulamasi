package com.example.ongun.myapplication.KanBagis;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ongun.myapplication.Giris.HomeActivity;
import com.example.ongun.myapplication.R;

public class KanBagis extends AppCompatActivity  implements PostFragment.OnFragmentInteractionListener
{

    Button button, button2;
    ImageView imageView;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kan_bagis);
        button = findViewById(R.id.button3);
        button2 = findViewById(R.id.button5);
        imageView = findViewById(R.id.imageView);
        floatingActionButton = findViewById(R.id.image_view_home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PostKan.class);
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                floatingActionButton.setVisibility(View.INVISIBLE);
               getSupportFragmentManager().beginTransaction().replace(R.id.containerKanBagıs,new PostFragment()).commit();
            }
        });

    }


    @Override
    public void onFragmentInteraction(Uri uri) {}

}



