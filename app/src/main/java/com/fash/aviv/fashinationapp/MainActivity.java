package com.fash.aviv.fashinationapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Product> mList = new ArrayList<>();
    ImageView page_shirts,page_jeans,page_boots,page_hat;
    ImageView login_page;
    RecyclerView recyclerView;
    RAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = new ArrayList<>();
        madapter = new RAdapter(this);
        recyclerView    = (RecyclerView) findViewById(R.id.pr_list);
        page_shirts     = (ImageView) findViewById(R.id.page_shirt);
        page_jeans      = (ImageView) findViewById(R.id.page_jeans);
        page_boots      = (ImageView) findViewById(R.id.page_boots);
        page_hat        = (ImageView) findViewById(R.id.page_hat);
        login_page      = (ImageView) findViewById(R.id.login_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));



        getProductsByCateogry("Shirts");

/*      ******************    Saving new data! ***********************************

        mList.add(new Product("32","Bowler Hat", "115", "For bowlers ", R.drawable.oo,"Hats"));
        mList.add(new Product("33","Boater Hat", "130", "for sea men", R.drawable.ooo,"Hats"));
        for(int i=0;i<mList.size();i++) {
            mList.get(i).save();
        }
*/

/*      ******************    Getting single id! ***********************************
        GetSingleId func = new GetSingleId() {
            @Override
            public void run(Map<String, Object> map) {

            }
        };

        DB.onGet("table_name","id_we_want", func);
*/

        page_shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductsByCateogry("Shirts");
            }
        });
        page_jeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductsByCateogry("Pants");
            }
        });

        page_boots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductsByCateogry("Shoes");
            }
        });
        page_hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductsByCateogry("Hats");
            }
        });

        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(v.getContext(), LoginActivity.class);
                startActivity(p);
            }
        });

    }

    public void clearData() {
        mList.clear();
        madapter.notifyDataSetChanged();
    }

    public void getProductsByCateogry(String category_name) {
        clearData();

        final Context con = this;
        GetTable getFunc = new GetTable() {
            @Override
            public void run(List<Map<String, Object>> list) {
                List<Product> products_list = new ArrayList<>();
                for(Map<String,Object> prod_arr:list) {
                    String product_id = String.valueOf(prod_arr.get("key"));
                    Product p = new Product(prod_arr,product_id);
                    products_list.add(p);
                }

                RecyclerView recyclerView = findViewById(R.id.pr_list);
                madapter = new RAdapter(con,products_list);
                recyclerView.setAdapter(madapter);
            }
        };
        DB.OnGetTable("Products","category",category_name,getFunc);
    }

}
