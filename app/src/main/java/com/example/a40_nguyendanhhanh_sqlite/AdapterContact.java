package com.example.a40_nguyendanhhanh_sqlite;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class  AdapterContact extends BaseAdapter {
    List<Contact> contactList;

    public AdapterContact(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_contact, viewGroup, false);

        TextView tvName =view.findViewById(R.id.tvName);
        TextView tvNumber =view.findViewById(R.id.tvNumber);

        Contact contact=contactList.get(i);

        tvName.setText(contact.getName());
        tvNumber.setText(contact.getNumber());

        ImageView imageView=view.findViewById(R.id.imgContact);
        imageView.setVisibility(View.VISIBLE);



        return view;
    }
}
