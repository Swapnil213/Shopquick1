package com.example.shopquick.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopquick.Interfaces.ItemClickListener;
import com.example.shopquick.R;

public class RelatedProdsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView pro_Name,pr_Price;
    public ImageView pro_Image;
    private ItemClickListener itemClickListener;

     public RelatedProdsHolder(@NonNull View itemView) {

         super(itemView);
         pro_Name = itemView.findViewById(R.id.RPName);
         pr_Price = itemView.findViewById(R.id.RPPrice);
         pro_Image = itemView.findViewById(R.id.RPImage);
    }
 public void setItemClickListener(ItemClickListener itemClickListener)
 {
        this.itemClickListener=itemClickListener;
 }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
