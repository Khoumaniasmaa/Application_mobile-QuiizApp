package com.khoumani.quizapp_c;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText etMail, etPassword, etPassword1;
    Button bRegister;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etMail=(EditText) findViewById(R.id.etMail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        etPassword1=(EditText)findViewById(R.id.etPassword1);
        bRegister=(Button)findViewById(R.id.bRegister);
            mAuth = FirebaseAuth.getInstance();
        bRegister.setOnClickListener(view ->{
                createUser();
            });
        }
        private void createUser(){
            String email = etMail.getText().toString();
            String password = etPassword.getText().toString();
            String confirm = etPassword1.getText().toString();

            if(TextUtils.isEmpty(email)) {
                etMail.setError("Name cannot be empty");
                etMail.requestFocus();
            }else if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password cannot be empty");
                etPassword.requestFocus();
            }else if (TextUtils.isEmpty(confirm)){
                etPassword1.setError("Confirm Password cannot be empty");
                etPassword1.requestFocus();
            }else{
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, login.class));
                        }else{
                            Toast.makeText(Register.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }