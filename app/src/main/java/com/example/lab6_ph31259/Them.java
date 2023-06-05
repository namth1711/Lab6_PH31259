package com.example.lab6_ph31259;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Them extends AppCompatActivity {
    Spinner sp;
    TextView edtName, edtAddress;
    Button btnSubmit;

    public static final String KEY_COSO = "coso";
    public static final String KEY_TEN_SV="ten";
    public static final String KEY_DIA_CHI="diachi";
    ArrayList<School> list =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        sp = findViewById(R.id.sp);
        edtName =findViewById(R.id.edtName);
        edtAddress =findViewById(R.id.edtAddress);
        btnSubmit =findViewById(R.id.btSubmit);

        list.add(new School(R.drawable.hn,"FPoly Hà Nội"));
        list.add(new School(R.drawable.dn,"FPoly Đà Nẵng"));
        list.add(new School(R.drawable.tn,"FPoly Tây Nguyên"));
        list.add(new School(R.drawable.logo,"FPoly Hồ Chí Minh"));
        list.add(new School(R.drawable.ct,"FPoly Cần Thơ"));

        SinhVienModel svModel = (SinhVienModel) getIntent().getSerializableExtra(Home.KEY_SV_MODEL);
        SchoolAdapter adapter = new SchoolAdapter(list,Them.this);
        sp.setAdapter(adapter);
        if (svModel != null){
            edtName.setText(svModel.hoTen);
            edtAddress.setText(svModel.diaChi);

            int position = list.indexOf(svModel.coSo);
            sp.setSelection(position);
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = sp.getSelectedItemPosition();
                String cs = list.get(index).ten;

                String name = edtName.getText().toString();
                String adr = edtAddress.getText().toString();
                if (name.trim().equals("")) {
                    Toast.makeText(Them.this, "Tên SV không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (adr.trim().equals("")) {
                    Toast.makeText(Them.this, "Địa chỉ không được để trống!", Toast.LENGTH_SHORT).show();
                } else{
                    Intent i =new Intent();
                    Bundle b = new Bundle();
                    b.putString(KEY_COSO,cs);
                    b.putString(KEY_DIA_CHI,adr);
                    b.putString(KEY_TEN_SV,name);
                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });
    }

}