package com.example.ongun.myapplication.Giris;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ongun.myapplication.R;

public class StartActivity extends AppCompatActivity {

    TextView textView;
    Animation frombottom, fromtop;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        textView = findViewById(R.id.textView2);
        img = findViewById(R.id.imageView2);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        textView.setAnimation(frombottom);
        img.setAnimation(fromtop);

        new Thread(new Runnable()
        {
            public void run()
            {
                try {
                    Thread.sleep(5000); //5 saniye
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }).start();

    }


}
