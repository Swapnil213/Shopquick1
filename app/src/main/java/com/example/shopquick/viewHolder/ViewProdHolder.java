package com.example.shopquick.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopquick.Interfaces.ItemClickListener;
import com.example.shopquick.R;

public class ViewProdHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView AddProdName,AddProdPrice;
    private ItemClickListener itemClickListener;
    public ImageView AddProdImg;

    public ViewProdHolder(@NonNull View itemView) {
        super(itemView);

        AddProdName= itemView.findViewById(R.id.Prod_Name);
        AddProdPrice= itemView.findViewById(R.id.Prod_Price);
        AddProdImg= itemView.findViewById(R.id.Prod_Image);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
}
