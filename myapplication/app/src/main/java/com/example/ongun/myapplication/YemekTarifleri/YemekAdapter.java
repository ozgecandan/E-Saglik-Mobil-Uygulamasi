package com.example.ongun.myapplication.YemekTarifleri;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ongun.myapplication.R;
import java.util.List;
import com.example.ongun.myapplication.R;
public class YemekAdapter extends RecyclerView.Adapter<YemekAdapter.MyViewHolder>{
    Context context;
    List<Yemek> mData ;

    public YemekAdapter(Context context, List<Yemek> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row =  LayoutInflater.from(context).inflate(R.layout.row_yemek_item,
                parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)  {
        holder.tarifAdi.setText(mData.get(position).getTarifler());
        Glide.with(context).load(mData.get(position).getPhotourl()).into(holder.food);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tarifAdi;
        ImageView food;

        public MyViewHolder(View itemView) {
            super(itemView);
            tarifAdi = itemView.findViewById(R.id.txtTarifAdi);
            food = itemView.findViewById(R.id.food);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent foodDetailActivity = new Intent(context,YemekDetail.class);
                    int position = getAdapterPosition();
                    foodDetailActivity.putExtra("tarifadi",mData.get(position).getTarifler());
                    foodDetailActivity.putExtra("yemekfoto",mData.get(position).getPhotourl());
                    foodDetailActivity.putExtra("kalori",mData.get(position).getKalori());
                    foodDetailActivity.putExtra("malzemeler",mData.get(position).getMalzemeler());
                    foodDetailActivity.putExtra("adimlar",mData.get(position).getAdımlar());
                    foodDetailActivity.putExtra("foodid", mData.get(position).getFoodid());
                    context.startActivity(foodDetailActivity);
                }
            });

        }


        }
    }


