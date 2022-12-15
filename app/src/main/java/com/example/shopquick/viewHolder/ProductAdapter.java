package com.example.shopquick.viewHolder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopquick.R;
import com.example.shopquick.constant.Constant;
import com.example.shopquick.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductAdapter(Context context){
        this.context=context;
    }

    public void updateProducts(List<Product> products){
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        TextView ProductName,ProductPrice;
        ImageView ProductImage;

        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.activity_product_adapter,parent,false);
            ProductName=view.findViewById(R.id.ProductName);
            ProductPrice=view.findViewById(R.id.ProductPrice);
            ProductImage=view.findViewById(R.id.ProductImage);
            view.setTag(new ViewHolder(ProductName,ProductPrice,ProductImage));
        }
        else
        {
            ViewHolder viewHolder =(ViewHolder) view.getTag();
            ProductName=viewHolder.productName;
            ProductPrice= viewHolder.productPrice;
            ProductImage= viewHolder.productImage;
        }

        final Product product=getItem(i);
        ProductName.setText(product.getpName());
        ProductPrice.setText(Constant.CURRENCY+String.valueOf(product.getpPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
        ProductImage.setImageResource(context.getResources().getIdentifier(product.getpImgName()
                ,"drawable",context.getPackageName()));
    return view;

    }

    private static class ViewHolder{
        public final TextView productName;
        public final TextView productPrice;
        public final ImageView productImage;

        public ViewHolder(TextView productName,TextView productPrice,ImageView productImage){
            this.productName=productName;
            this.productPrice=productPrice;
            this.productImage=productImage;
        }
    }


}
