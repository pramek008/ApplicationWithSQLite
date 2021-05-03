package com.example.applicationwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.applicationwithsqlite.database.DBController;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class TemanBaru extends AppCompatActivity {
    private TextInputEditText tNama, tTelepon;
    private Button simpanBtn;
    String nm,tlp;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_baru);

        tNama = (TextInputEditText)findViewById(R.id.inputNama);
        tTelepon = (TextInputEditText)findViewById(R.id.inputTelepon);
        simpanBtn = (Button)findViewById(R.id.buttonSave);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((tNama.getText().toString()).equals("") || (tTelepon.getText().toString()).equals("")){
                    Snackbar t = Snackbar.make(v, "Isi semua Kolom !!!",Snackbar.LENGTH_SHORT);
                    t.show();
                }else {
                    nm = tNama.getText().toString();
                    tlp = tTelepon.getText().toString();

                    HashMap<String,String> qValues = new HashMap<>();
                    qValues.put("nama", nm);
                    qValues.put("telepon",tlp);

                    controller.insertData(qValues);
                    callHome();
                }
            }
        });
    }

    public void callHome(){
        Intent i = new Intent(TemanBaru.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}