package com.example.shopquick;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.shopquick.MenuFiles.BaseActivity;
import com.example.shopquick.MenuFiles.CartActivity;
import com.example.shopquick.MenuFiles.SearchActivity;
import com.example.shopquick.constant.Constant;
import com.example.shopquick.model.Product;
import com.example.shopquick.viewHolder.ProductAdapter;
import com.example.shopquick.viewHolder.SliderAdapter;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.math.BigDecimal;

public class HomeActivity extends BaseActivity {

    Toolbar toolbar;

    SliderView sliderView;

    int[] images={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};
    
    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;

    CardView shoe1,shoe2,shoe3,shoe4,shoe5;

    TextView odd,even,oddPrice,evenPrice,viewAll;

    public static ImageView HomeCart;

    Intent intentCart;
    String getCartUpdate;

    FirebaseStorage storage;
    StorageReference storageReference;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_home);

        dynamicContent=findViewById(R.id.Dynamic_content);
        bottomNavBar=findViewById(R.id.BottomNavBar);

        View wizard = getLayoutInflater().inflate(R.layout.activity_home,null);
        dynamicContent.addView(wizard);

        RadioGroup rg=findViewById(R.id.radioG1);
        RadioButton rb=findViewById(R.id.B_Home);

        rb.setBackgroundColor(R.color.Item_selected);
        rb.setTextColor(R.color.use);

        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        toolbar=findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.drawable.bg_color);

        shoe1=findViewById(R.id.shoes1);
        shoe2=findViewById(R.id.shoes2);
        shoe3=findViewById(R.id.shoes3);
        shoe4=findViewById(R.id.shoes4);
        shoe5=findViewById(R.id.shoes5);

        odd=findViewById(R.id.odd);
        even=findViewById(R.id.even);

        oddPrice=findViewById(R.id.oddprice);
        evenPrice=findViewById(R.id.evenprice);

        viewAll=findViewById(R.id.viewAllProduct);
        HomeCart=findViewById(R.id.home_cart);

        intentCart=getIntent();
        if (intentCart.getStringExtra("CartAdd")!=null&& intentCart.getStringExtra("CartAdd").equals("yes"))
        {
            HomeCart.setImageResource(R.drawable.cnoti);
        }
        else if (intentCart.getStringExtra("CartAdd")!=null&& intentCart.getStringExtra("CartAdd").equals("no"))
        {
            HomeCart.setImageResource(R.drawable.ic_baseline_shopping_cart_24);
        }
        else
        {
            HomeCart.setImageResource(R.drawable.ic_baseline_shopping_cart_24);
        }

        storage=FirebaseStorage.getInstance();

        shoe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", odd.getText().toString());
                i.putExtra("price", oddPrice.getText().toString());
                i.putExtra("UniqueId", odd.getText().toString());
                i.putExtra("category", "Men's Sneakers");
                i.putExtra("Id", 1);
                startActivity(i);
            }
        });
        shoe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", even.getText().toString());
                i.putExtra("price", evenPrice.getText().toString());
                i.putExtra("UniqueId", even.getText().toString());
                i.putExtra("Category", "Men's High Top Sneakers");
                i.putExtra("Id", 2);
                startActivity(i);
            }
        });
        shoe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", odd.getText().toString());
                i.putExtra("price", oddPrice.getText().toString());
                i.putExtra("UniqueId", odd.getText().toString());
                i.putExtra("Category", "Men's Sneakers");
                i.putExtra("Id", 1);
                startActivity(i);
            }
        });
        shoe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", even.getText().toString());
                i.putExtra("price", evenPrice.getText().toString());
                i.putExtra("UniqueId", even.getText().toString());
                i.putExtra("category", "Men's High Top Sneakers");
                i.putExtra("Id", 2);
                startActivity(i);
            }
        });
        shoe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", odd.getText().toString());
                i.putExtra("price", oddPrice.getText().toString());
                i.putExtra("UniqueId", odd.getText().toString());
                i.putExtra("category", "Men's Sneakers");
                i.putExtra("Id", 1);
                startActivity(i);
            }
        });

        ListView lvp = findViewById(R.id.productList);

        ProductAdapter productAdapter=new ProductAdapter(this);
        productAdapter.updateProducts(Constant.PRODUCT_LIST);

        lvp.setAdapter(productAdapter);

        lvp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product= Constant.PRODUCT_LIST.get(i);
                Intent intent = new Intent(HomeActivity.this, ProductDetails.class);
                intent.putExtra("name",product.getpName() );
                intent.putExtra("price", Constant.CURRENCY+String.valueOf(product.getpPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
                intent.putExtra("UniqueId", product.getpName());
                intent.putExtra("category", "Smartphones");
                intent.putExtra("Id", 3);
                intent.putExtra("Description",product.getpDesc());
                intent.putExtra("imageName",product.getpImgName());
                startActivity(intent);
            }
        });


        HomeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(in);
            }
        });
        //addingToProdList();
    }
//    private void addingToProdList()
//    {
//        String saveCurrentDate,saveCurrentTime;
//
//        Calendar calendar= Calendar.getInstance();
//        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd, yyyy");
//        saveCurrentDate =currentDate.format(calendar.getTime());
//
//        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime = currentTime.format(calendar.getTime());
//
//        DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All");
//        String name="Samsung Galaxy M42 5G";
//
//        final HashMap<String ,Object >prodMap =new HashMap<>();
//        prodMap.put("pid",name);
//        prodMap.put("name",name);
//        prodMap.put("price","₹24999");
//        prodMap.put("Description","6.6 inches (16.77 cm) 265 PPI, HD+ Super AMOLED Infinity-U Display | Fingerprint Sensor\n" +
//                "6 GB RAM | 128 GB ROM | Expandable Upto 1 TB\n" +
//                "48 MP + 8 MP + 5 MP + 5 MP Quad Primary Cameras | 20 MP Front Camera\n" +
//                "Octa core (2.2 GHz, Dual Core + 1.8 GHz, Hexa Core) Qualcomm Snapdragon 750G Processor\n" +
//                "5000 mAh Lithium-ion Battery\n" +
//                "Android v11 OS\n" +
//                "1 Year Manufacturer Warranty");
//        prodMap.put("date",saveCurrentDate);
//        prodMap.put("time",saveCurrentTime);
//
//        prodListRef.child("User View").child("Products")
//        .child(name).updateChildren(prodMap)
//        .addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Log.i("Task","successfull");
//                }
//            }
//        });
//    }
}









