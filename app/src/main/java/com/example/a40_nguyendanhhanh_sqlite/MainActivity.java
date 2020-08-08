package com.example.a40_nguyendanhhanh_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


import com.example.a40_nguyendanhhanh_sqlite.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    SQLHelper sqlHelper = new SQLHelper(this);
    ArrayList<Contact> arrayList;
    static ArrayList<Contact> call = new ArrayList<>();
    AdapterContact adapter;
    int position;
    int EDIT_CONTACT_LIST = 115;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        arrayList = new ArrayList<>();
//       arrayList = sqlHelper.getAllContacts();

        //thêm
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String number = intent.getStringExtra("number");
        arrayList.add(new Contact(name,number));
        sqlHelper.InsertContact(new Contact(name,number));
        adapter = new AdapterContact(arrayList);
       binding.tvPhoneBook.setAdapter(adapter);
        //sqlHelper.DelContact2();

//        String nameET = intent.getStringExtra("nameET");
//        String numberET = intent.getStringExtra("numberET");
//        Contact contact=new Contact(nameET,numberET);
//        sqlHelper.EditContacts(contact,position);
//        adapter.notifyDataSetChanged();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            getcontact();
        }
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,add_new_contact.class);
                startActivity(intent);
            }
        });


        binding.tvPhoneBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = arrayList.get(i).getName();
                String number = arrayList.get(i).getNumber();

                Toast.makeText(getBaseContext(), "Name: " + name
                        + "\n" + "Number: " + number, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), Call.class);
                //truyền 1 biến string qua activity khác
                intent.putExtra("name", name);
                intent.putExtra("number",number);
                position = i;
                startActivityForResult(intent, EDIT_CONTACT_LIST);

            }
        });
    }
    private void getcontact() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            arrayList.add(new Contact(name, number));
            call.add(new Contact(name,number));
            sqlHelper.InsertContact(new Contact(name,number));
            adapter = new AdapterContact(arrayList);
            binding.tvPhoneBook.setAdapter(adapter);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getcontact();
            }
        }
    }
}
