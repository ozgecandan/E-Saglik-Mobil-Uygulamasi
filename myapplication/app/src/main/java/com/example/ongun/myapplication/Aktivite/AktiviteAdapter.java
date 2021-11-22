package com.example.ongun.myapplication.Aktivite;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ongun.myapplication.R;

import java.util.List;

public class AktiviteAdapter extends RecyclerView.Adapter <AktiviteAdapter.MyViewHolder>{


    private Context mContext;
    private List<Aktivite> mData;

    public AktiviteAdapter(Context mContext, List<Aktivite> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cardview_item_aktivite, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.aktivite_adi.setText(mData.get(position).getAktiviteAdi());
        holder.aktivite_img.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aktivite Detay class'ına aktivite adi, ayrint, ve foto gönderiyoz
                Intent intent = new Intent(mContext,AktiviteDetay.class);
                intent.putExtra("AktiviteAdi", mData.get(position).getAktiviteAdi());
                intent.putExtra("AktiviteAyrinti",mData.get(position).getAktiviteAyrinti());
                intent.putExtra("AktiviteFoto",mData.get(position).getThumbnail());

                //start activity
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView aktivite_adi;
        ImageView aktivite_img;
        CardView cardView;



        public MyViewHolder(View itemView){  //aktivite adi ve fotosunu tanıttık
            super(itemView);

            aktivite_adi = itemView.findViewById(R.id.aktivite_adi);
            aktivite_img = itemView.findViewById(R.id.aktivite_foto);
            cardView = itemView.findViewById(R.id.cardview_id);

        }


    }
}
