package com.example.cl1434.lee_ice_cream;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView infoView;
    private TextView seekBarTextView;
    private TextView orderTotalTextView;
    private CheckBox peanutsCheckBox;
    private CheckBox almondsCheckBox;
    private CheckBox strawberriesCheckBox;
    private CheckBox gummyBearCheckBox;
    private CheckBox mmCheckBox;
    private CheckBox browniesCheckBox;
    private CheckBox oreosCheckBox;
    private CheckBox marshmallowsCheckBox;
    private SeekBar seekBar;
    private Spinner iceCreamSpinner;
    private Spinner sizeSpinner;

    private double basePrice;
    private double orderTotal;
    private double optionsPrice;
    private double fudgePrice = 0.15;

    ArrayList<OrderItem> orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoView = (TextView) findViewById(R.id.infoView);
        seekBarTextView = (TextView) findViewById(R.id.seekBarTextView);
        orderTotalTextView = (TextView) findViewById(R.id.orderTotalTextView);
        peanutsCheckBox = (CheckBox) findViewById(R.id.peanutsCheckBox);
        almondsCheckBox = (CheckBox) findViewById(R.id.almondsCheckBox);
        strawberriesCheckBox = (CheckBox) findViewById(R.id.strawberriesCheckBox);
        gummyBearCheckBox = (CheckBox) findViewById(R.id.gummyBearCheckBox);
        mmCheckBox = (CheckBox) findViewById(R.id.mmCheckBox);
        browniesCheckBox = (CheckBox) findViewById(R.id.browniesCheckBox);
        oreosCheckBox = (CheckBox) findViewById(R.id.oreosCheckBox);
        marshmallowsCheckBox = (CheckBox) findViewById(R.id.marshmallowsCheckBox);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarTextView.setText(Integer.toString(progress) + " oz.");
                if (progress == 0)
                    fudgePrice = 0;
                else if (progress == 1)
                    fudgePrice = 0.15;
                else if (progress == 2)
                    fudgePrice = 0.25;
                else
                    fudgePrice = 0.30;
                updatePrice();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        iceCreamSpinner = (Spinner) findViewById(R.id.iceCreamSpinner);
        iceCreamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    basePrice = 2.99;
                else if (position == 1)
                    basePrice = 3.99;
                else
                    basePrice = 4.99;
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        updatePrice();

        orders = new ArrayList<OrderItem>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order_history) {
            Toast.makeText(this, "Order History", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, OrderHistoryActivity.class);
            i.putExtra("DataKey", orders);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, AboutActivity.class);
            i.putExtra("DataKey", "Developed by: Corey Lee");
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void processTheWorks(View view) {
        Log.d("Debug", "The Works Button Pressed");
        peanutsCheckBox.setChecked(true);
        almondsCheckBox.setChecked(true);
        strawberriesCheckBox.setChecked(true);
        gummyBearCheckBox.setChecked(true);
        mmCheckBox.setChecked(true);
        browniesCheckBox.setChecked(true);
        oreosCheckBox.setChecked(true);
        marshmallowsCheckBox.setChecked(true);
        seekBar.setProgress(3);
        optionsPrice = 1.5;
        sizeSpinner.setSelection(2);
        updatePrice();

    }

    public void processOrder(View view) {
        Log.d("Debug", "Order Button Pressed");
        Toast.makeText(this, "Thanks! Your order is being prepared!", Toast.LENGTH_SHORT).show();
        //orders.add(new OrderItem());
    }

    public void processReset(View view) {
        Log.d("Debug", "Reset Button Pressed");
        peanutsCheckBox.setChecked(false);
        almondsCheckBox.setChecked(false);
        strawberriesCheckBox.setChecked(false);
        gummyBearCheckBox.setChecked(false);
        mmCheckBox.setChecked(false);
        browniesCheckBox.setChecked(false);
        oreosCheckBox.setChecked(false);
        marshmallowsCheckBox.setChecked(false);
        seekBar.setProgress(1);
        optionsPrice = 0.0;
        sizeSpinner.setSelection(0);
        updatePrice();
    }

    public void updatePrice() {
        Log.d("Debug", "Price updated");
        orderTotal = basePrice + optionsPrice + fudgePrice;
        orderTotalTextView.setText("$" + Double.toString(orderTotal));

    }

    public void processCheckBoxChanged(View view) {
        if (view.getId() == R.id.peanutsCheckBox) {
            if (peanutsCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.15;
            else
                optionsPrice = optionsPrice - 0.15;
            updatePrice();
        }
        if (view.getId() == R.id.almondsCheckBox) {
            if (almondsCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.15;
            else
                optionsPrice = optionsPrice - 0.15;
            updatePrice();
        }
        if (view.getId() == R.id.strawberriesCheckBox) {
            if (strawberriesCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.20;
            else
                optionsPrice = optionsPrice - 0.20;
            updatePrice();
        }
        if (view.getId() == R.id.gummyBearCheckBox) {
            if (gummyBearCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.20;
            else
                optionsPrice = optionsPrice - 0.20;
            updatePrice();
        }
        if (view.getId() == R.id.mmCheckBox) {
            if (mmCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.25;
            else
                optionsPrice = optionsPrice - 0.25;
            updatePrice();
        }
        if (view.getId() == R.id.browniesCheckBox) {
            if (browniesCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.20;
            else
                optionsPrice = optionsPrice - 0.20;
            updatePrice();
        }
        if (view.getId() == R.id.oreosCheckBox) {
            if (oreosCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.20;
            else
                optionsPrice = optionsPrice - 0.20;
            updatePrice();
        }
        if (view.getId() == R.id.marshmallowsCheckBox) {
            if (marshmallowsCheckBox.isChecked() == true)
                optionsPrice = optionsPrice + 0.15;
            else
                optionsPrice = optionsPrice - 0.15;
            updatePrice();
        }
    }
}
