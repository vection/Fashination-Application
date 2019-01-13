package com.fash.aviv.fashinationapp;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class AccountActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ArrayList<Order> pOrder;
    TextView welcome;
    ListView Show_orders;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page);
        welcome = (TextView) findViewById(R.id.hello_id);
        Show_orders = (ListView) findViewById(R.id.orders_id);


        //welcome.setText("Hello "+currentUser.getEmail());
        //pOrder = new ArrayList<>();
        getOrders();
        Date s = new Date();
        //pOrder.add(new Order("2","sdas",s, "100","ds","sd","sds", "sds",true));
        //pOrder.add(new Order("2","sdas",s, "100","ds","sd","sds", "sds",true));



        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

    }
    private void getOrders() {
        pOrder = new ArrayList<>();

        GetTable getFunc = new GetTable() {
            @Override
            public void run(List<Map<String, Object>> list) {
                for(Map<String,Object> prod_arr:list) {
                    String product_id = String.valueOf(prod_arr.get("key"));
                    product_id = product_id.substring(0, 4);
                    Order p = new Order(prod_arr,product_id);
                    pOrder.add(p);
                }

                CustomAdapter customAdapter = new CustomAdapter(pOrder);
                Show_orders.setAdapter(customAdapter);

            }
        };
        DB.OnGetTable2("Orders","price","0",getFunc);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class CustomAdapter extends BaseAdapter {
        List<Order> mOrders;
        TextView date;
        TextView orderid;
        TextView product_id;
        TextView product_name;
        TextView product_price;
        TextView orderindex;
        Button details;

        public CustomAdapter(List<Order> mOrders) {
            this.mOrders = mOrders;
        }

        @Override
        public int getCount() {
            return mOrders.size();
        }

        @Override
        public Object getItem(int i) {
            return mOrders.get(i);
        }



        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final int r = i;
            view = getLayoutInflater().from(getApplicationContext()).inflate(R.layout.order_item,null);
            orderid = (TextView) view.findViewById(R.id.order_id_admin);
            product_id = (TextView) view.findViewById(R.id.product_id_admin);
            product_price = (TextView) view.findViewById(R.id.price_id_order);
            orderindex = (TextView) view.findViewById(R.id.orderindex);
            details = (Button) view.findViewById(R.id.details_order_id);


            orderid.setText("Order id: " +mOrders.get(i).getId());
            product_id.setText("Product id: " +mOrders.get(i).getProduct_id());
            product_price.setText("Sold price: "+mOrders.get(i).getPrice());
            orderindex.setText(String.valueOf(i));

           details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDetails(r);
                }
            });



            return view;
        }

       private void getDetails(int i) {
            Intent p = new Intent(getApplicationContext(), OrderDetailsActivity.class);
            Order s = mOrders.get(i);
            p.putExtra("orderid", orderid.getText().toString());
            p.putExtra("product_id", product_id.getText().toString());
            p.putExtra("product_price", product_price.getText().toString());
            p.putExtra("time", s.getTime());
            p.putExtra("ordername", s.getName());
            p.putExtra("address", s.getAddress());
            p.putExtra("email", s.getEmail());
            p.putExtra("phone", s.getPhone());
            p.putExtra("paid", s.getIsPaid());
            startActivity(p);
        }
    }
}
