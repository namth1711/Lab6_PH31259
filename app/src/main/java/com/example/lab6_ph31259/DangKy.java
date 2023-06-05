package com.example.lab6_ph31259;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        EditText txtname = findViewById(R.id.edt_User);
        EditText txtpass = findViewById(R.id.edt_Pass);
        EditText txtconfirm = findViewById(R.id.edt_Retypepass);
        Button btndangki = findViewById(R.id.btn_back);

        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtname.getText().toString();
                String pass = txtpass.getText().toString();
                String confirm = txtconfirm.getText().toString();
                if (name.equals("") || pass.equals("") || confirm.equals("") || !pass.equals(confirm)) {
                    Toast.makeText(DangKy.this, "Dữ liệu sai", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences data = getSharedPreferences("abc", MODE_PRIVATE);
                    SharedPreferences.Editor ed = data.edit();
                    ed.putString("Username", name);
                    ed.putString("password", pass);
                    ed.apply();
                    Intent i = new Intent();
                    Bundle b = new Bundle();
                    b.putString("username", name);
                    b.putString("password", pass);
                    i.putExtras(b);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }
}