package com.fash.aviv.fashinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ProductBuyActivity extends AppCompatActivity {
    ImageView prod_image;
    TextView prod_name,prod_desc,prod_price;
    EditText c_email,c_fullname,c_address,c_phone;
    Button c_order;

    String prod_id;

    public ProductBuyActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_buy);

        prod_image = (ImageView) findViewById(R.id.product_image_buy);
        prod_name = (TextView) findViewById(R.id.product_name_buy);
        prod_desc = (TextView) findViewById(R.id.product_description_buy);
        prod_price = (TextView) findViewById(R.id.product_price_buy);

        getIncomingIntent();
        c_email = (EditText) findViewById(R.id.email_id);
        c_fullname = (EditText) findViewById(R.id.fullname_id);
        c_address = (EditText)findViewById(R.id.address_id);
        c_phone = (EditText)findViewById(R.id.phonenumber_id);


        c_order = (Button) findViewById(R.id.product_checkout);

        c_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order();
            }
        });



    }
    private void getIncomingIntent() {
        if(getIntent().hasExtra("product_name") && getIntent().hasExtra("product_desc") && getIntent().hasExtra("product_price") && getIntent().hasExtra("product_image")) {
            Picasso.get().load(getIntent().getStringExtra("product_image")).into(prod_image);
            prod_name.setText(getIntent().getStringExtra("product_name"));
            prod_price.setText(getIntent().getStringExtra("product_price"));
            prod_desc.setText(getIntent().getStringExtra("product_desc"));
            prod_id = getIntent().getStringExtra("product_id");
        }

    }

    private void Order()  {

        if(c_fullname.getText().toString().trim().equals("")) {
            Toast.makeText(ProductBuyActivity.this, "Please insert full name", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = c_email.getText().toString().trim();
        if (email.equals("") || !isValidEmailAddress(email)) {
            Toast.makeText(ProductBuyActivity.this, "Please insert a correct email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(c_address.getText().toString().trim().equals("")){
            Toast.makeText(ProductBuyActivity.this, "Please insert address", Toast.LENGTH_SHORT).show();
            return;
        }

        if(c_phone.getText().toString().trim().equals("")) {
            Toast.makeText(ProductBuyActivity.this, "Please insert phone", Toast.LENGTH_SHORT).show();
            return;
        }


        Date newdate = new Date();
        String uniqueID = UUID.randomUUID().toString();
        Order new_order = new Order(
                uniqueID,
                prod_id,
                newdate,
                prod_price.getText().toString().trim(),
                c_fullname.getText().toString().trim(),
                email,
                c_phone.getText().toString().trim(),
                c_address.getText().toString().trim(),
                false);

        new_order.save();

        Intent p = new Intent(this, ThankYou.class);
        startActivity(p);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}


