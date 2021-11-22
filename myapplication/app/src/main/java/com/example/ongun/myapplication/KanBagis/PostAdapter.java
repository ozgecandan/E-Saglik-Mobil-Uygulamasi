package com.example.ongun.myapplication.KanBagis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ongun.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

//GÖNDERİLERİN TUTULDUĞU
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    Context mContext;
    List<Post> mData ;
    private static final int REQUEST_CALL = 1;



    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row =  LayoutInflater.from(mContext).inflate(R.layout.row_post_item,
                parent, false);
        return new MyViewHolder(row);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sehir.setText(mData.get(position).getSehir());
        holder.hastane.setText(mData.get(position).getHastane());
        holder.kanGrubu.setText(mData.get(position).getKanGrubu());
        holder.telefonNo.setText(mData.get(position).getTelefon());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sehir, hastane, kanGrubu, telefonNo;
        ImageView telephone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sehir = itemView.findViewById(R.id.txtSehir);
            hastane = itemView.findViewById(R.id.txtHastane);
            kanGrubu = itemView.findViewById(R.id.txtKan);
            telephone = itemView.findViewById(R.id.telephoneImage);
            telefonNo = itemView.findViewById(R.id.txtTelefon);



            telephone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = mData.get(getAdapterPosition()).getTelefon();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    mContext.startActivity(intent);

                }
            });

        }


    }




}
