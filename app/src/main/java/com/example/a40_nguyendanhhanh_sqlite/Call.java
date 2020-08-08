package com.example.a40_nguyendanhhanh_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Call extends AppCompatActivity {
    EditText txtNameCall,txtNumberCall;
    Button  btnCall,btnEdit;
    String name="",number="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        txtNameCall= findViewById(R.id.txtNameCall);
        txtNumberCall = findViewById(R.id.txtNumberPhone);
        btnCall = findViewById(R.id.btnCall);
        btnEdit=findViewById(R.id.btnEdit);

        final Intent intent = getIntent();
        name = intent.getStringExtra("name");
        txtNameCall.setText(name);
        number = intent.getStringExtra("number");
        txtNumberCall.setText(number);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Call.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Call.this,new String[] {Manifest.permission.CALL_PHONE},1);
                }
                else {
                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+txtNumberCall.getText().toString()));
                        startActivity(callIntent);
                    } catch (ActivityNotFoundException activityException) {
                        Log.e("Calling a Phone Number", "Call failed", activityException);
                    }
                }
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameET = txtNameCall.getText().toString();
                String numberET=txtNumberCall.getText().toString();
                Intent intent1 = new Intent(Call.this,MainActivity.class);
                intent1.putExtra("nameET",nameET);
                intent1.putExtra("numberET",numberET);
                startActivity(intent1);
            }
        });
    }
}