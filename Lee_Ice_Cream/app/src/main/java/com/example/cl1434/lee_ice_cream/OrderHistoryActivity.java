package com.example.cl1434.lee_ice_cream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    ArrayList<OrderItem> order;
    ArrayList<String> stringOrder;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        setTitle("Order History");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        order = (ArrayList<OrderItem>) i.getSerializableExtra("DataKey");

        stringOrder = new ArrayList<String>();
        for (OrderItem item: order) {
            stringOrder.add(item.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                stringOrder);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent directory
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
