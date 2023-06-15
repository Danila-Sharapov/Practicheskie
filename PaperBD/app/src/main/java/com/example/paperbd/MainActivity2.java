package com.example.paperbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;

public class MainActivity2 extends AppCompatActivity {

    EditText gunHeader, gunInfo;
    Button addGun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Paper.init(this);

        gunHeader = findViewById(R.id.Name);
        gunInfo = findViewById(R.id.Inf);
        addGun = findViewById(R.id.add_manga);

        PaperDbClass paperDbClass = new PaperDbClass();

        addGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guns guns = new Guns(gunHeader.getText().toString(), gunInfo.getText().toString());

                paperDbClass.addGun(guns, getApplicationContext());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}