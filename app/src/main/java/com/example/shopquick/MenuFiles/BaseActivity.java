package com.example.shopquick.MenuFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.shopquick.HomeActivity;
import com.example.shopquick.R;

public class BaseActivity extends AppCompatActivity {

    RadioGroup G1;
    RadioButton Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        RadioGroup G1=findViewById(R.id.radioG1);
        Home=findViewById(R.id.B_Home);

        G1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Intent in;

                switch(i)
                {
                    case R.id.B_Home:
                        in = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.B_Add:
                        in = new Intent(getBaseContext(), AddProduct.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.B_Search:
                        in = new Intent(getBaseContext(), SearchActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.B_Cart:
                        in = new Intent(getBaseContext(), CartActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.B_pro:
                        in = new Intent(getBaseContext(), ProfileActivity.class);
                        startActivity(in);
                        overridePendingTransition(0,0);
                        break;

                    default:
                        break;
                }
            }
        });
    }
}