package com.example.projectbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView Name, Info, AllInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Name = findViewById(R.id.NameGun);
        Info = findViewById(R.id.InfoGun);
        AllInfo = findViewById(R.id.AllInfoGun);

        Intent intent = getIntent();
        String nameC = intent.getStringExtra("iName");
        String infoC = intent.getStringExtra("iDesc");
        String allinfoC = intent.getStringExtra("iDesc");

        Name.setText(nameC);
        Info.setText(infoC);
        AllInfo.setText(allinfoC);
    }
}