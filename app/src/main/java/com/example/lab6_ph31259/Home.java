package com.example.lab6_ph31259;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    Button btThemMoi;
    ListView lstview;
    TextView edtTim;
    ArrayList<SinhVienModel> lst = new ArrayList<>();

    SinhVienAdapter adapter = new SinhVienAdapter(lst,Home.this);

    ActivityResultLauncher<Intent> nhan = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String cs = b.getString(Them.KEY_COSO);
                        String ten = b.getString(Them.KEY_TEN_SV);
                        String dc = b.getString(Them.KEY_DIA_CHI);
                        lst.add(new SinhVienModel(cs, ten, dc));
                        fill();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btThemMoi = findViewById(R.id.btThemMoi);
        lstview = findViewById(R.id.lstview);
        edtTim = findViewById(R.id.edt_tim);
        edtTim.setVisibility(View.GONE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lst.add(new SinhVienModel("FPoly Hà Nội", "Đinh Tiến Lực", "Ninh Bình"));
        lst.add(new SinhVienModel("FPoly Hà Nội", "Ngô Tiến Tuấn", "Thanh Hóa"));
        lst.add(new SinhVienModel("FPoly Hà Nội", "Mai Trọng Linh", "Nam Định"));
        fill();


        btThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Them.class);
                nhan.launch(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quanlysv_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_them_sv) {
            Intent i = new Intent(Home.this, Them.class);
            nhan.launch(i);
        } else if (item.getItemId() == R.id.menu_dang_xuat) {
            Intent i = new Intent();
            setResult(RESULT_OK,i);
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_bang_diem) {
            Toast.makeText(this, "Bảng Điểm", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_diem_danh) {
            Toast.makeText(this, "Điểm danh", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_search) {
            if (edtTim.getVisibility() == View.VISIBLE) {
                edtTim.setVisibility(View.GONE);
            } else {
                edtTim.setVisibility(View.VISIBLE);
            }
            edtTim.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                    lstview.setAdapter(adapter);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else if (item.getItemId() == R.id.menu_lich_hoc){
            Toast.makeText(this, "Lịch Học", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void fill() {
        adapter = new SinhVienAdapter(lst,Home.this);
        lstview.setAdapter(adapter);
    }

    public void deleteSV(int index) {
        lst.remove(index);
        fill();
    }

    public static final String KEY_SV_MODEL = "sv_model";

    ActivityResultLauncher<Intent> goToEditSV = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent i = result.getData();
                        Bundle b = i.getExtras();
                        String cs = b.getString(Them.KEY_COSO);
                        String ten = b.getString(Them.KEY_TEN_SV);
                        String dc = b.getString(Them.KEY_DIA_CHI);

                        svModel.hoTen = ten;
                        svModel.diaChi = dc;
                        svModel.coSo = cs;
                        fill();
                    }
                }
            }
    );

    private SinhVienModel svModel;

    public void updateSV(int position) {

        Intent intent = new Intent(Home.this, Them.class);

        svModel = lst.get(position);
        intent.putExtra(KEY_SV_MODEL, svModel);
        goToEditSV.launch(intent);
    }
    @Override
    public void onBackPressed() {
        finish();

        Intent intent = new Intent(this, DangNhap.class);
        startActivity(intent);

        overridePendingTransition(0, 0);
    }
}