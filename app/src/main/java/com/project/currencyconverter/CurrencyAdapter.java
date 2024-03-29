package com.project.currencyconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<Currency> {
    private int resourceId;
    private List<Currency> list;
    public CurrencyAdapter(Context context, int resource, List<Currency> objects) {
        super(context, resource, objects);
        list = objects;
        resourceId = resource;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView);
    }

    private View initView(int position, View convertView) {
        if(convertView == null) {
            convertView = View.inflate(getContext(), R.layout.currency_item, null);
        }
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvCharCode = convertView.findViewById(R.id.tvCharCode);
        TextView tvNumCode = convertView.findViewById(R.id.tvNumCode);
        Currency currency = getItem(position);
        tvName.setText(currency.name);
        tvCharCode.setText(currency.charCode);
        tvNumCode.setText(currency.numCode);
        return convertView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Currency currency = list.get(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvCharCode = view.findViewById(R.id.tvCharCode);
        TextView tvNumCode = view.findViewById(R.id.tvNumCode);
        tvName.setText(currency.name);
        tvCharCode.setText(currency.charCode);
        tvNumCode.setText(currency.numCode);
        return view;
    }
}
