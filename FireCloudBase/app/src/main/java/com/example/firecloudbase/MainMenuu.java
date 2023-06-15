package com.example.firecloudbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainMenuu extends AppCompatActivity {


    private TextView tvHello;
    private Button btnExit;
    private RecyclerView rvNotes;

    private Map<String, String> notes = new HashMap<String, String>();
    private String email;

    SharedPreferences userPreferences;
    private static final String USER_PREFERENCES_EMAIL = "email";
    private static final String USER_PREFERENCES_PASS = "password";
    private static final String USER_PREFERENCES = "user_settings";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menuu);

        userPreferences = getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
        tvHello = findViewById(R.id.Textt);
        btnExit = findViewById(R.id.ExitBut);
        rvNotes = findViewById(R.id.ZametkiSpisok);
        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");
        tvHello.setText("Хай, " + email);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query qrRef = db.collection("notes").whereEqualTo("email", email);
        qrRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<DocumentSnapshot> notes = task.getResult().getDocuments();

                            setRecyclerView(notes);
                        }
                        else{
                            Toast.makeText(MainMenuu.this, "У вас нет заметок", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = userPreferences.edit();
                editor.putString(USER_PREFERENCES_EMAIL, "null");
                editor.putString(USER_PREFERENCES_PASS, "null");
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainMenuu.class);
                startActivity(intent);

                Intent intent1 = new Intent(MainMenuu.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    private void setRecyclerView(List<DocumentSnapshot> notes){
        rvNotes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvNotes.setHasFixedSize(true);
        Adapter adapter = new Adapter(getApplicationContext(), notes);
        rvNotes.setAdapter(adapter);
    }
    public void toCreateNote(View view) {
        Intent intent = new Intent(getApplicationContext(), NewNote.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
    }


}