package com.example.ongun.myapplication.KanBagis;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ongun.myapplication.Diyetisyen.User;
import com.example.ongun.myapplication.Giris.HomeActivity;
import com.example.ongun.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import com.bumptech.glide.Glide;

//POST YAYINLAMA SAYFASI

public class PostKan extends AppCompatActivity  implements PostFragment.OnFragmentInteractionListener{
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    FloatingActionButton floatingActionButton;


    Spinner spinSehir, spinHastane, spinKan;
    ArrayAdapter arrayAdapterSehir,arrayAdapterHastane, arrayAdapterKan;
    ImageView popupUserImage, image;
    Button btnYayın;
   // Dialog popAddPost;
    TextView txt1,txt2,txt3,txt4,txt5;
    EditText telefonNo;
    List<String> sehirList = new ArrayList<>();
    String secilenSehir;

    String verification_code;

    AlertDialog.Builder mBuilder;
    AlertDialog dialog;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_kan);

        spinSehir = findViewById(R.id.spinSehir);
        spinHastane = findViewById(R.id.spinHastane);
        spinKan = findViewById(R.id.spinKan);

        popupUserImage = findViewById(R.id.imageView2);
        btnYayın = findViewById(R.id.btnYayınla);

        txt1 = findViewById(R.id.textView1);
        txt3 = findViewById(R.id.textView3);
        txt4 = findViewById(R.id.textView4);
        txt5 = findViewById(R.id.textView5);
        telefonNo = findViewById(R.id.editTelefon);
        image = findViewById(R.id.imageView3);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        floatingActionButton = findViewById(R.id.back_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), KanBagis.class);
                startActivity(intent);
            }
        });



        ///TELEFON ONAY KODU GÖNDERİYOR
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            //KOD GÖNDERİLİNCE
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verification_code = s;
                showMessage("Kod gönderildi!");
            }
        };


        /*
        String[] androidStrings = getResources().getStringArray(R.array.android);
        if (Arrays.asList(androidStrings).contains("software") {// found a match to "software"}*/

        //SEHİR-HASTANE SPINNER
       arrayAdapterSehir = ArrayAdapter.createFromResource(this, R.array.Sehirler,
                R.layout.spinner_item);//sonuncusu nerede görüneceği hazır aldık
        arrayAdapterSehir.setDropDownViewResource(R.layout.spinner_item);
        spinSehir.setAdapter(arrayAdapterSehir); //spinner ile adapter bağlandı
        spinSehir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                 //secilenSehir = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(),secilenSehir,Toast.LENGTH_SHORT).show();;
                int pos;
                pos = spinSehir.getSelectedItemPosition(); //seçilen şehrin pozisyonunu aldık
                int iden=parent.getId();

                if(iden==R.id.spinSehir)
                {

                    switch (pos)  //positionlara göre
                    {
                        case 0: arrayAdapterHastane= ArrayAdapter.createFromResource(
                               getApplicationContext(), R.array.SecilenYok, R.layout.spinner_item);
                            break;
                        case 1: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AdanaHastane, R.layout.spinner_item);
                            break;
                        case 2: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AdiyamanHastane, R.layout.spinner_item);
                            break;
                        case 3: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AfyonHastane, R.layout.spinner_item);
                            break;
                        case 4: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AgriHastane, R.layout.spinner_item);
                            break;
                        case 5: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AmasyaHastane, R.layout.spinner_item);
                            break;
                        case 6: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AnkaraHastane, R.layout.spinner_item);
                            break;
                        case 7: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AntalyaHastane, R.layout.spinner_item);
                            break;
                        case 8: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.ArtvinHastane, R.layout.spinner_item);
                            break;
                        case 9: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AydinHastane, R.layout.spinner_item);
                            break;
                        case 10: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BalıkesirHastane, R.layout.spinner_item);
                            break;
                        case 11: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BilecikHastane, R.layout.spinner_item);
                            break;
                        case 12: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BingölHastane, R.layout.spinner_item);
                            break;
                        case 13: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BitlisHastane, R.layout.spinner_item);
                            break;
                        case 14: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BoluHastane, R.layout.spinner_item);
                            break;
                        case 15: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BurdurHastane, R.layout.spinner_item);
                            break;
                        case 16: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BursaHastane, R.layout.spinner_item);
                            break;
                        case 17: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.CanakkaleHastane, R.layout.spinner_item);
                            break;
                        case 18: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.CankiriHastane, R.layout.spinner_item);
                            break;
                        case 19: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.CorumHastane, R.layout.spinner_item);
                            break;
                        case 20: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.DenizliHastane, R.layout.spinner_item);
                            break;
                        case 21: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.DiyarbakırHastane, R.layout.spinner_item);
                            break;
                        case 22: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.EdirneHastane, R.layout.spinner_item);
                            break;
                        case 23: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.ElazigHastane, R.layout.spinner_item);
                            break;
                        case 24: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.ErzincanHastane, R.layout.spinner_item);
                            break;
                        case 25: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.ErzurumHastane, R.layout.spinner_item);
                            break;
                        case 26: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.EskisehirHastane, R.layout.spinner_item);
                            break;
                        case 27: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.GaziantepHastane, R.layout.spinner_item);
                            break;
                        case 28: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.GiresunHastane, R.layout.spinner_item);
                            break;
                        case 29: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.GumushaneHastane, R.layout.spinner_item);
                            break;
                        case 30: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.HakkariHastane, R.layout.spinner_item);
                            break;
                        case 31: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.HatayHastane, R.layout.spinner_item);
                            break;
                        case 32: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.IspartaHastane, R.layout.spinner_item);
                            break;
                        case 33: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.IstanbulHastane, R.layout.spinner_item);
                            break;
                        case 34: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.IzmirHastane, R.layout.spinner_item);
                            break;
                        case 35: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KarsHastane, R.layout.spinner_item);
                            break;
                        case 36: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KastamonuHastane, R.layout.spinner_item);
                            break;
                        case 37: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KayseriHastane, R.layout.spinner_item);
                            break;
                        case 38: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KirklareliHastane, R.layout.spinner_item);
                            break;
                        case 39: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KirsehirHastane, R.layout.spinner_item);
                            break;
                        case 40: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KocaeliHastane, R.layout.spinner_item);
                            break;
                        case 41: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KonyaHastane, R.layout.spinner_item);
                            break;
                        case 42: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KutahyaHastane, R.layout.spinner_item);
                            break;
                        case 43: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.MalatyaHastane, R.layout.spinner_item);
                            break;
                        case 44: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.ManisaHastane, R.layout.spinner_item);
                            break;
                        case 45: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KahramanmarasHastane, R.layout.spinner_item);
                            break;
                        case 46: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.MardinHastane, R.layout.spinner_item);
                            break;
                        case 47: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.MuglaHastane, R.layout.spinner_item);
                            break;
                        case 48: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.MusHastane, R.layout.spinner_item);
                            break;
                        case 49: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.NevsehirHastane, R.layout.spinner_item);
                            break;
                        case 50: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.NigdeHastane, R.layout.spinner_item);
                            break;
                        case 51: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.OrduHastane, R.layout.spinner_item);
                            break;
                        case 52: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.RizeHastane, R.layout.spinner_item);
                            break;
                        case 53: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.SakaryaHastane, R.layout.spinner_item);
                            break;
                        case 54: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.SamsunHastane, R.layout.spinner_item);
                            break;
                        case 55: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.SiirtHastane, R.layout.spinner_item);
                            break;
                        case 56: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.SinopHastane, R.layout.spinner_item);
                            break;
                        case 57: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.SivasHastane, R.layout.spinner_item);
                            break;
                        case 58: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.TekirdagHastane, R.layout.spinner_item);
                            break;
                        case 59: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.TokatHastane, R.layout.spinner_item);
                            break;
                        case 60: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.TrabzonHastane, R.layout.spinner_item);
                            break;
                        case 61: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.TunceliHastane, R.layout.spinner_item);
                            break;
                        case 62: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.SanliurfaHastane, R.layout.spinner_item);
                            break;
                        case 63: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.UsakHastane, R.layout.spinner_item);
                            break;
                        case 64: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.VanHastane, R.layout.spinner_item);
                            break;
                        case 65: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.YozgatHastane, R.layout.spinner_item);
                            break;
                        case 66: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.ZonguldakHastane, R.layout.spinner_item);
                            break;
                        case 67: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.AksarayHastane, R.layout.spinner_item);
                            break;
                        case 68: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BayburtHastane, R.layout.spinner_item);
                            break;
                        case 69: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KaramanHastane, R.layout.spinner_item);
                            break;
                        case 70: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KirikkaleHastane, R.layout.spinner_item);
                            break;
                        case 71: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BatmanHastane, R.layout.spinner_item);
                            break;
                        case 72: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.SirnakHastane, R.layout.spinner_item);
                            break;
                        case 73: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.BartinHastane, R.layout.spinner_item);
                            break;
                        case 74: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.ArdahanHastane, R.layout.spinner_item);
                            break;
                        case 75: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.IgdirHastane, R.layout.spinner_item);
                            break;
                        case 76: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.YalovaHastane, R.layout.spinner_item);
                            break;
                        case 77: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KarabukHastane, R.layout.spinner_item);
                            break;
                        case 78: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.KilisHastane, R.layout.spinner_item);
                            break;
                        case 79: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.OsmaniyeHastane, R.layout.spinner_item);
                            break;
                        case 80: arrayAdapterHastane= ArrayAdapter.createFromResource(
                                getApplicationContext(), R.array.DuzceHastane, R.layout.spinner_item);
                            break;

                        default:
                            break;
                    }

                    arrayAdapterHastane.setDropDownViewResource(R.layout.spinner_item);
                    spinHastane.setAdapter(arrayAdapterHastane); //spinner ile adapter bağlandı

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }});


        //KAN GRUBU SPINNER
        arrayAdapterKan = ArrayAdapter.createFromResource(this, R.array.KanGrubu,
               R.layout.spinner_item);//sonuncusu nerede görüneceği hazır aldık
        arrayAdapterKan.setDropDownViewResource(R.layout.spinner_item);
        spinKan.setAdapter(arrayAdapterKan); //spinner ile adapter bağlandı
        spinKan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
               //String secilenKan = parent.getItemAtPosition(position).toString();
               // Toast.makeText(parent.getContext(),secilenKan,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }});
    }

    public void send_sms(View view){ ///ONAY KODU GÖNDERME
        String number1 = telefonNo.getText().toString();
        String number2 = "+9";
        String number = number2+number1; //KODU GÖNDER
        String telefonNumara = telefonNo.getText().toString();
        if(spinSehir.getSelectedItemPosition()==0||  //ALANLAR BOŞSA UYARI VER!
                spinHastane.getSelectedItemPosition() == 0  ||
                spinKan.getSelectedItemPosition() == 0 ||
                telefonNumara.isEmpty() ||
                telefonNumara.length() < 10){
            showMessage("Lütfen alanları doğru bir şekilde doldurunuz");
        }
        else//ALANLAR DOLUYSA KOD İÇİN ALER DİALOG AÇ
        {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,this,mCallback);
            mBuilder = new AlertDialog.Builder(PostKan.this);
            View mView = getLayoutInflater().inflate(R.layout.alert_telephone_dialog,null);
            final EditText code = mView.findViewById(R.id.code);

            Button onay = mView.findViewById(R.id.onayla);
            onay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) { //ALERT DİALOG DAKİ ONAYLA BUTONUNA BASINCA OLACAKLAR:
                    String input_code = code.getText().toString();

                    if(input_code.isEmpty()){
                        showMessage("Lütfen kodu giriniz!");
                    }
                    else {
                        verifyPhoneNumber(verification_code, input_code);
                    }


                    //create post object ve firebase database'ine ekle
                    //create post object.
                    Post post = new Post(spinSehir.getSelectedItem().toString(),
                            spinHastane.getSelectedItem().toString(),
                            spinKan.getSelectedItem().toString(),
                            telefonNo.getText().toString(),
                            currentUser.getUid());
                    //add firebase database
                    addPost(post);
                }
            });
            mBuilder.setView(mView);
            dialog = mBuilder.create();
            dialog.show();

        }


    }




    void addPostWithPhone(PhoneAuthCredential credential){ //KOD DOĞRUYSA GÖNDERİLERİN OLDUĞU SAYFAYA GİDER
        currentUser.updatePhoneNumber(credential).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    showMessage("Doğru Kod!");
                    spinSehir.setVisibility(View.INVISIBLE);
                    btnYayın.setVisibility(View.INVISIBLE);
                    spinHastane.setVisibility(View.INVISIBLE);
                    spinKan.setVisibility(View.INVISIBLE);
                    txt1.setVisibility(View.INVISIBLE);
                    txt3.setVisibility(View.INVISIBLE);
                    txt4.setVisibility(View.INVISIBLE);
                    txt5.setVisibility(View.INVISIBLE);
                    btnYayın.setVisibility(View.INVISIBLE);
                    telefonNo.setVisibility(View.INVISIBLE);
                    image.setVisibility(View.INVISIBLE);
                    floatingActionButton.setVisibility(View.INVISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new PostFragment()).commit();
                }
                else{
                    showMessage("Yanlış kod!");
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                    } else {
                        Toast.makeText(getApplicationContext(),"signInWithCredential:failure"+task.getException(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }




    public void verifyPhoneNumber(String verifyCode, String input_code) { //KODU DOĞRULA
      PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode, input_code);
       addPostWithPhone(credential);

    }

    private void addPost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();

        //get post unique id and update post key
        String key = myRef.getKey();
        post.setPostKey(key);

        //add post data to firebase
        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
              //  showMessage("Gönderi Oluşturuldu!");
            }
        });

    } //POST EKLİYOR

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG ).show();
    } //toast için kısa yol
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
