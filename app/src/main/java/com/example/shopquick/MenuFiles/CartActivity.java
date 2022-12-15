package com.example.shopquick.MenuFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shopquick.R;

public class CartActivity extends BaseActivity {

    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dynamicContent=findViewById(R.id.Dynamic_content);
        bottomNavBar=findViewById(R.id.BottomNavBar);

        View wizard = getLayoutInflater().inflate(R.layout.activity_cart,null);
        dynamicContent.addView(wizard);

        RadioGroup rg=findViewById(R.id.radioG1);
        RadioButton rb=findViewById(R.id.B_Cart);

        rb.setBackgroundColor(R.color.Item_selected);
        rb.setTextColor(R.color.use);
    }
}