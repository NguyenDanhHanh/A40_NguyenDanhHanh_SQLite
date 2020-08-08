package com.example.a40_nguyendanhhanh_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.a40_nguyendanhhanh_sqlite.databinding.ActivityAddNewContactBinding;

import java.util.ArrayList;

public class add_new_contact extends AppCompatActivity {
    ActivityAddNewContactBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);
        binding.btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etNameNew.getText().toString();
                String number= binding.etPhoneNew.getText().toString();
                Intent intent = new Intent(add_new_contact.this,MainActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("number",number);
                startActivity(intent);
            }
        });
    }
}