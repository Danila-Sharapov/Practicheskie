package com.example.roomdb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class menu extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Glav()).commit();
        setTitle("Главная");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Glav()).commit();
                        setTitle("Главная");
                        break;
                    case R.id.bal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new BlankFragment()).commit();
                        setTitle("История");
                        break;
                }
                return true;
            }
        });
    }


    public void onBack(View view) {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor =  MainActivity.shared.edit();
        editor.putString(MainActivity.SHARED_LOGIN, "");
        editor.putString(MainActivity.SHARED_PASSWORD, "");
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}