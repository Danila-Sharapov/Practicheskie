package com.example.firecloudbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registretion extends AppCompatActivity {

    private EditText RegEm, RegPas;
    private Button RegBut;
    private String email, password;
    private FirebaseAuth fireBAZe;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registretion);

        RegEm = findViewById(R.id.RegEmailET);
        RegPas = findViewById(R.id.RegPaswET);
        RegBut = findViewById(R.id.RegistrBut);
        fireBAZe = FirebaseAuth.getInstance();
        RegBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validData();
            }
        });

    }


    private void validData(){
        email = RegEm.getText().toString().trim();
        password = RegPas.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            RegEm.setError("Неверно введена почта");
        }
        else if(TextUtils.isEmpty(password)){
            RegPas.setError("Пароль не может быть пустым");
        }
        else if(password.length() < 6){
            RegPas.setError("Пароль должен быть больше 6 символов");
        }
        else firebaseRegistration();
    }
    private void firebaseRegistration(){
        fireBAZe.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser firebaseUser = fireBAZe.getCurrentUser();
                        String currentUserEmail = firebaseUser.getEmail();
                        Users newUser = new Users(firebaseUser.getEmail(), firebaseUser.getUid(), null);
                        userRef.add(newUser)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(getApplicationContext(),
                                                "Пользователь " + currentUserEmail + " зарегестрирован",
                                                Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                        Intent intent = new Intent(Registretion.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registretion.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


}