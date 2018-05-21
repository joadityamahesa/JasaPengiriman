package com.example.android.jasapengiriman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton registrasi;
    EditText user,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrasi = (ImageButton) findViewById(R.id.btnDaftar);
        user= (EditText) findViewById(R.id.txtUser);
        password=(EditText) findViewById(R.id.txtPassword);
        login = (Button) findViewById(R.id.btnLogin);

        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,Registrasi.class);
//                startActivity(intent);

                Toast.makeText(MainActivity.this,"Fitur akan segera hadir",Toast.LENGTH_LONG).show();
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this,"Mohon isi kolom diatas untuk memulai aplikasi!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Selamat Datang, "+user.getText().toString(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,ListJasa.class);
                    startActivity(intent);
                }

            }
        });
    }
}
