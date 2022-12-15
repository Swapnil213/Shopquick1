package com.example.shopquick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shopquick.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    EditText regName,regEmail,regPass,regConfirmPass;
    AppCompatButton suBtn;
    LinearLayout signInText;
    String imgUri;
    String EmailPattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        regName=findViewById(R.id.regUn);
        regEmail=findViewById(R.id.regemail);
        regPass=findViewById(R.id.regpass);
        regConfirmPass=findViewById(R.id.regconfirmpass);
        suBtn=findViewById(R.id.subtn);
        suBtn.setBackgroundResource(R.drawable.signin_bg);
        signInText=findViewById(R.id.signInText);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait !!");
        progressDialog.setCancelable(false);

        suBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String name=regName.getEditableText().toString();
                String email=regEmail.getEditableText().toString();
                String pass=regPass.getEditableText().toString();
                String cPass=regConfirmPass.getEditableText().toString();

                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(cPass))
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Enter Complete details", Toast.LENGTH_SHORT).show();
                }
                else if(!email.matches(EmailPattern))
                {
                    progressDialog.dismiss();
                    regEmail.setError("Invalid Email");
                    Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<=6)
                {
                    progressDialog.dismiss();
                    regPass.setError("Invalid password");
                    Toast.makeText(RegisterActivity.this, "Password should be more than 6 characters", Toast.LENGTH_SHORT).show();
                }
                else if(!pass.equals(cPass))
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                DatabaseReference reference = database.getReference().child("users")
                                        .child(auth.getCurrentUser().getUid());
                                StorageReference storageReference= storage.getReference().child("upload")
                                        .child(auth.getCurrentUser().getUid());

                                imgUri = "https://firebasestorage.googleapis.com/v0/b/shopquick-7f8ec.appspot.com/o/profile.jpg?alt=media&token=c01c9c4f-4500-4db0-afca-e89aa442d475";

                                Users user= new Users(auth.getCurrentUser().getUid(),name,email,imgUri);

                                reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            progressDialog.dismiss();
                                            startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                                        }
                                        else
                                        {
                                            progressDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this, "ERROR in creating Account !!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}