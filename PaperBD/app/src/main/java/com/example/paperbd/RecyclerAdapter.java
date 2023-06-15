package com.example.paperbd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context cont;
    ArrayList<Guns> gunsList;

    public RecyclerAdapter(Context cont, ArrayList<Guns> gunsList) {
        this.cont = cont;
        this.gunsList = gunsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cont).inflate(R.layout.item_card, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        Guns guns = gunsList.get(pos);
        holder.Header.setText(guns.getHeader());
        holder.Info.setText(guns.getInfo());
    }

    @Override
    public int getItemCount() {
        return gunsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Header;
        TextView Info;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Header = itemView.findViewById(R.id.Name);
            Info = itemView.findViewById(R.id.Info);
        }
    }
}
