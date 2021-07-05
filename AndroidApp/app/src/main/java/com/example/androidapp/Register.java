package com.example.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mEmail, mPassword, mPasswordConfirm;
    Button mRegisterBtn;
    TextView mLoginPage;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPasswordConfirm = findViewById(R.id.confirmpassword);
        mRegisterBtn = findViewById(R.id.register);
        mLoginPage = findViewById(R.id.toLoginPage);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);




        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassword.setError(null);
                mPasswordConfirm.setError(null);
                mEmail.setError(null);

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String passwordConfirm = mPasswordConfirm.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Please Enter Your Email.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Please Enter A Password.");
                    return;
                }
                if (TextUtils.isEmpty(passwordConfirm)){
                    mPasswordConfirm.setError("Please Confirm Password.");
                    return;
                }
               /* if(password.length() < 6){
                    mPassword.setError("Password must be more than 6 characters.");
                    return;
                }*/
                if(!password.equals(passwordConfirm)){
                    mPasswordConfirm.setError("Two passwords do not match.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //REGISTER USER IN FIREBASE



                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //verify email
                            Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Register.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });



            }
        });


        mLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });
    }



}