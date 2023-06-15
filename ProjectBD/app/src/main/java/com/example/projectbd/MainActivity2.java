package com.example.projectbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText gunHeader, gunInfo;
    Button addGun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gunHeader = findViewById(R.id.Name);
        gunInfo = findViewById(R.id.Inf);
        addGun = findViewById(R.id.add_manga);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        addGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guns guns = new Guns(0,gunHeader.getText().toString(), gunInfo.getText().toString());

                dataBaseHelper.addGun(guns);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}