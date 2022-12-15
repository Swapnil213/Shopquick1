package com.example.shopquick.MenuFiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import com.example.shopquick.HomeActivity;
import com.example.shopquick.ProductDetails;
import com.example.shopquick.R;
import com.example.shopquick.model.AddProdModel;
import com.example.shopquick.viewHolder.ViewProdHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SearchActivity extends BaseActivity {

    EditText inputText;
    AppCompatButton SearchBtn;
    RecyclerView SearchList;
    String SearchInput;
    ImageView BackHome;
    Toolbar ViewToolbar;

    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dynamicContent = findViewById(R.id.Dynamic_content);
        bottomNavBar = findViewById(R.id.BottomNavBar);

        View wizard = getLayoutInflater().inflate(R.layout.activity_search, null);
        dynamicContent.addView(wizard);

        RadioGroup rg = findViewById(R.id.radioG1);
        RadioButton rb = findViewById(R.id.B_Search);

        rb.setBackgroundColor(R.color.Item_selected);
        rb.setTextColor(R.color.use);

        ViewToolbar=findViewById(R.id.viewToolBar);
        inputText=findViewById(R.id.SET);
        SearchBtn=findViewById(R.id.searchBtn);
        BackHome=findViewById(R.id.backHome);
        SearchList=findViewById(R.id.searchList);

        ViewToolbar.setBackgroundResource(R.drawable.bg_color);
        SearchBtn.setBackgroundResource(R.drawable.signin_bg);

        SearchList.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchInput = inputText.getEditableText().toString();
                onStart();
            }
        });
        BackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference prodListRef= FirebaseDatabase.getInstance().getReference().child("View All")
                .child("User View").child("Products");

        FirebaseRecyclerOptions<AddProdModel> options = new FirebaseRecyclerOptions.Builder<AddProdModel>()
                .setQuery(prodListRef.orderByChild("name").startAt(SearchInput),AddProdModel.class).build();

        FirebaseRecyclerAdapter<AddProdModel, ViewProdHolder> adapter= new FirebaseRecyclerAdapter<AddProdModel, ViewProdHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewProdHolder holder, int position, @NonNull AddProdModel model) {
                String name= model.getName().replaceAll("\n"," ");
                String price = model.getPrice();
                String imgUri = model.getImage();

                holder.AddProdName.setText(name);
                holder.AddProdPrice.setText(price);
                Picasso.get().load(imgUri).into(holder.AddProdImg);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(SearchActivity.this, ProductDetails.class);
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
            public ViewProdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_product_adapter,parent,false);
                ViewProdHolder holder=new ViewProdHolder(view);
                return holder;
            }
        };
        SearchList.setAdapter(adapter);
        adapter.startListening();
    }
}