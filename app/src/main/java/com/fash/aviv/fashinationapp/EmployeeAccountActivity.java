package com.fash.aviv.fashinationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeAccountActivity extends AppCompatActivity {

    EditText name,email,pass,phone,rank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_account);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        phone = (EditText) findViewById(R.id.phone);
        rank = (EditText) findViewById(R.id.rank);

        Button update_btn = (Button) findViewById(R.id.update_btn);
        populate();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });
    }

    public void populate() {
        name.setText(Employee.emp.name);
        email.setText(Employee.emp.email);
        pass.setText(Employee.emp.password);
        phone.setText(Employee.emp.phone);
        rank.setText(Employee.emp.rank);
    }

    private void updateEmployee()  {

        if(name.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please insert full name", Toast.LENGTH_SHORT).show();
            return;
        }


        if(pass.getText().toString().trim().equals("")) {
            Toast.makeText(EmployeeAccountActivity.this, "Please insert password", Toast.LENGTH_SHORT).show();
            return;
        }


        if(phone.getText().toString().trim().equals("")) {
            Toast.makeText(EmployeeAccountActivity.this, "Please insert phone", Toast.LENGTH_SHORT).show();
            return;
        }

        Employee.emp.name = name.getText().toString().trim();
        Employee.emp.password = pass.getText().toString().trim();
        Employee.emp.rank = rank.getText().toString().trim();
        Employee.emp.phone = phone.getText().toString().trim();


        Employee.emp.save();

        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        Intent p = new Intent(this, AdminActivity.class);
        startActivity(p);
    }

}
