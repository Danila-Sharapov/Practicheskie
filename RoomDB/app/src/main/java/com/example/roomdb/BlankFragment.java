package com.example.roomdb;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

public class BlankFragment extends Fragment {
    TextView infoBal;
    AppDatabase db;
    int sumA, sumB, sumC, sumD;
    Button btMoney, getMoney;
    PieChart pieChart;
    TextView A, B, C, D;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_blank, container, false);
        db = AppDatabase.getObInstance(getContext());
        pieChart = root.findViewById(R.id.piechart);
        infoBal = root.findViewById(R.id.infoBal);
        btMoney = root.findViewById(R.id.popolneniee);
        getMoney = root.findViewById(R.id.spisanie);
        A = root.findViewById(R.id.A);
        B = root.findViewById(R.id.B);
        C = root.findViewById(R.id.C);
        D = root.findViewById(R.id.D);
        pieChart = root.findViewById(R.id.piechart);
        loadGetMoneyText();
        loadDiagram();
        btMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMoneyText();
                loadMoneyText();
                loadDiagram();
                infoBal.setText(String.valueOf(sumA + sumB + sumC + sumD));
            }

        });
        getMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMoneyText();
                loadGetMoneyText();
                loadDiagram();
                infoBal.setText(String.valueOf(sumA + sumB + sumC + sumD));
            }
        });
        infoBal.setText(String.valueOf(sumA + sumB + sumC + sumD));
        return root;
    }
    public void loadGetMoneyText(){
        List<Hist> textHistor = db.historyDao().getHistory(MainActivity.ID);
        for(int i = 0; i < textHistor.size(); i++) {
            if(!textHistor.get(i).isCheck()) {
                switch (textHistor.get(i).getCategory()){
                    case "На колбасу":
                        sumA += textHistor.get(i).getCount();
                        break;
                    case "На сыр":
                        sumB += textHistor.get(i).getCount();;
                        break;
                    case "На пк":
                        sumC += textHistor.get(i).getCount();
                        break;
                    case "На диплом":
                        sumD += textHistor.get(i).getCount();
                        break;
                }
            }
        }
    }
    public void loadMoneyText(){
        List<Hist> textHistor = db.historyDao().getHistory(MainActivity.ID);
        for(int i = 0; i < textHistor.size(); i++) {
            if(textHistor.get(i).isCheck()) {
                switch (textHistor.get(i).getCategory()){
                    case "Заработал":
                        sumA += textHistor.get(i).getCount();
                        break;
                    case "Украл":
                        sumB += textHistor.get(i).getCount();
                        break;
                    case "Выпросил":
                        sumC += textHistor.get(i).getCount();
                        break;
                    case "Из магического мешка":
                        sumD += textHistor.get(i).getCount();
                        break;
                }
            }
        }
    }
    public void loadDiagram(){
        pieChart.clearChart();
        pieChart.addPieSlice(
                new PieModel(
                        "На колбасу",
                        sumA,
                        Color.parseColor("#0033ED")));
        pieChart.addPieSlice(
                new PieModel(
                        "На сыр",
                        sumB,
                        Color.parseColor("#000000")));
        pieChart.addPieSlice(
                new PieModel(
                        "На пк",
                        sumC,
                        Color.parseColor("#C600F3")));
        pieChart.addPieSlice(
                new PieModel(
                        "На диплом",
                        sumD,
                        Color.parseColor("#EF0000")));
    }
    public void getMoneyText(){
        setDefaultText();
        A.setText("На колбасу");
        B.setText("На сыр");
        C.setText("На пк");
        D.setText("На диплом");
    }
    public void setMoneyText(){
        setDefaultText();
        A.setText("Заработал");
        B.setText("Украл");
        C.setText("Выпросил");
        D.setText("Из магического мешка");
    }
    public void setDefaultText(){
        sumA = 0;
        sumB = 0;
        sumC = 0;
        sumD = 0;
    }
}