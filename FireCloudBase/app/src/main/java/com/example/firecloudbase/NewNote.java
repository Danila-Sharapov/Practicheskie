package com.example.firecloudbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewNote extends AppCompatActivity {
    EditText NewSoddET, NewTitleeET;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");
        NewTitleeET = findViewById(R.id.NewTitlee);
        NewSoddET = findViewById(R.id.NewSodd);
    }


    public void createNote(View view) {
        if (NewTitleeET.getText().length() > 0 & NewSoddET.getText().length() > 0){
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Notes newNote = new Notes(NewTitleeET.getText().toString(), NewSoddET.getText().toString(), email);

            db.collection("notes").add(newNote)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Заметка успешно создана", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainMenuu.class);
                            intent.putExtra("EMAIL", email);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewNote.this, "Ошибка!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}