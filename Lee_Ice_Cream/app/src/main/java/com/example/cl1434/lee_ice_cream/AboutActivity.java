package com.example.cl1434.lee_ice_cream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView aboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle("About");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        aboutTextView = (TextView) findViewById(R.id.aboutTextView);

        Intent i = getIntent();
        String message = i.getStringExtra("DataKey");
        aboutTextView.setText(message);
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
