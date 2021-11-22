package com.example.ongun.myapplication.Aktivite;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ongun.myapplication.Giris.HomeActivity;
import com.example.ongun.myapplication.R;

public class AktiviteDetay extends AppCompatActivity {


    private TextView aktiviteadi, aktiviteayrinti,sayacTxt;
    private ImageView img;
    private Button sayacBtn;
    FloatingActionButton floatingActionButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMiliSecond = 60000; //60 saniye = 1 saniye 1000 ms
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivite_detay);

        aktiviteadi = findViewById(R.id.aktiviteadi);
        aktiviteayrinti = findViewById(R.id.aktiviteayrinti);
        img = findViewById(R.id.aktivitefoto);
        floatingActionButton = findViewById(R.id.back_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AktiviteSayfasi.class);
                startActivity(i);
            }
        });

        sayacTxt = findViewById(R.id.sayacTxt);
        sayacBtn = findViewById(R.id.sayacBtn);

        sayacBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        //dataya ulaşacağız
        Intent intent = getIntent();
        String adi = intent.getExtras().getString("AktiviteAdi");
        String detay = intent.getExtras().getString("AktiviteAyrinti");
        int image = intent.getExtras().getInt("AktiviteFoto");

        //değer vereceğim
        aktiviteadi.setText(adi);
        aktiviteayrinti.setText(detay);
        img.setImageResource(image);

    }

    private void startStop() {
        if(timerRunning){
            stopTimer();
        }
        else{
            startTimer();
        }
    }


    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMiliSecond, 1000) {//parametrenin ilki geri sayıma başlanacağı zaman diğeri
                                                                                        //diğer parametre ise zaman aralığı (ms) cinsinden.
            @Override
            public void onTick(long l) { //bu metotda belirttiğiniz aralıktaki her azalma sonunda otomatik olarak çağıralacaktır.
                // Bu kod bloğunda her çağrıldığında uptadeTimer çağrılıyor
                timeLeftInMiliSecond = l;
                uptadeTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        sayacBtn.setText("Durdur");
        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        sayacBtn.setText("Başla");
        timerRunning = false;
    }//100 ms = 1 saniye

    private void uptadeTimer() { // onTick her çağrıldığında TextView elemanı içerisine kalan zamanı yazdırıyor.
        int minutes = (int) timeLeftInMiliSecond / 60000; //dakika
        int seconds = (int) timeLeftInMiliSecond %60000 / 1000; //saniye

        String timeLeftText;

        timeLeftText = " "+minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";  //kalan süre
        timeLeftText += seconds;

        sayacTxt.setText(timeLeftText);
    }

}
