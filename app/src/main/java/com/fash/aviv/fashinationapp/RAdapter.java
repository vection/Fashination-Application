package com.fash.aviv.fashinationapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.MyViewHolder> {

    Context mContext;
    private AppCompatActivity main;
    private List<Product> mData;

    public RAdapter(AppCompatActivity m) {
        main = m;
    }

    public RAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.product_item, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.prod_name.setText(mData.get(i).getName());
        //  myViewHolder.prod_image.setImageResource(mData.get(i).getPhotoindex());
      //  myViewHolder.prod_desc.setText(mData.get(i).getDescription());
        myViewHolder.prod_price.setText(mData.get(i).getPrice());
        Picasso.get().load(mData.get(i).getPhotoindex()).into(myViewHolder.prod_image);
        myViewHolder.prod_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(mContext,ProductBuyActivity.class);
                p.putExtra("product_name",mData.get(i).getName());
                p.putExtra("product_price",mData.get(i).getPrice());
                p.putExtra("product_desc",mData.get(i).getDescription());
                p.putExtra("product_image",mData.get(i).getPhotoindex());
                mContext.startActivity(p);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prod_name,prod_price,prod_desc;
        ImageView prod_image;
        Button prod_buy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            prod_name = (TextView) itemView.findViewById(R.id.product_name);
            prod_price = (TextView) itemView.findViewById(R.id.product_price);
            prod_image = (ImageView) itemView.findViewById(R.id.product_image_buy);
            prod_buy = (Button) itemView.findViewById(R.id.buy_btn);
        }
    }
}
