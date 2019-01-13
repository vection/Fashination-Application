package com.fash.aviv.fashinationapp;

import android.accounts.Account;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        ImageView add_product_image = findViewById(R.id.add_product_image);
        ImageView orders_page_image = findViewById(R.id.orders_page_image);
        ImageView account_page_image = findViewById(R.id.account_page_image);



        add_product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(v.getContext(), AddProductActivity2.class);
                startActivity(p);
            }
        });

        orders_page_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(v.getContext(), AccountActivity.class);
                startActivity(p);
            }
        });

        account_page_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(v.getContext(), EmployeeAccountActivity.class);
                startActivity(p);
            }
        });
    }
}
