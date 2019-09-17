package com.project.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText editValue;

    private CurrencyData data;
    private CurrencyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new CurrencyData(this);

        tvResult = findViewById(R.id.tvResult);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        editValue = findViewById(R.id.editValue);


        editValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CountCurrency(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        adapter = new CurrencyAdapter(this, R.layout.currency_item, data.GetCurrenciesData());
        adapter.setDropDownViewResource(R.layout.currency_item);
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Currency item = (Currency) adapterView.getAdapter().getItem(i);
                Toast.makeText(MainActivity.this, item.name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        fromSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(listener);
        toSpinner.setAdapter(adapter);
        toSpinner.setSelection(data.getIndexByCharCode("USD"));
        toSpinner.setOnItemSelectedListener(listener);

        CountCurrency(editValue.getText().toString());
    }

    private void CountCurrency(String value){
        Currency fromCurrency = (Currency)fromSpinner.getSelectedItem();
        Currency toCurrency = (Currency)toSpinner.getSelectedItem();

        try {
            float val = Float.parseFloat(value);


            float c1 = fromCurrency.value / fromCurrency.nominal;
            c1 *= val;//uah

            float c2 = toCurrency.value / toCurrency.nominal;
            float res = c1 / c2;

            tvResult.setText(String.valueOf(res));
        }catch (Exception ex){
            tvResult.setText("Input value");
        }

    }

}