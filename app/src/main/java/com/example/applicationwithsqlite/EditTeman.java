package com.example.applicationwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.applicationwithsqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class EditTeman extends AppCompatActivity {
    private TextInputEditText editNm, editTlp;
    private Button btnEdit;
    String getNama, getTelepon, getId;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        editNm = findViewById(R.id.editNama);
        editTlp = findViewById(R.id.editTelepon);
        btnEdit = findViewById(R.id.buttonEdit);

        getId = getIntent().getStringExtra("id");
        getNama = getIntent().getStringExtra("nama");
        getTelepon = getIntent().getStringExtra("telepon");

        //menerima data
        setTitle("Edit Data");
        editNm.setText(getNama);
        editTlp.setText(getTelepon);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNama = editNm.getText().toString();
                getTelepon = editTlp.getText().toString();

                HashMap<String, String> qValues = new HashMap<String, String>();
//                Intent i = getIntent();
//                String temanId = i.getStringExtra("id");
//                qValues.put("id", temanId);

                qValues.put("id", getId);
                qValues.put("nama", getNama);
                qValues.put("telepon", getTelepon);

                controller.updateTeman(qValues);
                callHome();
            }
        });
    }

    public void callHome(){
        Intent i = new Intent(EditTeman.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}