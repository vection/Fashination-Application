package com.fash.aviv.fashinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText email,password;
    Button registerb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        email = (EditText)findViewById(R.id.registeremail_id);
        password = (EditText)findViewById(R.id.registerpass_id);
        registerb = (Button) findViewById(R.id.registernow_id);


        registerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  UserRegister();
            }
        });
    }
    protected void UserRegister() {
        String email_ = email.getText().toString().trim();
        String pass_ = password.getText().toString().trim();
        if(TextUtils.isEmpty(email_)) {
            Toast.makeText(RegisterActivity.this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(pass_)) {
            Toast.makeText(RegisterActivity.this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        Customer cus = Customer.SetCustomer(email_,pass_);
        cus.save();

        Toast.makeText(RegisterActivity.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
        Intent p = new Intent(this, MainActivity.class);
        startActivity(p);
    }
}
