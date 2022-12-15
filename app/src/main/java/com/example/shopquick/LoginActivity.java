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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText loginEmail,loginPass;
    AppCompatButton siBtn;
    LinearLayout signUpText;
    String EmailPattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait !!");
        progressDialog.setCancelable(false);

        loginEmail=findViewById(R.id.loginemail);
        loginPass=findViewById(R.id.loginpass);
        siBtn=findViewById(R.id.sibtn);
        siBtn.setBackgroundResource(R.drawable.signin_bg);
        signUpText=findViewById(R.id.signUpText);

        siBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                String email=loginEmail.getEditableText().toString();
                String pass=loginPass.getEditableText().toString();

                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass))
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Enter Complete details", Toast.LENGTH_SHORT).show();
                }
                else if(!email.matches(EmailPattern))
                {
                    progressDialog.dismiss();
                    loginEmail.setError("Invalid Email");
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<=6)
                {
                    progressDialog.dismiss();
                    loginPass.setError("Invalid password");
                    Toast.makeText(LoginActivity.this, "Password should be more than 6 characters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Try Again !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}