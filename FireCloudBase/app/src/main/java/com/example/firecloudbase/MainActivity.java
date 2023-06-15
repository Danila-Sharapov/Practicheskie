package com.example.firecloudbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private String email, password;
    private EditText EmailET, PaswwET;
    private Button EntryButt;

    private static final String USER_PREFERENCES = "user_settings";
    private static final String USER_PREFERENCES_EMAIL = "email";
    private static final String USER_PREFERENCES_PASS = "password";

    SharedPreferences PolzPref;

    FirebaseAuth fireBaze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmailET = findViewById(R.id.EmailEditText);
        PaswwET = findViewById(R.id.PaswwEditText);

        fireBaze = FirebaseAuth.getInstance();
        PolzPref =getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);

        checkAutorization();

    }


    private void checkAutorization(){
        if (PolzPref.contains(USER_PREFERENCES_EMAIL) && PolzPref.contains(USER_PREFERENCES_PASS)){
            String prefEmail = PolzPref.getString(USER_PREFERENCES_EMAIL, "null");
            String prefPassword = PolzPref.getString(USER_PREFERENCES_PASS, "null");

            fireBaze.signInWithEmailAndPassword(prefEmail, prefPassword)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent intent = new Intent(MainActivity.this, MainMenuu.class);
                            intent.putExtra("EMAIL", prefEmail);
                            startActivity(intent);
                        }
                    });
        }
    }

    public void clickReg(View view) {
        Intent intent = new Intent(this, Registretion.class);
        startActivity(intent);
    }

    public void clickEntry(View view) {
        validData();
    }

    private void validData(){
        email = EmailET.getText().toString().trim();
        password = PaswwET.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmailET.setError("Введите нормальную почта");
        }
        else if(TextUtils.isEmpty(password)){
            PaswwET.setError("Пустого пароля не может быть!");
        }
        else if(password.length() < 6){
            PaswwET.setError("Пароль должен содержать в себе больше 6 символов!");
        }
        else firebaseAutorization();
    }

    private void firebaseAutorization(){
        fireBaze.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(MainActivity.this, MainMenuu.class);
                        intent.putExtra("EMAIL", email);
                        startActivity(intent);

                        SharedPreferences.Editor editor = PolzPref.edit();
                        editor.putString(USER_PREFERENCES_EMAIL, email);
                        editor.putString(USER_PREFERENCES_PASS, password);
                        editor.apply();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Ошибка авторизации "+ e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}