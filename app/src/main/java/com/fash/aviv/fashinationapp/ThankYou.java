package com.fash.aviv.fashinationapp;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you);

        final TextView count_down = (TextView) findViewById(R.id.count_down);

        final Context t= this;

        CountDownTimer start = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                count_down.setText("redirect to main page in: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent p = new Intent(t, MainActivity.class);
                startActivity(p);
            }

        }.start();
    }
}
