package com.fash.aviv.fashinationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar progress;
    private FirebaseAuth mAuth;
    EditText email, password;
    Button login;
    TextView registered;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        progress = new ProgressBar(this);
        mAuth = FirebaseAuth.getInstance();


        email = (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.password_id);
        login = (Button) findViewById(R.id.login_btn);
        registered = (TextView) findViewById(R.id.register_id);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(p);
            }
        });

    }

    protected void checkUser() {
        final String email_ = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(email_)) {
            Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }


        final Context con = this;
        GetSingleId func_employee = new GetSingleId() {
            @Override
            public void run(Map<String, Object> map) {
                if(map.get("password").equals(pass)) { // login success as employee
                    Employee employee = Employee.SetEmployee(map,email_);
                    Toast.makeText(LoginActivity.this, "Hello " + employee.name, Toast.LENGTH_SHORT).show();
                    // redirect to admin panel

                    Intent p = new Intent(con, AdminActivity.class);
                    startActivity(p);
                } else { // password incorrect
                    Toast.makeText(LoginActivity.this, "password is incorrect", Toast.LENGTH_SHORT).show();

                }
            }
            public void onFail() {
                GetSingleId func_customer = new GetSingleId() {
                    @Override
                    public void run(Map<String, Object> map) {
                        if(map.get("password").equals(pass)) { // login success as customer
                            Customer customer = Customer.SetCustomer(map,email_);
                            Toast.makeText(LoginActivity.this, "Hello " + customer.email, Toast.LENGTH_SHORT).show();

                            Intent p = new Intent(con, MainActivity.class);
                            startActivity(p);
                        } else {
                            Toast.makeText(LoginActivity.this, "password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public void onFail() {
                        Toast.makeText(LoginActivity.this, "not found any user", Toast.LENGTH_SHORT).show();
                    }
                };
                DB.onGet("Customers",email_, func_customer);

            }
        };

        DB.onGet("Employee",email_, func_employee);
    }
}
