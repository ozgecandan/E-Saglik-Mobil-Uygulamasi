package com.example.ongun.myapplication.AboutApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import com.example.ongun.myapplication.Giris.HomeActivity;
import com.example.ongun.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btn_next;
    int position=0;
    Button btn_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);



        tabIndicator = findViewById(R.id.tab_indicator);
        btn_next = findViewById(R.id.btn_next);
        btn_getStarted = findViewById(R.id.btn_getStarted);



        //fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Kan Bağışı Bölümü", "Telefon numaranıza gelen doğrulama kodu ile bölüme giriş yapabilir, ihtiyaç durumunda " +
                "hastane, telelfon ve kan bilgileri ile ilan oluşturabilirsiniz. Oluşturulan ilanlar aracılığıyla ihtiyaç sahiplerine telefon araması ile ulaşabilirsiniz.", R.drawable.kanbagisi));
        mList.add(new ScreenItem("Sağlıklı Tarifler Bölümü", "Sağlıklı tarifler deyince herkesin aklına sıkıcı aynı tip beslenme tipi gelirken, siz hergün" +
                "yeni tarifler ve kalori bilgilerini öğrenebilirsiniz.", R.drawable.sagliklitarifler));
        mList.add(new ScreenItem("Diyetisyen ile İletişim", "Diyetisyeninizin ofisine gitmeyi beklemeden aklınızdaki soruları mesaj yoluyla sorabilir, kararlarınızı " +
                "diyetisyenininizle kolayca paylaşabilirsiniz.", R.drawable.diyetisyenabout));
        mList.add(new ScreenItem("Aktivite", "Resimli anlatım yardımıyla her hareketi ayrılan süre içinde yapabilir, güne daha zinde başlayabilir ya da günün yorgunluğunu " +
                "atabilirisiniz.", R.drawable.aktivite));

        //setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);


        //setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = screenPager.getCurrentItem(); //SIRADAKİ SAYFA
                if (position<mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size()-1){
                    //TODO: kullanmaya başla butonunu göster. indicator ve next butonunu yok et
                    loadLastScreen();
                }
            }
        });

        //tabIndicatoru kaydırdıktan sonra getstarted butonu gelsin
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    //kullanmaya başla butonunu göster. indicator ve next butonunu yok et
    private void loadLastScreen() {
        btn_next.setVisibility(View.INVISIBLE);
        btn_getStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

    }
}
