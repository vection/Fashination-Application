package com.fash.aviv.fashinationapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OrderDetailsActivity extends AppCompatActivity {
    TextView orderid,time,prod_id,email,address,price,paid,number;

    public OrderDetailsActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_page);

        orderid = (TextView) findViewById(R.id.details_order_id);
        time = (TextView) findViewById(R.id.details_time_id);
        prod_id = (TextView) findViewById(R.id.details_product_id);
        email = (TextView) findViewById(R.id.email_details_id);
        address = (TextView) findViewById(R.id.details_address_id);
        price = (TextView) findViewById(R.id.details_price_id);
        paid = (TextView) findViewById(R.id.paid_details_id);
        number = (TextView) findViewById(R.id.details_phone_id);


        getIncomingIntent();

    }
    private void getIncomingIntent() {
        orderid.setText(getIntent().getStringExtra("orderid"));
        prod_id.setText(getIntent().getStringExtra(""));
        price.setText("Price:" + getIntent().getStringExtra("product_price"));
        time.setText("Date:" + getIntent().getStringExtra("time"));
        address.setText("Address" + getIntent().getStringExtra("address"));
        email.setText("Email:" + getIntent().getStringExtra("email"));
        number.setText("Phone Number:" + getIntent().getStringExtra("phone"));
        paid.setText("Is Paid?:" + getIntent().getStringExtra("paid"));

    }
}
