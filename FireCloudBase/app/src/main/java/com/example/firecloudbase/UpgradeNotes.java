package com.example.firecloudbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpgradeNotes extends AppCompatActivity {
    EditText UpTitle, Upsodd;
    Button UppBut;
    String id, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_notes);
        UpTitle = findViewById(R.id.TitleUpgradeET);
        Upsodd = findViewById(R.id.SoddUpgradeET);
        UppBut = findViewById(R.id.UpgradeBut);
        id = getIntent().getStringExtra("id");
        email = getIntent().getStringExtra("EMAIL");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("notes").document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            Notes note = new Notes(documentSnapshot.getString("title"),
                                    documentSnapshot.getString("content"),
                                    documentSnapshot.getString("email"));

                            fill(note);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpgradeNotes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



    private void fill(Notes notes){
        Upsodd.setText(notes.getContent());
        UpTitle.setText(notes.getTitle());
    }

    public void changeNote(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference noteRef = db.collection("notes").document(id);

        noteRef.update("title", UpTitle.getText().toString(),
                        "content", Upsodd.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Изменение подтверждено!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainMenuu.class);
                            intent.putExtra("EMAIL", email);
                            startActivity(intent);
                        }

                    }
                });
    }
    public void deleteNote(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference noteRef = db.collection("notes").document(id);

        noteRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Удаление завершено!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainMenuu.class);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });
    }

}