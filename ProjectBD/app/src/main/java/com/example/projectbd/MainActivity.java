package com.example.projectbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView listGun;
    Button goAddGun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listGun = findViewById(R.id.recycll);
        goAddGun = findViewById(R.id.add);

        goAddGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        listGun.setLayoutManager(new LinearLayoutManager(this));
        listGun.setHasFixedSize(true);
        RecyclerAdapter adapter = new RecyclerAdapter(this, dataBaseHelper.getGunsList());
        listGun.setAdapter(adapter);

    }
}