package com.example.ongun.myapplication.Aktivite;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.ongun.myapplication.Giris.HomeActivity;
import com.example.ongun.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AktiviteSayfasi extends AppCompatActivity {

    List<Aktivite> listAktivite;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivite_sayfasi);
        floatingActionButton = findViewById(R.id.image_view_home);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });


        listAktivite = new ArrayList<>();
        listAktivite.add(new Aktivite("Nefes Egzersizi","Pasif bir asana olarak görülen palmiye duruşu; kollar, göğüs, karın, omurga ve bacakları esnetirken boy uzamasını destekler. \n" +
                "Dengeli ve düz bir alt ve üst vücut simetrisi oluşturulmasına yardımcı olur. Bu pozisyonda derin nefes alın.", R.drawable.bir));

        listAktivite.add(new Aktivite("Çocuk Pozu","Sinir sistemi üzerinde yatıştırıcı etkiye sahip pozlardan ilki Çocuk pozu. \n" +
                "Bu pozu yapmak için eller ve dizler üzerinde 4 ayak pozuna gelin. Ardından kalçalarınızı topuklarınızın üzerine doğru göndererek geriye çekilin, alnınız yere gelsin. \n" +
                "Kalçalarınız veya alnınız yere gelmiyorsa altlarına birer yastık yerleştirerek destekleyebilirsiniz. \n" +
                "Elleriniz önde uzun ve canlı olsun, dirsekleriniz yere değmesin. " +
                "Birkaç nefes bu şekilde kaldıktan sonra kollarınızı kalçalarınızın arkasına yerleştirerek yine birkaç nefes daha pasif bir şekilde kalabilirsiniz.", R.drawable.iki));

        listAktivite.add(new Aktivite("Aşağı Bakan Köpek Pozu","Çocuk pozundan tekrar eller ve dizler üzerine gelin. \n" +
                "Tüm el parmaklarınızı ve parmak köklerinizi iyice matınıza yayın. \n" +
                "Ardından dizlerinizi yerden sıyırın ve kalçanızı yukarıya doğru gönderin. \n" +
                "Bedeniniz yandan bakıldığında sanki ters V harfi gibi görünüyor olacak. \n" +
                "Başınızı ve boynunuzu mümkün olduğunca serbest bırakmaya çalışın. " +
                "Kalçalarınızı ise gökyüzüne doğru yönlendirmeye devam edin. Birkaç nefes kalın." ,R.drawable.uc));

        listAktivite.add(new Aktivite("Ayakta Öne Eğilme","Matınızın önüne gelin ve ayaklarınızın arasını kalça mesafesi kadar açın. " +
                "Kollarınızı gökyüzüne doğru uzatın ve yukarı doğru uzarken ayaklarınızla da yere köklenin. \n" +
                "Nefes alın ve nefes verirken kalçanızdan bacaklarınızın üzerine doğru katlanın. Dizlerinizi hafifçe bükebilirsiniz. " +
                "Böylelikle omurganızı bacaklarınızın üzerine daha rahat bir şekilde bırakabilirsiniz. \n" +
                "Başınız ve boynunuz serbest kalsın. Rahat nefes alabildiğiniz bir alanda, birkaç nefes kalın. \n" +
                "Her nefes verişte omurganızı bacaklarınızın üzerine doğru biraz daha ağırlaştırın." ,R.drawable.dort));

        listAktivite.add(new Aktivite("Bağlı Açı Pozu", "Rahat bir oturuşa gelin, omurganızı uzun tutun. \n" +
                "Ayak tabanlarını birbiriyle buluşturun ve ayaklarınızı kasıklarınıza doğru çekin. \n" +
                "Dizlerinizde herhangi noktasal bir batma olmasın. \n" +
                "Dizleriniz iki yandan açıldığında çok yukarıda kalıyorsa, iki yandan altlarına birer yastık/minder yerleştirebilirsiniz. \n" +
                "Ellerinizle ayaklarınızdan tutun. Nefes alırken yukarıya doğru uzayın nefes verirken bacaklarınızın üzerine doğru eğilin. \n" +
                "Kamburlaşmak yerine omurganızı hep uzun kullanın. Rahat nefes alabileceğiniz bir alanda birkaç nefes kalın.",R.drawable.bes));

        listAktivite.add(new Aktivite("Bacaklar Duvarda Pozu","Bu pozu ister matınızın üzerinde, isterseniz de yatağınızın bir kenarında duvar varsa yatağınızda yapabilirsiniz. \n" +
                "Bacaklarınızı desteklemesi için bir duvara yaklaşarak uzatın. Kollarınızı iki yanda rahat bir şekilde yerleştirin ve omuzlarınızı, kollarınızı gevşetin. \n" +
                "Dikkatinizi nefes alış verişlerinize getirin. Dilerseniz 5-10 dakika arası kalabilirsiniz. " +
                "Kalçalarınızın altına bir bolster yerleştirerek kalçalarınızı yükseltebilirsiniz. \n" +
                "Pozdan çıkmak için çok yavaş ve nazik hareketler yapın." ,R.drawable.alti));

        listAktivite.add(new Aktivite("Karın Bölgesi Çevrilmesi", "Sırtüstü uzanın. Dizlerinizi iyice karnınıza doğru çekin ve sarılın. \n" +
                "Önce bir sola bir sağa doğru hafif hafif sallanarak sırtınıza masaj yapabilirsiniz. \n" +
                "Dizlerinizi sağ tarafa doğru düşürün, kollarınızı iki yandan açın. \n" +
                "Başınızı dizlerinizden ters yöne doğru çevirin. Sağ elinizle dizlerinizin üzerine nazikçe bir baskı uygulayarak çevrilmenizi destekleyebilirsiniz. \n" +
                "Nefes alıp vermeyi unutmayın ve gevşemeye çalışın. Ardından dizlerinizi diğer tarafa doğru yerleştirerek pozu diğer tarafta uygulayın. " +
                "Nefeslerinizi unutmayın.",R.drawable.yedi));

        RecyclerView recyclerView = findViewById(R.id.recyclerview_id);
        AktiviteAdapter aktiviteAdapter = new AktiviteAdapter(this,listAktivite);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(aktiviteAdapter);





    }
}
