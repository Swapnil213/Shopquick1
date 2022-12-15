package com.example.shopquick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.shopquick.model.AddProdModel;
import com.example.shopquick.viewHolder.RelatedProdsHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {

    Intent intent;
    ImageView pro_Image;
    TextView pro_Name,pro_Cat,pro_Desc,pro_Price;
    AppCompatButton order;
    Toolbar detailsToolbar;

    FirebaseAuth auth;
    String uniqueId;
    String name ;
    String relCategory;
    String checkName;
    RecyclerView rel_Prod_List;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product1);

        auth = FirebaseAuth.getInstance();

        pro_Name=findViewById(R.id.Pro_Name);
        pro_Image=findViewById(R.id.Pro_Image);
        pro_Cat=findViewById(R.id.Pro_Cat);
        pro_Desc=findViewById(R.id.Pro_descrip);
        pro_Price=findViewById(R.id.Pro_Price);

        detailsToolbar=findViewById(R.id.detailsToolbar);
        detailsToolbar.setBackgroundResource(R.drawable.bg_color);

        back=findViewById(R.id.PBack);
        order=findViewById(R.id.orderBtn);

        rel_Prod_List=findViewById(R.id.Related_prod_list);
        rel_Prod_List.setLayoutManager(new LinearLayoutManager(ProductDetails.this,LinearLayoutManager.HORIZONTAL,true));

        intent=getIntent();
        pro_Name.setText(intent.getStringExtra("name").replaceAll("\n"," "));
        pro_Cat.setText(intent.getStringExtra("category"));

        int id = intent.getIntExtra("id",1);
        uniqueId=intent.getStringExtra("uniqueId");

        relCategory = pro_Cat.getText().toString();

        if(id==1)
        {
            uniqueId=uniqueId.replaceAll("\n"," ");
            pro_Price.setText("₹11,453");
            pro_Desc.setText("Richly textured leather brings you premium off-court styling, durability and support.Mid-top design provides padding around the ankle.Air cushioning in the heel provides lightweight comfort with a springy feel.Cupsole construction adds stability and durability.Colour Shown: Black/White/Light Smoke Grey/University RedStyle: DM1200-016Country/Region of Origin: China");
            pro_Image.setImageResource(R.drawable.shoe1);
        }
        else if (id==2)
        {
            uniqueId=uniqueId.replaceAll("\n"," ");
            pro_Price.setText("₹18,395");
            pro_Desc.setText("Foam midsoleRubber outsoleNot intended for use as Personal Protective Equipment (PPE)Colour Shown: White/Black/Dark ConcordStyle: DD0587-141Country/Region of Origin: China");
            pro_Image.setImageResource(R.drawable.shoe2);
        }
        else if (id==3)
        {
            pro_Price.setText(intent.getStringExtra("price"));
            pro_Desc.setText(intent.getStringExtra("Description"));
            String img = intent.getStringExtra("imageName");
            pro_Image.setImageResource(this.getResources().getIdentifier(img,"drawable",this.getPackageName()));
        }
        else
        {
            pro_Name.setText(intent.getStringExtra("AddProdName"));
            pro_Price.setText(intent.getStringExtra("AddProdPrice"));
            pro_Desc.setText(intent.getStringExtra("AddProdDesc"));
            pro_Cat.setText(intent.getStringExtra("AddProdCat"));
            String imgUri = intent.getStringExtra("Image");
            Picasso.get().load(imgUri).into(pro_Image);
        }

        back.bringToFront();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetails.this,HomeActivity.class));
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addingToCartList();
            }
        });

        onStart();

    }

    private void addingToCartList(){
        String saveCurrentDate,saveCurrentTime;

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate =currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String , Object> CartMap = new HashMap<>();
        CartMap.put("uid",uniqueId);
        CartMap.put("name",pro_Name.getText().toString());
        CartMap.put("price",pro_Price.getText().toString());
        CartMap.put("date",saveCurrentDate);
        CartMap.put("time",saveCurrentTime);

        cartListRef.child("User View").child(auth.getCurrentUser().getUid())
                .child("Products").updateChildren(CartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ProductDetails.this,"Added to Cart Successfully!", Toast.LENGTH_SHORT).show();

                    Intent intentCart = new Intent(ProductDetails.this,HomeActivity.class);
                    intentCart.putExtra("CartAdd","yes");
                    startActivity(intentCart);

                }
            }
        });
    }
    protected  void onStart()
    {
        super.onStart();

        final DatabaseReference prodListRef= FirebaseDatabase.getInstance().getReference()
                .child("View All").child("User View").child("Products");

        FirebaseRecyclerOptions<AddProdModel> options=new FirebaseRecyclerOptions.Builder<AddProdModel>()
                .setQuery(prodListRef.orderByChild("Category").startAt(relCategory) ,AddProdModel.class).build();

        FirebaseRecyclerAdapter<AddProdModel, RelatedProdsHolder> adapter=
                new FirebaseRecyclerAdapter<AddProdModel, RelatedProdsHolder>(options){
                    @Override
                    protected void onBindViewHolder(@NonNull RelatedProdsHolder holder, int position, @NonNull AddProdModel model) {
                        name = model.getName();
                        String price= model.getPrice();
                        String imgUri=model.getImage();

                        holder.pro_Name.setText("name");
                        holder.pr_Price.setText("price");
                        Picasso.get().load(imgUri).into(holder.pro_Image);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(ProductDetails.this,ProductDetails.class);

                                intent.putExtra("id",4);
                                intent.putExtra("uniqueId",name);
                                intent.putExtra("AddProdName",name);
                                intent.putExtra("AddProdPrice",price);
                                intent.putExtra("AddProdDesc",model.getDescription());
                                intent.putExtra("AddProdCat",model.getCategory());
                                intent.putExtra("Image",imgUri);

                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public RelatedProdsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.related_prods_adapter, parent,false);
                        RelatedProdsHolder holder = new RelatedProdsHolder(view);
                        return holder;
                    }
                };

        rel_Prod_List.setAdapter(adapter);
        adapter.startListening();
    }
}