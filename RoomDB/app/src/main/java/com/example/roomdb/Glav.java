package com.example.roomdb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.roomdb.MainActivity;
import com.example.roomdb.R;
import com.example.roomdb.AppDatabase;
import com.example.roomdb.Hist;
import com.example.roomdb.User;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Glav extends Fragment {
    Button buttonHistory, getMoney;
    AppDatabase db;
    TextView textHistor;
    EditText money;
    Spinner spisanie, popolnenie;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = AppDatabase.getObInstance(getContext());
        View root = inflater.inflate(R.layout.fragment_glav, container, false);
        textHistor = root.findViewById(R.id.infoHistory);
        money = root.findViewById(R.id.editText3);
        getMoney = root.findViewById(R.id.button4);
        textHistor.setText(String.valueOf(db.userDao().getUserById(MainActivity.ID).getBalance()));
        buttonHistory = root.findViewById(R.id.button3);
        spisanie = root.findViewById(R.id.spinner);
        popolnenie = root.findViewById(R.id.spinner2);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Integer.parseInt(money.getText().toString()) < 0) {
                        Toast.makeText(getContext(), "Сумма должена быть больше 0", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (Exception e){
                    Toast.makeText(getContext(), "Должно быть число", Toast.LENGTH_LONG).show();
                    return;
                }
                User user = db.userDao().getUserById(MainActivity.ID);
                db.userDao().updateUser(user.getId(), user.getLogin(), user.getPassword(), user.getBalance() + Integer.parseInt(money.getText().toString()));
                db.historyDao().insertHistory(new Hist(true, Integer.parseInt(money.getText().toString()), popolnenie.getSelectedItem().toString(), user.getId()));
                textHistor.setText(String.valueOf(db.userDao().getUserById(MainActivity.ID).getBalance()));
            }
        });
        getMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Integer.parseInt(money.getText().toString()) < 0) {
                        Toast.makeText(getContext(), "Сумма должена быть больше 0", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (Exception e){
                    Toast.makeText(getContext(), "Должно быть число", Toast.LENGTH_LONG).show();
                    return;
                }
                if(db.userDao().getUserById(MainActivity.ID).getBalance() < Integer.parseInt(money.getText().toString())){
                    Toast.makeText(getContext(), "На вашем счету не достаточно денег", Toast.LENGTH_LONG).show();
                    return;
                }
                User user = db.userDao().getUserById(MainActivity.ID);
                db.userDao().updateUser(user.getId(), user.getLogin(), user.getPassword(), user.getBalance() - Integer.parseInt(money.getText().toString()));
                db.historyDao().insertHistory(new Hist(false, Integer.parseInt(money.getText().toString()), spisanie.getSelectedItem().toString(), user.getId()));
                textHistor.setText(String.valueOf(db.userDao().getUserById(MainActivity.ID).getBalance()));
            }
        });
        return root;
    }
}