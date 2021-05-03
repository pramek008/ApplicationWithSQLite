package com.example.applicationwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailTeman extends AppCompatActivity {
    TextView tvnama,tvtelp;
    String nm,id,tlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvnama = findViewById(R.id.tvNamaKontak);
        tvtelp = findViewById(R.id.tvNomorTelepon);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telepon");

        setTitle("Detail Data");
        tvnama.setText(nm);
        tvtelp.setText(tlp);
    }
}