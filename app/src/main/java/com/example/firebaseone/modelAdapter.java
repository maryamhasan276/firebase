package com.example.firebaseone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class modelAdapter  extends BaseAdapter {

    ArrayList<model> contacts =new ArrayList<>();
    Activity activity;
    private TextView name;
    private TextView address;
    private TextView phone;

    public modelAdapter(ArrayList<model> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;


    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(activity).inflate(R.layout.activity_main, null);
        name = v.findViewById(R.id.name);
        address = v.findViewById(R.id.address);
        phone = v.findViewById(R.id.phone);

        name.setText(contacts.get(i).getName());
        address.setText(contacts.get(i).getNumber());
        phone.setText(contacts.get(i).getAddress());
        return v;

    }
}


