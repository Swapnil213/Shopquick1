package com.example.shopquick.MenuFiles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.shopquick.HomeActivity;
import com.example.shopquick.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddProduct extends BaseActivity {

    ImageView AddProdImg,APBack;
    EditText AddProdName,AddProdPrice,AddProdDesc,AddProdCat;
    TextView ConfirmAddProd;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri setImageUri;
    String finalImageUri;
    Toolbar addToolBar;

    LinearLayout dynamicContent,bottomNavBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dynamicContent=findViewById(R.id.Dynamic_content);
        bottomNavBar=findViewById(R.id.BottomNavBar);

        View wizard = getLayoutInflater().inflate(R.layout.activity_add_product,null);
        dynamicContent.addView(wizard);

        RadioGroup rg=findViewById(R.id.radioG1);
        RadioButton rb=findViewById(R.id.B_Add);

        rb.setBackgroundColor(R.color.Item_selected);
        rb.setTextColor(R.color.use);

        AddProdName=findViewById(R.id.AddProdName);
        AddProdImg=findViewById(R.id.AddProdImg);
        AddProdDesc=findViewById(R.id.AddProdDesc);
        AddProdCat=findViewById(R.id.AddProdCat);
        AddProdPrice=findViewById(R.id.AddProdPrice);
        APBack=findViewById(R.id.APBack);
        ConfirmAddProd=findViewById(R.id.ConfirmAddProd);
        addToolBar=findViewById(R.id.addToolBar);

        addToolBar.setBackgroundColor(R.drawable.bg_color);
        storage=FirebaseStorage.getInstance();

        AddProdImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);

            }
        });

        ConfirmAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=AddProdName.getEditableText().toString();
                String price=AddProdPrice.getEditableText().toString();
                String description=AddProdDesc.getEditableText().toString();
                String categeory=AddProdCat.getEditableText().toString();

                if(TextUtils.isEmpty(name))
                {
                    AddProdName.setError("Please Enter Name of the Product");
                    Toast.makeText(AddProduct.this, "Enter Complete Details !!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(price))
                {
                    AddProdName.setError("Please Enter Price of the Product");
                    Toast.makeText(AddProduct.this, "Enter Complete Details !!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(description))
                {
                    AddProdName.setError("Please Enter Description of the Product");
                    Toast.makeText(AddProduct.this, "Enter Complete Details !!", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(categeory))
                {
                    AddProdName.setError("Please Enter Categeory of the Product");
                    Toast.makeText(AddProduct.this, "Enter Complete Details !!", Toast.LENGTH_SHORT).show();
                }
                else if(AddProdImg==null||AddProdImg.getDrawable().equals(R.drawable.addp))
                {
                    Toast.makeText(AddProduct.this, "Please Choose Product Image!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    storageReference=storage.getReference().child("products").child(name);
                    addingToProdList();
                }
            }
        });
        APBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduct.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
    private void addingToProdList()
    {
        String saveCurrentDate,saveCurrentTime;

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate =currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All");

        final HashMap<String ,Object > prodMap =new HashMap<>();
        prodMap.put("pid",AddProdName.getText().toString());
        prodMap.put("name",AddProdName.getText().toString());
        prodMap.put("price","₹"+AddProdPrice.getText().toString());
        prodMap.put("Description",AddProdDesc.getText().toString());
        prodMap.put("Categeory",AddProdCat.getText().toString());
        prodMap.put("date",saveCurrentDate);
        prodMap.put("time",saveCurrentTime);

        if(setImageUri!=null)
        {
            storageReference.putFile(setImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            finalImageUri=uri.toString();
                            Log.i("Image","Added Successfully");
                            prodMap.put("Image",finalImageUri);

                            prodListRef.child("User View").child("Products")
                                    .child(AddProdName.getText().toString()).updateChildren(prodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(AddProduct.this, "Product added successfully after Verification !!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AddProduct.this,HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }
                    });
                }
            });

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setImageUri=data.getData();
        if(requestCode==1&&resultCode==RESULT_OK&&data!=null)
        {
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),setImageUri);
                AddProdImg.setImageBitmap(bitmap);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}